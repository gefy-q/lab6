//package org.example;
//
//import client.App;
//import common.network.requests.Request;
//import common.network.responses.Response;
//
//import com.google.common.primitives.Bytes;
//import org.apache.commons.lang3.SerializationUtils;
//import org.apache.logging.log4j.Logger;
//
//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.InetSocketAddress;
//import java.net.SocketAddress;
//import java.nio.ByteBuffer;
//import java.nio.channels.DatagramChannel;
//import java.util.Arrays;
//
//public class UDPClient {
//    private final int PACKET_SIZE = 1024;
//    private final int DATA_SIZE = PACKET_SIZE - 1;
//
//    private final DatagramChannel client;
//    private final InetSocketAddress addr;
//
//    private final Logger logger = App.logger;
//
//    public UDPClient(InetAddress address, int port) throws IOException {
//        this.addr = new InetSocketAddress(address, port);
//        this.client = DatagramChannel.open().bind(null).connect(addr);
//        this.client.configureBlocking(false);
//        logger.info("DatagramChannel подключен к " + addr);
//    }
//
//    public Response sendAndReceiveCommand(Request request) throws IOException {
//        var data = SerializationUtils.serialize(request);
//        var responseBytes = sendAndReceiveData(data);
//
//        Response response = SerializationUtils.deserialize(responseBytes);
//        logger.info("Получен ответ от сервера:  " + response);
//        return response;
//    }
//
//    private void sendData(byte[] data) throws IOException {
//        byte[][] ret = new byte[(int)Math.ceil(data.length / (double)DATA_SIZE)][DATA_SIZE];
//
//        int start = 0;
//        for(int i = 0; i < ret.length; i++) {
//            ret[i] = Arrays.copyOfRange(data, start, start + DATA_SIZE);
//            start += DATA_SIZE;
//        }
//
//        logger.info("Отправляется " + ret.length + " чанков...");
//
//        for(int i = 0; i < ret.length; i++) {
//            var chunk = ret[i];
//            if (i == ret.length - 1) {
//                var lastChunk = Bytes.concat(chunk, new byte[]{1});
//                client.send(ByteBuffer.wrap(lastChunk), addr);
//                logger.info("Последний чанк размером " + lastChunk.length + " отправлен на сервер.");
//            } else {
//                var answer = Bytes.concat(chunk, new byte[]{0});
//                client.send(ByteBuffer.wrap(answer), addr);
//                logger.info("Чанк размером " + answer.length + " отправлен на сервер.");
//            }
//        }
//
//        logger.info("Отправка данных завершена.");
//    }
//
//    private byte[] receiveData() throws IOException {
//        var received = false;
//        var result = new byte[0];
//
//        while(!received) {
//            var data = receiveData(PACKET_SIZE);
//            logger.info("Получено \"" + new String(data) + "\"");
//            logger.info("Последний байт: " + data[data.length - 1]);
//
//            if (data[data.length - 1] == 1) {
//                received = true;
//                logger.info("Получение данных окончено");
//            }
//            result = Bytes.concat(result, Arrays.copyOf(data, data.length - 1));
//        }
//
//        return result;
//    }
//
//    private byte[] receiveData(int bufferSize) throws IOException {
//        var buffer = ByteBuffer.allocate(bufferSize);
//        SocketAddress address = null;
//        while(address == null) {
//            address = client.receive(buffer);
//        }
//        return buffer.array();
//    }
//
//    private byte[] sendAndReceiveData(byte[] data) throws IOException {
//        sendData(data);
//        return receiveData();
//    }
//}

package org.example.udpclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

    public static void run() throws Exception {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getByName("localhost");
        int port = 23586; //9876

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        String sentence = inFromUser.readLine();
        String sentence2 = inFromUser.readLine();
        sendData = (sentence + "!!" + sentence2).getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("From server: " + serverResponse);

        clientSocket.close();
    }
}