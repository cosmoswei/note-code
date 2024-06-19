package com.wei;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class CaffeineTest {
    public static void main(String[] args) {
        test1(args);
    }


    public static void test1(String[] args) {
        Cache<String, String> cache = Caffeine.newBuilder()
                //过期时间
                .expireAfterWrite(10, TimeUnit.MINUTES)
                //最大容量
                .maximumSize(10_000)
                .build();
        String key = "test";
        // 查找一个缓存元素， 没有查找到的时候返回null
        String res = cache.get(key, k -> createValue(key));
        // 添加或者更新一个缓存元素
        cache.put(key, "testValue");
        // 移除一个缓存元素
        cache.invalidate(key);
    }

    // 模拟从外部数据源加载数据的逻辑
    static String createValue(String key) {
        return "value";
    }
}
