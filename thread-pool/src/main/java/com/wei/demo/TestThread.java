package com.wei.demo;

import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;

public class TestThread {

    public static void main(String[] args) {
        test1(args);
    }

    public static void test1(String[] args) {
        AtomicReference<List<String>> resultString = new AtomicReference<>();
        AtomicReference<List<Integer>> resultInteger = new AtomicReference<>();

        ThreadPool threadPool = ThreadPool.getThreadPool();

        try {
            /**
             * 无返回值的多线程
             */
            threadPool.executor(new GetThreadString());
            threadPool.executor(new GetThreadInteger());
            for (int i = 0; i < 100; i++) {
                System.out.println("========--------" + Thread.currentThread().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

        System.out.println(resultInteger.get().size());
        System.out.println(resultString.get().size());
    }

    public static void test2(String[] args) {
        AtomicReference<List<String>> resultString = new AtomicReference<>();
        AtomicReference<List<Integer>> resultInteger = new AtomicReference<>();

        ThreadPool threadPool = ThreadPool.getThreadPool();

        try {
            /**
             * 有返回值的多线程
             */
            FutureTask<List<String>> listStringFutureTask = new FutureTask(new GetThreadStringReturn());
            FutureTask<List<Integer>> listIntegerFutureTask = new FutureTask(new GetThreadIntegerReturn());
            threadPool.executor(listStringFutureTask);
            threadPool.executor(listIntegerFutureTask);

            resultString.set(listStringFutureTask.get());
            resultInteger.set(listIntegerFutureTask.get());

            for (int i = 0; i < 100; i++) {
                System.out.println("========--------" + Thread.currentThread().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
        System.out.println(resultInteger.get().size());
        System.out.println(resultString.get().size());
    }
}