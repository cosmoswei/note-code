package com.atguigu.netty.geek.proxy;

import com.atguigu.netty.geek.Constants;
import com.atguigu.netty.geek.protocol.Header;
import com.atguigu.netty.geek.protocol.Message;
import com.atguigu.netty.geek.protocol.Request;
import com.atguigu.netty.geek.registry.Registry;
import com.atguigu.netty.geek.registry.ServerInfo;
import com.atguigu.netty.geek.transport.Connection;
import com.atguigu.netty.geek.transport.DemoRpcClient;
import com.atguigu.netty.geek.transport.NettyResponseFuture;
import io.netty.channel.ChannelFuture;
import org.apache.curator.x.discovery.ServiceInstance;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static com.atguigu.netty.geek.Constants.MAGIC;
import static com.atguigu.netty.geek.Constants.VERSION_1;

public class DemoRpcProxy implements InvocationHandler {

    private String serviceName; // 需要代理的服务(接口)名称

    public Map<Method, Header> headerCache = new ConcurrentHashMap<>();

    // 用于与Zookeeper交互，其中自带缓存
    private Registry<ServerInfo> registry;

    public DemoRpcProxy(String serviceName,
                        Registry<ServerInfo> registry) {
        this.serviceName = serviceName;
        this.registry = registry;
    }

    public static <T> T newInstance(Class<T> clazz, Registry<ServerInfo> registry) throws Exception {
        // 创建代理对象
        // 获取的注册服务信息
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{clazz},
                new DemoRpcProxy("demoService", registry));
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 从Zookeeper缓存中获取可用的Server地址,并随机从中选择一个
        List<ServiceInstance<ServerInfo>> serviceInstances =
                registry.queryForInstances(serviceName);
        ServiceInstance<ServerInfo> serviceInstance =
                serviceInstances.get(ThreadLocalRandom.current().nextInt(serviceInstances.size()));
        // 创建请求消息，然后调用remoteCall()方法请求上面选定的Server端
        String methodName = method.getName();
        Header header = headerCache.computeIfAbsent(method, h -> new Header(MAGIC, VERSION_1));
        Message<Request> message = new Message(header, new Request(serviceName, methodName, args));
        return remoteCall(serviceInstance.getPayload(), message);
    }

    protected Object remoteCall(ServerInfo serverInfo, Message message) throws Exception {
        if (serverInfo == null) {
            throw new RuntimeException("get available server error");
        }
        Object result;
        try {
            // 创建DemoRpcClient连接指定的Server端
            DemoRpcClient demoRpcClient = new DemoRpcClient(serverInfo.getHost(), serverInfo.getPort());
            ChannelFuture channelFuture = demoRpcClient.connect().awaitUninterruptibly();
            // 创建对应的Connection对象，并发送请求
            Connection connection = new Connection(channelFuture, true);
            NettyResponseFuture responseFuture = connection.request(message, Constants.DEFAULT_TIMEOUT);
            // 等待请求对应的响应
            result = responseFuture.getPromise().get(Constants.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
}