package com.wei.demo;


import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Author: kevin
 * @Date: 2019-04-12 13:20
 */
public class GetThreadStringReturn implements Callable<List<String>> {
    @Override
    public List<String> call() {
        List<String> list = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "=====" + i);
            list.add(String.valueOf(i));
        }
        return list;
    }
}

