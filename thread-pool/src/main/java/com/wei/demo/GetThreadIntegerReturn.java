package com.wei.demo;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.Callable;

public class GetThreadIntegerReturn implements Callable<List<Integer>> {
    @Override
    public List<Integer> call() {
        List<Integer> list = Lists.newArrayList();
        for (int i = 100; i < 200; i++) {
            System.out.println(Thread.currentThread().getName() + "=====" + i);
            list.add(i);
        }
        return list;
    }
}