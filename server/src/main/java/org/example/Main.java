package org.example;

import org.example.udpserver.UDPServer;

public class Main {
    public static void main(String[] args) throws Exception {

        UDPServer server = new UDPServer();
        server.run();
    }
}