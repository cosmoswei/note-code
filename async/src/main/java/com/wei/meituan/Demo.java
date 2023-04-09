package com.wei.meituan;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("do something 1");
            return "result 1";
        }, executorService);
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("do something 2");
            return "result 2";
        });

        cf1.thenCombine(cf2, (result1, result2) -> {
            System.out.println(result1 + ", " + result2);
            System.out.println("do something3");
            return "result3";
        }).thenAccept(System.out::println);
        CompletableFuture<Void> cf5 = CompletableFuture.allOf(cf1, cf2);
        CompletableFuture<Void> cf6 = CompletableFuture.allOf(cf1, cf2);
        CompletableFuture<String> cf7 = CompletableFuture.anyOf(cf5, cf6).thenApply(v -> {
            String result1 = cf1.join();
            String result2 = cf2.join();
            return result1 + ", " + result2;
        });
        System.out.println();
        cf7.thenAccept(System.out::println);
    }
}
