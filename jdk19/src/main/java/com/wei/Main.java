package com.wei;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        Thread thread = Thread.startVirtualThread(runnable);
        System.out.println(thread.getName());

        ExecutorService executorService = Executors.newCachedThreadPool();
    }
}