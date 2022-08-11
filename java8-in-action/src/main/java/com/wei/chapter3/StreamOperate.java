package com.wei.chapter3;

import com.wei.entity.Person;
import com.wei.entity.Shop;
import com.wei.entity.User;
import com.wei.utils.UserInitializationUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Slf4j
public class StreamOperate {
    public static void main(String[] args) {
        Shop shop = new Shop();
        long l = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsyncV2("my favorite product");

        long l1 = (System.nanoTime() - l) / 1_000_000;
        System.out.println("l1 = " + l1);

        try {
            Double aDouble = futurePrice.get(2, TimeUnit.SECONDS);
            System.out.println("aDouble = " + aDouble);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        long l2 = (System.nanoTime() - l) / 1_000_000;
        System.out.println("l2 = " + l2);

    }

    private static void mapOperate() {
        List<Integer> integers1 = Arrays.asList(1, 2, 3);
        List<Integer> integers2 = Arrays.asList(3, 4, 5, 6);

        List<int[]> collect = integers1.stream().flatMap(i ->
                integers2.stream().filter(j -> (i * j) % 3 == 0).map(j -> new int[]{i, j})
        ).collect(toList());

        collect.forEach(i -> Arrays.stream(i).asLongStream().forEachOrdered(System.out::println));
    }
}
