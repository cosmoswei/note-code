package com.wei;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        Thread thread = Thread.startVirtualThread(runnable);
        System.out.println(thread.getName());

        List<String> list = Stream.of("").toList();
//        list.stream().takeWhile()
        ExecutorService executorService = Executors.newCachedThreadPool();
    }
}