package com.wei.benchmark;

import com.github.benmanes.caffeine.cache.*;
import com.google.common.testing.FakeTicker;
import com.wei.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class CaffeineCacheTest {

    public static void main(String[] args) throws Exception {
//        main1(args);
//        main2(args);
//        main3(args);
//        main4(args);
//        main5(args);
//        main8(args);
//        main10(args);
//        main11(args);
//        main12(args);
//        main13(args);
//        main14(args);
        main15(args);
    }

    public static void main1(String[] args) {
        Cache<Integer, Integer> loadingCache = Caffeine.newBuilder().build();
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            loadingCache.put(i, i);
        }
        Long writeFinishTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            loadingCache.getIfPresent(i);
        }
        Long readFinishTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            loadingCache.invalidate(i);
        }
        Long deleteFinishTime = System.currentTimeMillis();
        System.out.println("CaffeineCache存用时：" + (writeFinishTime - start));
        System.out.println("CaffeineCache读用时：" + (readFinishTime - writeFinishTime));
        System.out.println("CaffeineCache删用时：" + (deleteFinishTime - readFinishTime));
    }

    public static void main2(String[] args) {
        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10_000)
                .build();
        String key = "test";
        String res = cache.get(key, k -> createValue(key));
        System.out.println(res);
    }

    // 模拟从外部数据源加载数据的逻辑
    static String createValue(String key) {
        //模拟异常情况
        int a = 1 / 0;
        return "";
    }

    public static void main3(String[] args) {
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) {
                        return getValue(key);
                    }
                });

        String value = cache.get("key");
        System.out.println(value);
    }

    // 模拟从外部数据源加载数据的逻辑
    private static String getValue(String key) {
        // 实际情况下，这里会有从数据库、远程服务加载数据的逻辑
        return "value";
    }

    public static void main4(String[] args) {
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                .build(key -> getValue(key));

        String value = cache.get("key");
        System.out.println(value);
    }

    public static void main5(String[] args) throws Exception {
        CacheLoader loader = new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                return getValue(s);
            }

            @Override
            public Map<String, String> loadAll(Iterable<? extends String> keys) throws Exception {
                Map currentMap = new HashMap<String, String>();
                for (String key : keys) {
                    currentMap.put(key, getValue(key));
                }
                return currentMap;
            }
        };

        loader.load("key1");
        loader.loadAll(new ArrayList(Arrays.asList("key2", "key3")));
        LoadingCache<String, String> cache = Caffeine.newBuilder().build(loader);
        String value = cache.get("key1");
        String value2 = cache.get("key2");
        System.out.println(value + value2);
    }

    public static void main6(String[] args) {
        AsyncCache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(10_000).buildAsync();
        String key = "test";
        CompletableFuture<String> res = cache.get(key, k -> getValue(key));
        res.thenAccept(result -> System.out.println(result));
    }

