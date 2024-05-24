package com.wei;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncConsolePrinter {

    private static final ExecutorService executor = Executors.newFixedThreadPool(20);

    public static void printAsync(final String message) {
        executor.submit(() -> System.out.println(message));
    }

    // 记得在程序结束时关闭线程池
    public static void shutdown() {
        executor.shutdown();
    }

    // 以下是一个简单的测试方法
    public static void main(String[] args) {
        printAsync("Hello, this is an asynchronous message!");
        printAsync("Another asynchronous message!");

        // 程序结束前关闭线程池
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down the executor service.");
            shutdown();
        }));
    }
}