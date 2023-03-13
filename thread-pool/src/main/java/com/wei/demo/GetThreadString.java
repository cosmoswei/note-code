package com.wei.demo;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: kevin
 * @Date: 2019-04-12 09:57
 */
public class GetThreadString implements Runnable {
    AtomicReference<List<String>> resultString = new AtomicReference<>();
    @Override
    public void run() {
        resultString.set(getStrings());
    }

    public List<String> getStrings() {
        List<String> list = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "=====" + i);
            list.add(String.valueOf(i));
        }
        return list;
    }
}