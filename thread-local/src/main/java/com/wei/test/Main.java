package com.wei.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Configuration
public class Main {
    public static void main(String[] args) {
        UserUtils.setUserId("1");
        String userId = UserUtils.getUserId();
        System.out.println(userId);
        System.out.println(Thread.currentThread().getName());
        System.out.println(userId);
        CompletableFuture.runAsync(() -> {
            UserUtils.setUserId(userId);
            System.out.println(Thread.currentThread().getName());
            System.out.println(UserUtils.getUserId());
        });
    }
}