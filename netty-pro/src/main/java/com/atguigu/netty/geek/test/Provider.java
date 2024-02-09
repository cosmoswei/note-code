package com.atguigu.netty.geek.test;

import com.atguigu.netty.geek.registry.ServerInfo;
import com.atguigu.netty.geek.registry.ZookeeperRegistry;
import com.atguigu.netty.geek.transport.DemoRpcServer;
import com.demo.rpc.factory.BeanManager;
import org.apache.curator.x.discovery.ServiceInstance;

/**
 * Created on 2020-06-21
 */
public class Provider {
    public static void main(String[] args) throws Exception {
        // 创建DemoServiceImpl，并注册到BeanManager中
        BeanManager.registerBean("demoService", new DemoServiceImpl());
        // 创建ZookeeperRegistry，并将Provider的地址信息封装成ServerInfo
        // 对象注册到Zookeeper
        ZookeeperRegistry<ServerInfo> discovery = new ZookeeperRegistry<>();
        discovery.start();
        ServerInfo serverInfo = new ServerInfo("127.0.0.1", 20880);
        discovery.registerService(
                ServiceInstance.<ServerInfo>builder().name("demoService").payload(serverInfo).build());
        // 启动DemoRpcServer，等待Client的请求
        DemoRpcServer rpcServer = new DemoRpcServer(20880);
        rpcServer.start();
        System.out.println("rpcServer = " + rpcServer);
        Thread.sleep(100000000L);
    }
}