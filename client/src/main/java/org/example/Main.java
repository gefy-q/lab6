package org.example;

import org.example.udpclient.UDPClient;

public class Main {
    public static void main(String[] args) throws Exception {

        UDPClient client = new UDPClient();
        client.run();
    }
}