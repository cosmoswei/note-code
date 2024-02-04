package com.wei.dubbo;


import com.wei.HelloService;
import org.apache.dubbo.common.extension.ExtensionLoader;

public class Provider {
    public static void main(String[] args) {
        ExtensionLoader<HelloService> extensionLoader = ExtensionLoader.getExtensionLoader(HelloService.class);
        HelloService helloService = extensionLoader.getDefaultExtension();
        helloService.sayHello();
    }
}
