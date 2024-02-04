package com.wei;

import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        ServiceLoader<HelloService> serviceLoader = ServiceLoader.load(HelloService.class);
        for (HelloService helloService : serviceLoader) {
            helloService.sayHello();
        }
    }
}
