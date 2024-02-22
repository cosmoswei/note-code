package com.wei.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AioClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        final AsynchronousSocketChannel clientChannel = AsynchronousSocketChannel.open();
        clientChannel.connect(new InetSocketAddress("localhost", 8888), null, new CompletionHandler<Void, Void>() {
            @Override
            public void completed(Void result, Void attachment) {
                String message = "Hello, server! AIO";
                ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
                clientChannel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {
                        if (attachment.hasRemaining()) {
                            clientChannel.write(attachment, attachment, this);
                        } else {
                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                            clientChannel.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                                @Override
                                public void completed(Integer result, ByteBuffer attachment) {
                                    attachment.flip();
                                    String response = new String(attachment.array(), 0, result);
                                    System.out.println("Received response: " + response);
                                }

                                @Override
                                public void failed(Throwable exc, ByteBuffer attachment) {
                                    // Handle error
                                }
                            });
                        }
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {
                        // Handle error
                    }
                });
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                // Handle error
            }
        });

        Thread.sleep(Integer.MAX_VALUE);
    }
}
