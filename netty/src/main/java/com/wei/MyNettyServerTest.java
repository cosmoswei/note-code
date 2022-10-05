package com.wei;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 主程序类
 */
public class MyNettyServerTest {
    public static void main(String[] args) throws InterruptedException {
        /**
         * EventLoopGroup：事件循环组，是一个线程池，也是一个死循环，用于不断地接收用户请求；
         * serverGroup：用户监听及建立连接，并把每一个连接抽象为一个channel，最后再将连接交给clientGroup处理；
         * clientGroup：真正的处理连接
         */
        EventLoopGroup serverGroup = new NioEventLoopGroup();
        EventLoopGroup clientGroup = new NioEventLoopGroup();
        try {
            // 服务端启动时的初始化操作
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 1、将serverGroup和clientGroup注册到服务端的Channel上；
            // 2、注册一个服务端的初始化器MyNettyServerInitializer；
            // 3、该初始化器中的initChannel()方法会在连接被注册到Channel后立刻执行；
            // 5、最后将端口号绑定到8080；
            ChannelFuture channelFuture = serverBootstrap.group(serverGroup, clientGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyNettyServerInitializer()).bind(8080).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            serverGroup.shutdownGracefully();
            clientGroup.shutdownGracefully();
        }
    }
}
