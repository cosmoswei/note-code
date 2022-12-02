package com.wei.chapter11;

import com.wei.entity.Shop;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CompletableFutureExplore {


    public static void main(String[] args) {
        long start = System.nanoTime();
        CompletableFuture[] myPhones = findPriceV2("myPhone").map(f -> f.thenAccept(s -> {
            System.out.println("Done in " + (System.nanoTime() - start) / 1_000_000 + " msecs");
        })).toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(myPhones).join();
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("All of shops have now responded in " + duration + " msecs");
    }


    //1063925042
    static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetSaveBig1"),
            new Shop("LetSaveBig2"),
            new Shop("LetSaveBig3"),
            new Shop("LetSaveBig4"),
            new Shop("LetSaveBig5"),
            new Shop("MyFirstFavoriteShop"),
            new Shop("MySecondFavoriteShop"),
            new Shop("MyLastFavoriteShop"),
            new Shop("BuyItAll"));

    static List<String> findPrice(String product) {
        return shops.stream().map(shop -> String.format("%s price is %s", shop.getShopName(), shop.getPrice(product)))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(toList());
    }

    static Stream<CompletableFuture<String>> findPriceV2(java.lang.String product) {

        ExecutorService executorService = Executors.newFixedThreadPool(Math.min(shops.size(), 100), r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
        return shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executorService))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(quote), executorService)));
    }

    static List<String> findPriceParallelStream(String product) {
        return shops.parallelStream().map(shop -> String.format("%s price is %.2f",
                        shop.getShopName(), shop.getPrice(product)))
                .collect(toList());
    }

    static List<String> findPriceCompletableFuture(String product) {

        ExecutorService executorService = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
        });

        List<CompletableFuture<String>> collect = shops.stream().map(shop -> CompletableFuture.supplyAsync(() ->
                                String.format("%s price is %.2f", shop.getShopName(), shop.getPrice(product)),
                        executorService))
                .collect(toList());

        return collect.stream().map(CompletableFuture::join).collect(toList());
    }

    static List<String> findPriceCompletableFutureV2(String product) {

        ExecutorService executorService = Executors.newFixedThreadPool(Math.min(shops.size(), 100), r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

        List<CompletableFuture<String>> collect = shops.stream().map(shop -> CompletableFuture.supplyAsync(() ->
                                String.format("%s price is %s", shop.getShopName(), shop.getPrice(product)),
                        executorService))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executorService)))
                .collect(toList());

        return collect.stream().map(CompletableFuture::join).collect(toList());
    }
}
