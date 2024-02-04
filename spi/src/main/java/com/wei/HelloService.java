package com.wei;


import org.apache.dubbo.common.extension.SPI;

@SPI
public interface HelloService {
    void sayHello();
}
