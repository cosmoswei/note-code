package com.wei.jmx;

// MBean实现类
public class Hello implements HelloMBean {
    private final String name = "HelloMBean";
    private int cacheSize = 200;

    @Override
    public void sayHello() {
        System.out.println("Hello, world!");
    }

    @Override
    public int add(int x, int y) {
        return x + y;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCacheSize() {
        return cacheSize;
    }

    @Override
    public void setCacheSize(int size) {
        cacheSize = size;
        System.out.println("Cache size now " + cacheSize);
    }
}