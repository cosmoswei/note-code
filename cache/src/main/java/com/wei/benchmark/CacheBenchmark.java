package com.wei.benchmark;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheBenchmark {

    public static void main(String[] args) {
        long times = 10_000_000;
        guavaCacheBenchmark(times);
        caffeineCacheBenchmark(times);
    }
    public static void guavaCacheBenchmark(long times) {
        Cache<Integer, Integer> loadingCache = CacheBuilder.newBuilder().build();
        Long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            loadingCache.put(i, i);
        }
        Long writeFinishTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            loadingCache.getIfPresent(i);
        }
        Long readFinishTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            loadingCache.invalidate(i);
        }
        Long deleteFinishTime = System.currentTimeMillis();
        System.out.println("GuavaCache存用时：" + (writeFinishTime - start));
        System.out.println("GuavaCache取用时：" + (readFinishTime - writeFinishTime));
        System.out.println("GuavaCache删用时：" + (deleteFinishTime - readFinishTime));
    }

    public static void caffeineCacheBenchmark(long times) {
        com.github.benmanes.caffeine.cache.Cache<Integer, Integer> loadingCache = Caffeine.newBuilder().build();
        Long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            loadingCache.put(i, i);
        }
        Long writeFinishTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            loadingCache.getIfPresent(i);
        }
        Long readFinishTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            loadingCache.invalidate(i);
        }
        Long deleteFinishTime = System.currentTimeMillis();
        System.out.println("CaffeineCache存用时：" + (writeFinishTime - start));
        System.out.println("CaffeineCache读用时：" + (readFinishTime - writeFinishTime));
        System.out.println("CaffeineCache删用时：" + (deleteFinishTime - readFinishTime));
    }
}