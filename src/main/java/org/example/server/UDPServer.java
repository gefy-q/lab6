package org.example.server;

import java.io.*;
import java.net.*;

public class UDPServer {
    public static void main(String args[]) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);

        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String clientRequest = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received from client: " + clientRequest);

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); // инфа

            String response = "Server response: " + clientRequest.toUpperCase() + inFromUser.readLine();
            sendData = response.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }
}