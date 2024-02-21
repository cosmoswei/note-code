package com.atguigu.netty.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

public class HTTPServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            // 获取请求的URI和方法
            String uri = request.uri();
            HttpMethod method = request.method();
            // 判断请求是否是favicon.ico，如果是则忽略
            if (uri.equals("/favicon.ico")) {
                return;
            }
            // 根据不同的URI返回不同的内容
            String content = "";
            if (uri.equals("/hello")) {
                content = "Hello! ";
            } else if (uri.equals("/world")) {
                content = "World!";
            } else {
                content = "Page not found!";
            }
            System.out.println("uri = " + uri);
            // 构建HTTP响应
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
            response.content().writeBytes(content.getBytes());
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
            // 返回HTTP响应
            ctx.writeAndFlush(response);
        }
    }
}
