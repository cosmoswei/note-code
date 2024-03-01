package com.wei.netty.udp.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DeServer {
    public static void main(String[] args) {
        EventLoopGroup tcpGroup = new NioEventLoopGroup();
        EventLoopGroup udpGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap tcpBootstrap = new ServerBootstrap();
            ChannelFuture sync1 = tcpBootstrap.group(tcpGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new MyServerHandler());
                        }
                    })
                    .bind(8082)
                    .sync();

            Bootstrap udpBootstrap = new Bootstrap();
            ChannelFuture sync = udpBootstrap.group(udpGroup)
                    .channel(NioDatagramChannel.class)
                    .handler(new MyServerHandler())
                    .bind(8081)
                    .sync();

            // 其他逻辑处理...

            // 关闭服务器
            sync1.channel().closeFuture().sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            tcpGroup.shutdownGracefully();
            udpGroup.shutdownGracefully();
        }

    }
}
