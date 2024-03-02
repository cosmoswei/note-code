package com.wei.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        // 创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 创建一个ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8888));

        while (true) {
            // 接收客户端连接
            SocketChannel socketChannel = serverSocketChannel.accept();

            // 使用线程池处理客户端请求
            executorService.submit(() -> {
                try {
                    // 读取客户端发送的数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int bytesRead = socketChannel.read(buffer);
                    if (bytesRead == -1) {
                        socketChannel.close();
                        return;
                    }
                    buffer.flip();
                    String message = StandardCharsets.UTF_8.decode(buffer).toString();
                    System.out.println("message = " + message);
                    // 处理请求并返回响应
                    String response = "Echo: " + message;
                    ByteBuffer responseBuffer = StandardCharsets.UTF_8.encode(response);
                    socketChannel.write(responseBuffer);
                    // 关闭连接
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
