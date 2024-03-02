package com.wei.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        try {
            // 创建DatagramSocket
            DatagramSocket socket = new DatagramSocket();

            // 发送数据
            String message = "Hello, server!";
            byte[] sendData = message.getBytes();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 8888;
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            socket.send(sendPacket);

            // 接收数据
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received message from server: " + response);

            // 关闭Socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
