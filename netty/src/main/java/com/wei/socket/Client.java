package com.wei.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            // 创建Socket并连接服务器
            Socket socket = new Socket("localhost", 8888);
            System.out.println("Connected to server: " + socket.getInetAddress());

            // 获取输入流和输出流
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // 向服务器发送数据
            String message = "Hello, server! TCP!";
            outputStream.write(message.getBytes());

            // 读取服务器返回的数据
            byte[] buffer = new byte[1024];
            int length = inputStream.read(buffer);
            String response = new String(buffer, 0, length);
            System.out.println("Received message from server: " + response);

            // 关闭连接
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
