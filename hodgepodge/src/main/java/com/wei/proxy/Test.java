package com.wei.proxy;

import java.lang.reflect.Proxy;

public class Test {

    // 静态代理与代理的接口一一对应，且写死了
    // d动态代理可以动态的指导实现什么接口
    public static void main(String[] args) {
        main1(args);
        main2(args);
        main3(args);
    }

    // 测试代码
    public static void main1(String[] args) {
        UserService userServiceA = new UserServiceImplA();
        UserService userServiceProxyA = (UserService) Proxy.newProxyInstance(
                userServiceA.getClass().getClassLoader(),
                userServiceA.getClass().getInterfaces(),
                new DynamicProxyHandler(userServiceA)
        );
        userServiceProxyA.addUser("Alice");

        UserService userServiceB = new UserServiceImplB();
        UserService userServiceProxyB = (UserService) Proxy.newProxyInstance(
                userServiceB.getClass().getClassLoader(),
                userServiceB.getClass().getInterfaces(),
                new DynamicProxyHandler(userServiceB)
        );
        userServiceProxyB.addUser("Bob");
    }

    public static void main2(String[] args) {
        UserService userServiceA = new UserServiceImplA();
        UserServiceProxy proxyA = new UserServiceProxy(userServiceA);
        proxyA.addUser("Alice");

        UserService userServiceB = new UserServiceImplB();
        UserServiceProxy proxyB = new UserServiceProxy(userServiceB);
        proxyB.addUser("Bob");
    }

    public static void main3(String[] args) {
        AnimalService animalService = new AnimalServiceImplA();
        AnimalService newProxyInstance = (AnimalService) Proxy.newProxyInstance(
                animalService.getClass().getClassLoader(),
                animalService.getClass().getInterfaces(),
                new DynamicProxyHandler(animalService)
        );
        newProxyInstance.addUser("Dog");
    }
}