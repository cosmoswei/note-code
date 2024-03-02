package com.wei.netty.geek.test;

public class DemoServiceImpl implements DemoService {
    public String sayHello(String param) {
        System.out.println("param" + param);
        return "hello:" + param;
    }
}