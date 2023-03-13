package com.wei.demo;


import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: kevin
 * @Date: 2019-04-12 09:57
 */
public class GetThreadInteger implements Runnable {
    AtomicReference<List<Integer>> resultInteger = new AtomicReference<>();

    @Override
    public void run() {
        resultInteger.set(getIntegers());
    }

    public static List<Integer> getIntegers() {
        List<Integer> list = Lists.newArrayList();
        for (int i = 100; i < 200; i++) {
            System.out.println(Thread.currentThread().getName() + "=====" + i);
            list.add(i);
        }
        return list;
    }
}






