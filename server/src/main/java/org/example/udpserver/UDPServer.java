package org.example.udpserver;

import org.example.src.App;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

    public static void run() throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(23586); //9876

        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String clientRequest = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received from client: " + clientRequest);
            String file = clientRequest.split("!!")[0];
            String command = clientRequest.split("!!")[1];

            System.out.println("Received from client: " + clientRequest.split("!!")[0]);
            System.out.println("Received from client: " + clientRequest.split("!!")[1]);

            App app = new App(file, command);



            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); // инфа

            String response = "Server response: " + clientRequest + inFromUser.readLine();
            sendData = response.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
            serverSocket.close();
            break;
        }
    }
}