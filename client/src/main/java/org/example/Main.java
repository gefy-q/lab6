package org.example;

import org.example.udpclient.UDPClient;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {



        try {
            UDPClient client = new UDPClient();
            client.run();
        } catch (IOException e) {
            System.out.println("Невозможно подключиться к серверу!");
        }
    }
}