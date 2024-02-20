package com.atguigu.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            // 创建ServerSocket并绑定端口
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Server started, waiting for client...");

            // 监听客户端连接
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            // 获取输入流和输出流
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            // 读取客户端发送的数据
            byte[] buffer = new byte[1024];
            int length = inputStream.read(buffer);
            String message = new String(buffer, 0, length);
            System.out.println("Received message from client: " + message);

            // 向客户端发送数据
            String response = "Hello, client! TCP！";
            outputStream.write(response.getBytes());

            // 关闭连接
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
