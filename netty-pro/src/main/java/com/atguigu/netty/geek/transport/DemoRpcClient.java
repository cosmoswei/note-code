package com.atguigu.netty.geek.transport;

import com.atguigu.netty.geek.Constants;
import com.atguigu.netty.geek.codec.DemoRpcDecoder;
import com.atguigu.netty.geek.codec.DemoRpcEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.Closeable;

public class DemoRpcClient implements Closeable {

    protected Bootstrap clientBootstrap;
    protected EventLoopGroup group;
    private String host;
    private int port;

    public DemoRpcClient(String host, int port) {
        this.host = host;
        this.port = port;
        // 创建并配置客户端Bootstrap
        clientBootstrap = new Bootstrap();
        group = NettyEventLoopFactory.eventLoopGroup(Constants.DEFAULT_IO_THREADS, "NettyClientWorker");
        clientBootstrap.group(group)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .channel(NioSocketChannel.class) // 创建的Channel类型
                // 指定ChannelHandler的顺序
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("demo-rpc-encoder", new DemoRpcEncoder());
                        ch.pipeline().addLast("demo-rpc-decoder", new DemoRpcDecoder());
                        ch.pipeline().addLast("client-handler", new DemoRpcClientHandler());
                    }
                });
    }


    public ChannelFuture connect() {
        // 连接指定的地址和端口
        ChannelFuture connect = clientBootstrap.connect(host, port);
        connect.awaitUninterruptibly();
        return connect;
    }

    @Override
    public void close() {
        group.shutdownGracefully();
    }
}
