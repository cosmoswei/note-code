package com.wei.limit.limiter.impl;


import com.wei.limit.limiter.DTO.LimiterDTO;
import com.wei.limit.limiter.LimiterAbstract;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author: Xhy
 * CreateTime: 2023-03-08 08:51
 * 计数器限流
 */
public class CounterLimiter extends LimiterAbstract {

    private Map<String, Data> map = new HashMap<>();
    private BlockingQueue<DataDelay> delayQueue = new DelayQueue<>();
    private boolean state = false;

    private static CounterLimiter instance;

    public static CounterLimiter getInstance() {
        if (instance == null) {
            instance = new CounterLimiter();
        }
        return instance;
    }

    private CounterLimiter() {
        init();
    }

    @Override
    public void set(String key, Integer value, long time) {
        map.put(key, new Data(value, time));
        delayQueue.add(new DataDelay(key, time));
    }

    @Override
    public Integer get(String key) {
        return map.get(key).value.get();
    }

    @Override
    public void remove(String key) {
        map.remove(key);
        delayQueue.remove(new DataDelay(key));
    }

    /**
     * 自增,不存在则添加
     *
     * @param key
     */
    @Override
    public void incr(String key, long time) {
        if (map.containsKey(key)) {
            map.get(key).incr();
        } else {
            set(key, 1, time);
        }
    }


    /**
     * 达到限制让用户time期间内不允许访问
     * 如果用户执意访问则延续time期间
     *
     * @param restrictDTO
     * @return
     */
    @Override
    public boolean check(LimiterDTO restrictDTO) {
        // 此处key应该为请求路径+userId。此处是为了测试方便
        String key = restrictDTO.key;
        int limit = restrictDTO.limit;
        int time = restrictDTO.time;
        if (!map.containsKey(key)) {
            set(key, 1, time);
            return false;
        }
        Data data = map.get(key);
        boolean res = data.value.get() >= limit;
        if (res) {
            removeDelayKey(key);
            addDelay(key, data.time);
        }
        incr(key, time);
        return res;
    }

    private void removeDelayKey(String key) {
        delayQueue.remove(new DataDelay(key));
    }

    private void addDelay(String key, long time) {
        delayQueue.add(new DataDelay(key, time));
    }

    // 初始化延迟队列
    private void init() {
        new Thread(() -> {
            while (true) {
                try {
                    DataDelay take = delayQueue.take();
                    map.remove(take.key);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CounterLimiter").start();
    }

    // 封装数据
    private class Data {
        AtomicInteger value;
        long time;

        public Data(Integer value, long time) {

            this.value = new AtomicInteger(value);
            this.time = time;
        }

        // 自增
        public void incr() {
            value.getAndIncrement();
        }

        public Data() {

        }
    }

    // 延迟队列
    private class DataDelay implements Delayed {

        String key;
        long expire;

        public DataDelay() {
        }

        public DataDelay(String key, long time) {
            this.key = key;
            this.expire = new Date().getTime() + (time * 1000);
        }

        public DataDelay(String key) {
            this.key = key;

        }


        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);

        }

        @Override
        public int compareTo(Delayed o) {
            long f = this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
            return (int) f;
        }

        // 只比较key
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DataDelay dataDelay = (DataDelay) o;
            return Objects.equals(key, dataDelay.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }


}
