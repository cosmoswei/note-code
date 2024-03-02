package com.wei.netty.geek.test;


import com.wei.netty.geek.proxy.DemoRpcProxy;
import com.wei.netty.geek.registry.ServerInfo;
import com.wei.netty.geek.registry.ZookeeperRegistry;

public class Consumer {
    public static void main(String[] args) throws Exception {
        // 创建 ZookeeperRegistry 对象
        ZookeeperRegistry<ServerInfo> discovery = new ZookeeperRegistry<>();
        discovery.start();
        // 创建代理对象，通过代理调用远端Server
        DemoService demoService = DemoRpcProxy.newInstance(DemoService.class, discovery);
        // 调用sayHello()方法，并输出结果
        String result = demoService.sayHello("hello");
        System.out.println("result = " + result);
        Thread.sleep(10000000L);
    }
}