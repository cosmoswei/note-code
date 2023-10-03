package com.wei.demo;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;

public class EhCacheDemo {
    public static void main(String[] args) {
        //1. 构建核心组件，CacheManager
        CacheManager cacheManager = CacheManagerBuilder
                .newCacheManagerBuilder()
                // 设置缓存别名
                .withCache("cache1",
                        CacheConfigurationBuilder
                                .newCacheConfigurationBuilder(String.class,
                                        String.class,
                                        ResourcePoolsBuilder.heap(1)
                                                .offheap(1, MemoryUnit.GB)))  // 同时指定缓存的key-value类型，以及缓存容纳的个数

                .build();
        //2. 初始化走你
        cacheManager.init();

        //3. 获取Cache方式一。从缓存管理器拿到设置好的Cache
        Cache<String, String> cache1 = cacheManager.getCache("cache1", String.class, String.class);

        //4. 获取Cache方式二。 基于缓存管理器构建一个Cache
        Cache<String, String> cache2 = cacheManager.createCache("cache2", CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.heap(5)));

        //5. 操作
        cache2.put("cacheKey", "阿巴阿巴~~");
        Object value1 = cache2.get("cacheKey");
        System.out.println(value1);
    }
}
