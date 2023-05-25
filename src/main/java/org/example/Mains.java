package org.example;
import org.example.client.SimpleClientUDP;
import org.example.server.SimpleServerUDP;

import java.util.Scanner;

public class Mains {

    public static void main(String[] args) throws Exception {
        int port = 8080;

        SimpleClientUDP clientUDP = new SimpleClientUDP("localhost", port);
        SimpleServerUDP serverUDP = new SimpleServerUDP(port);

        {
            new Thread(serverUDP).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(clientUDP).start();
        };

        {
            Scanner in = new Scanner(System.in);
            String mes = in.nextLine();
            clientUDP.push(mes.getBytes());
            in.close();
        };
    }
}
