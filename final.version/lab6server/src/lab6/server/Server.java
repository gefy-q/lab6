package lab6.server;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import lab6.controllers.CollectionController;
import lab6.model.Dragon;
import lab6.representations.json.JsonCollectionControllerRepr;
import lab6.udp.ChunksData;
import lab6.udp.ServerCommand;
import lab6.udp.ServerCommandType;
import lab6.udp.Utils;

public class Server {
    public static final int PACKET_SIZE = 5*1024-8;
    private DatagramChannel channel;
    private Selector selector;
    private SocketAddress clientAddress;
    private Map<String, ChunksData> clientChunks = new HashMap<>();
    
    private CollectionController controller = null;
    private Path path = null;
    
    public Server(Path path, CollectionController controller) {
        this.path = path;
        this.controller = controller;
    }
    public void run() throws IOException {
        // Создаем канал и настраиваем его на неблокирующий режим
        channel = DatagramChannel.open();
        channel.configureBlocking(false);
        int bufferSize = 1024 * 1024; // 1 MB
        channel.setOption(StandardSocketOptions.SO_RCVBUF, bufferSize);
        channel.setOption(StandardSocketOptions.SO_SNDBUF, bufferSize);

        Scanner scanner = new Scanner(System.in);
        int port;
        while (true){
            System.out.println("Введите порт: ");
            try{
                port = Integer.parseInt(scanner.nextLine());
                if(available(port)){
                    channel.bind(new InetSocketAddress(port));
                    break;
                }
            }catch (Exception e){
                System.out.println("Порт не подходит");
            }
        }
        selector = Selector.open();
        channel.register(selector, SelectionKey.OP_READ);
        System.out.println("Сервер запущен");

        // Ожидаем сообщения от клиента
        while (true) {
            selector.select();
            // Обрабатываем готовые ключи
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();
                // Если ключ готов к чтению
                if (key.isReadable()) {
                    ByteBuffer bufferHelp = ByteBuffer.allocate(PACKET_SIZE+8);
                    clientAddress = channel.receive(bufferHelp);
                    byte[] recieved = bufferHelp.array();
                    System.out.println("Получен чанк длины "+recieved.length+" байт от клиента "+clientAddress.toString());
                    byte validation = Utils.checkFirst4Bytes(recieved);
                    if(validation == 1) {
                        byte[] size = Arrays.copyOfRange(recieved, 4, 8);
                        ChunksData chunksData = new ChunksData(Utils.fromByteArray(size));
                        clientChunks.put(clientAddress.toString(), chunksData);
                    } else if(validation == 0) {
                        if(!clientChunks.containsKey(clientAddress.toString()))
                            continue;
                        int index = Utils.fromByteArray(Arrays.copyOfRange(recieved, 4, 8));
                        byte[] data = Arrays.copyOfRange(recieved, 8, recieved.length);
                        clientChunks.get(clientAddress.toString()).addChunk(index, data);
                        if(clientChunks.get(clientAddress.toString()).isReady()) {
                            // YAY WE GOT IT
                            byte[] request = clientChunks.get(clientAddress.toString()).getFullResponse();
                            System.out.println("Получен полный запрос клиента длиной в "+request.length+" байт.");
                            ServerCommand message = executeInput((ServerCommand)Utils.deserializeObject(request));
                            System.out.println("Отправляем ответ " + message.type + " клиенту "+clientAddress.toString());
                            List<byte[]> chunks = splitByteArray(Utils.serializeObject(message));
                            List<byte[]> firstInfo = new ArrayList<>();
                            firstInfo.add(new byte[]{1,1,1,1});
                            firstInfo.add(Utils.intToBytes(chunks.size()));
                            ByteBuffer firstInfoSize = ByteBuffer.wrap(Utils.joinByteArrays(firstInfo));
                            channel.send(firstInfoSize, clientAddress);
                            System.out.println("Отправлен чанк длиной "+firstInfoSize.array().length+" байт клиенту "+clientAddress.toString());
                            for (int i = 0; i < chunks.size(); i++) {
                                List<byte[]> chunkInfo = new ArrayList<>();
                                chunkInfo.add(new byte[]{0,0,0,0});
                                chunkInfo.add(Utils.intToBytes(i));
                                chunkInfo.add(chunks.get(i));
                                ByteBuffer responseBuffer = ByteBuffer.wrap(Utils.joinByteArrays(chunkInfo));
                                channel.send(responseBuffer, clientAddress);
                                System.out.println("Отправлен чанк длиной "+responseBuffer.array().length+" байт клиенту "+clientAddress.toString());
                            }
                            clientChunks.remove(clientAddress.toString());
                        }
                    }

                }
            }
        }
    }
    /**
     * Execute action
     * @param action Command with arguments
     * @return Result
     */
    public final ServerCommand executeInput(ServerCommand action) {
        ServerCommandType type = action.type;
        byte[] args = action.data;
        try {
            switch (type) {
                case ADD:
                    controller.add((Dragon)Utils.deserializeObject(args));
                    save();
                    return new ServerCommand(ServerCommandType.ADD, null);
                case ADD_IF_MAX:
                    controller.addIfMax((Dragon)Utils.deserializeObject(args));
                    save();
                    return new ServerCommand(ServerCommandType.ADD_IF_MAX, null);
                case CLEAR:
                    controller.clear();
                    save();
                    return new ServerCommand(ServerCommandType.CLEAR, null);
                case COUNT_LESS_THAN_WINGSPAN:
                    return new ServerCommand(ServerCommandType.COUNT_LESS_THAN_WINGSPAN, Utils.intToBytes(controller.countLessThanWingspan((Double)Utils.deserializeObject(args))));
                case COUNT_BY_AGE:
                    return new ServerCommand(ServerCommandType.COUNT_BY_AGE, Utils.intToBytes(controller.countByAge(Utils.fromByteArray(args))));
                case FIND_INDEX:
                    return new ServerCommand(ServerCommandType.FIND_INDEX, Utils.intToBytes(controller.findIndexById(Utils.fromByteArray(args))));
                case GET_INDEX:
                    return new ServerCommand(ServerCommandType.GET_INDEX, Utils.intToBytes(controller.getIndexById(Utils.fromByteArray(args))));
                case GET_INIT:
                    return new ServerCommand(ServerCommandType.GET_INIT, Utils.serializeObject(controller.getInitTime()));
                case INSERT:
                    ArrayList<Object> insertArgs = (ArrayList<Object>)Utils.deserializeObject(args);
                    controller.insertAt((int)insertArgs.get(0), (Dragon)insertArgs.get(1));
                    save();
                    return new ServerCommand(ServerCommandType.INSERT, null);
                case IS_EMPTY:
                    return new ServerCommand(ServerCommandType.IS_EMPTY, new byte[]{(byte)(controller.isEmpty() ? 1 : 0)});
                case REMOVE:
                    controller.removeById(Utils.fromByteArray(args));
                    save();
                    return new ServerCommand(ServerCommandType.REMOVE, null);
                case REMOVE_GREATER:
                    controller.removeGreater((Dragon)Utils.deserializeObject(args));
                    save();
                    return new ServerCommand(ServerCommandType.REMOVE_GREATER, null);
                case SIZE:
                    return new ServerCommand(ServerCommandType.SIZE, Utils.intToBytes(controller.size()));
                case UPDATE:
                    ArrayList<Object> updateArgs = (ArrayList<Object>)Utils.deserializeObject(args);
                    controller.updateById((int)updateArgs.get(0), (Dragon)updateArgs.get(1));
                    save();
                    return new ServerCommand(ServerCommandType.UPDATE, null);
                case UPDATE_DATA:
                    return new ServerCommand(ServerCommandType.UPDATE_DATA, Utils.serializeObject(controller.getCollection()));
                default:
                    return new ServerCommand(ServerCommandType.ERROR, Utils.serializeObject("Неизвестная команда для сервера"));
            }
        } catch(Exception ex) {
            return new ServerCommand(ServerCommandType.ERROR, Utils.serializeObject(ex.getMessage()));
        }
    }

    public static boolean available(int port) {
        if (port < 1023 || port > 65534) {
            return false;
        }

        ServerSocket ss = null;
        DatagramSocket ds = null;
        try {
            ss = new ServerSocket(port);
            ss.setReuseAddress(true);
            ds = new DatagramSocket(port);
            ds.setReuseAddress(true);
            return true;
        } catch (IOException e) {
        } finally {
            if (ds != null) {
                ds.close();
            }

            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    /* should not be thrown */
                }
            }
        }

        return false;
    }

    public static List<byte[]> splitByteArray(byte[] source) {
        int maxChunkSize = PACKET_SIZE;
        if (source.length <= maxChunkSize) {
            return Collections.singletonList(source);
        }
        int numChunks = (int) Math.ceil((double) source.length / maxChunkSize);
        List<byte[]> chunks = new ArrayList<>(numChunks);

        int offset = 0;
        for (int i = 0; i < numChunks; i++) {
            int chunkSize = Math.min(maxChunkSize, source.length - offset);
            byte[] chunk = Arrays.copyOfRange(source, offset, offset + chunkSize);
            chunks.add(chunk);
            offset += chunkSize;
        }

        return chunks;
    }
    
    public boolean save() throws IOException {
        try (BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path.toString())))) {
            JsonCollectionControllerRepr.write(fileWriter, controller);
            System.out.println("Successfully saved");
            fileWriter.flush();
        } catch (IOException e) {}
        return true;
    }
}