//    public static void main7(String[] args) throws Exception {
//        Executor executor = Executors.newFixedThreadPool(5);
//        AsyncLoadingCache<String, String> cache = Caffeine.newBuilder()
//                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .maximumSize(10_000)
//                //异步的封装一段同步操作来生成缓存元素
//                .buildAsync(key -> getValue(key))
//                //OR建一个异步缓存元素操作并返回一个future
//                .buildAsync((key,executor1) -> getValue(key,executor));
//        String key = "test";
//        CompletableFuture<String> res = cache.get(key);
//        res.thenAccept(result -> System.out.println(result));
//    }

    // 模拟从外部数据源加载数据的逻辑
    private static CompletableFuture<String> getValue(String key, Executor executor) {
        return CompletableFuture.supplyAsync(() -> "value for " + key, executor);
    }

    public static void main8(String[] args) {
        Cache<String, Product> cache = Caffeine.newBuilder()
                .maximumWeight(1000)
                .weigher((String key, Product value) -> value.getWeight())
                //使用当前线程进行驱逐和刷新
                .executor(runnable -> runnable.run())
                //监听器，如果有元素被驱逐则会输出
                .removalListener(((key, value, cause) -> {
                    System.out.printf("Key %s was evicted (%s)%n", key, cause);
                }))
                .build();
        // 向缓存中添加商品信息
        cache.put("product1", new Product("Product 1", 200));
        cache.put("product2", new Product("Product 2", 400));
        cache.put("product3", new Product("Product 3", 500));
        // 获取缓存中的商品信息
        System.out.println(cache.getIfPresent("product1"));
        System.out.println(cache.getIfPresent("product2"));
        System.out.println(cache.getIfPresent("product3"));
    }

    public static void main9(String[] args) {
        // 基于固定的过期时间驱逐策略
//        LoadingCache<Key, Graph> graphs = Caffeine.newBuilder()
//                .expireAfterAccess(5, TimeUnit.MINUTES)
//                .build(key -> createExpensiveGraph(key));
//        LoadingCache<Key, Graph> graphs = Caffeine.newBuilder()
//                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .build(key -> createExpensiveGraph(key));
//
//        // 基于不同的过期驱逐策略
//        LoadingCache<Key, Graph> graphs = Caffeine.newBuilder()
//                .expireAfter(new Expiry<Key, Graph>() {
//                    public long expireAfterCreate(Key key, Graph graph, long currentTime) {
//                        // Use wall clock time, rather than nanotime, if from an external resource
//                        long seconds = graph.creationDate().plusHours(5)
//                                .minus(System.currentTimeMillis(), MILLIS)
//                                .toEpochSecond();
//                        return TimeUnit.SECONDS.toNanos(seconds);
//                    }
//
//                    public long expireAfterUpdate(Key key, Graph graph,
//                                                  long currentTime, long currentDuration) {
//                        return currentDuration;
//                    }
//
//                    public long expireAfterRead(Key key, Graph graph,
//                                                long currentTime, long currentDuration) {
//                        return currentDuration;
//                    }
//                })
//                .build(key -> createExpensiveGraph(key));
    }

    public static void main10(String[] args) {
        //模拟时间，使用的com.google.common.testing.FakeTicker;
        FakeTicker ticker = new FakeTicker();
        Cache<String, String> cache = Caffeine.newBuilder()
                //创建20分钟后元素被删除
                .expireAfterWrite(20, TimeUnit.MINUTES)
                //没有读取10分钟后元素被删除
                .expireAfterAccess(10, TimeUnit.MINUTES)
                .executor(Runnable::run)
                .ticker(ticker::read)
                .build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        ticker.advance(5, TimeUnit.MINUTES);
        System.out.println("5分钟都不删除，访问一次key2：" + cache.getIfPresent("key2"));
        ticker.advance(5, TimeUnit.MINUTES);
        System.out.println("10分钟key1被删除，因为它已经10分钟没有被访问过了：" + cache.getIfPresent("key1"));
        System.out.println("10分钟key2没有被删除，因为它在5分钟时被访问过了:" + cache.getIfPresent("key2"));
        ticker.advance(10, TimeUnit.MINUTES);
        System.out.println("20分钟key2也被删除:" + cache.getIfPresent("key2"));
    }

    public static void main11(String[] args) {
        //模拟时间，使用的com.google.common.testing.FakeTicker;
        FakeTicker ticker = new FakeTicker();
        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfter(new Expiry<String, String>() {
                    public long expireAfterCreate(String key, String value, long currentTime) {
                        // 在创建后的24小时后过期
                        return TimeUnit.HOURS.toNanos(24);
                    }

                    public long expireAfterUpdate(String key, String value, long currentTime, long currentDuration) {
                        // 在更新后如果值为"1234"，则立马过期
                        if ("1234".equals(value)) {
                            return 0;
                        }
                        // 在更新后的1小时后过期
                        return TimeUnit.HOURS.toNanos(1);
                    }

                    public long expireAfterRead(String key, String value, long currentTime, long currentDuration) {
                        // 在读取后的20小时后过期
                        return TimeUnit.HOURS.toNanos(20);
                    }
                })
                .executor(Runnable::run)
                .ticker(ticker::read)
                .build();
        cache.put("AfterCreateKey", "AfterCreate");
        cache.put("AfterUpdate1234Key", "1234key");
        cache.put("AfterUpdateKey", "AfterUpdate");
        cache.put("AfterReadKey", "AfterRead");
        //AfterUpdate1234Key值更新为1234
        cache.put("AfterUpdate1234Key", "1234");
        System.out.println("AfterUpdate1234Key在更新后值为1234，立马过期：" + cache.getIfPresent("AfterUpdate1234Key"));
        System.out.println("AfterReadKey读取一次：" + cache.getIfPresent("AfterReadKey"));
        //AfterUpdateKey更新一次
        cache.put("AfterUpdateKey", "AfterUpdate");
        ticker.advance(1, TimeUnit.HOURS);
        System.out.println("AfterUpdateKey更新了一个小时了，被删除：" + cache.getIfPresent("AfterUpdateKey"));
        ticker.advance(19, TimeUnit.HOURS);
        System.out.println("AfterReadKey再读取一次已经删除了，因为上一次读取已经过了20小时：" + cache.getIfPresent("AfterReadKey"));
        ticker.advance(4, TimeUnit.HOURS);
        System.out.println("AfterCreateKey被删除了，距离创建已经24小时了：" + cache.getIfPresent("AfterCreateKey"));
    }


    public static void main12(String[] args) {
        Cache<String, String> cache = Caffeine.newBuilder()
                .maximumSize(2)
                .executor(Runnable::run)
                //驱逐，删除key时会输出
                .removalListener(((key, value, cause) -> {
                    System.out.printf("removalListener—>Key %s was evicted (%s)%n", key, cause);
                }))
                //驱逐key时会输出
                .evictionListener(((key, value, cause) -> {
                    System.out.printf("evictionListener->Key %s was evicted (%s)%n", key, cause);
                }))
                .build();
        // 向缓存中添加商品信息
        cache.put("product1", "product1");
        cache.put("product2", "product2");
        cache.put("product3", "product3");
        // 获取缓存中的商品信息
        System.out.println(cache.getIfPresent("product1"));
        System.out.println(cache.getIfPresent("product2"));
        System.out.println(cache.getIfPresent("product3"));
        cache.invalidateAll();
    }

    public static void main13(String[] args) {

        FakeTicker ticker = new FakeTicker();
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                .refreshAfterWrite(5, TimeUnit.SECONDS) // 在写入后5秒钟自动刷新
                .ticker(ticker::read)
                .executor(Runnable::run)
                .build(key -> getVale(key)); // 提供加载方法
        System.out.println("Initial value for key1: " + cache.get("key1"));
        // 超过自动刷新时间
        ticker.advance(7, TimeUnit.SECONDS);
        System.out.println(cache.get("key1")); // 真正执行刷新
        System.out.println(cache.get("key1")); // 输出自动刷新后的值
    }

    private static String getVale(String key) {
        // 这里简单地返回一个当前时间的字符串
        return "loaded value for " + key + " at " + System.currentTimeMillis();
    }

    public static void main14(String[] args) {
        LoadingCache<String, String> cache = Caffeine.newBuilder().build(key -> getVale(key));
        System.out.println("Initial value for key1: " + cache.get("key1"));
        cache.refresh("key1");
        System.out.println(cache.get("key1"));
    }


    public static void main15(String[] args) {
        FakeTicker ticker = new FakeTicker();
        LoadingCache<String, String> cache = Caffeine.newBuilder().refreshAfterWrite(5, TimeUnit.SECONDS).ticker(ticker::read).build(new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                return getVale(s);
            }

            @Override
            public String reload(String s, String v) {
                return getVale(s) + "|" + v;
            }
        });
        System.out.println("Initial value for key1: " + cache.get("key1"));
        ticker.advance(7, TimeUnit.SECONDS);
        cache.get("key1");
        System.out.println(cache.get("key1"));
    }
}