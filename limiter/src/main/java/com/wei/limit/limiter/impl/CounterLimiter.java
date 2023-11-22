package com.wei.limit.limiter.impl;


import com.wei.limit.DTO.LimiterMataData;
import com.wei.limit.constant.SimpleLimiterConstant;
import com.wei.limit.limiter.LimiterAbstract;
import org.springframework.stereotype.Component;

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
 * 计数器限流
 */
@Component(SimpleLimiterConstant.COUNTER)
public class CounterLimiter extends LimiterAbstract {

    /**
     * key 与统计指标
     */
    private final Map<String, Data> map = new HashMap<>();

    /**
     * 阻塞队列，用来做什么的？
     */
    private final BlockingQueue<DataDelay> delayQueue = new DelayQueue<>();

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
    public void set(String key, Integer value, long interval) {
        map.put(key, new Data(value, interval));
        delayQueue.add(new DataDelay(key, interval));
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

    @Override
    public void incr(String key, int interval) {
        if (map.containsKey(key)) {
            map.get(key).incr();
        } else {
            set(key, 1, interval);
        }
    }

    @Override
    public boolean limit(LimiterMataData limiterMataData) {
        String key = limiterMataData.key;
        int limit = limiterMataData.limit;
        int time = limiterMataData.interval;
        if (!map.containsKey(key)) {
            set(key, 1, time);
            return false;
        }
        Data data = map.get(key);
        // 如果指标里的值大于限流值目标值
        boolean res = data.value.get() >= limit;
        if (res) {
            // 移除这个延迟队列的Key
            removeDelayKey(key);
            // 添加到延迟队列里，续上新的过期时间
            addDelayKey(key, data.interval);
        }
        // 自增
        incr(key, time);
        return res;
    }

    private void removeDelayKey(String key) {
        delayQueue.remove(new DataDelay(key));
    }

    private void addDelayKey(String key, long time) {
        delayQueue.add(new DataDelay(key, time));
    }

    // 初始化延迟队列
    private void init() {
        new Thread(() -> {
            while (true) {
                try {
                    // 每到过期时间，会把这个Key干掉
                    DataDelay take = delayQueue.take();
                    System.out.println("??????" + take.key);
                    map.remove(take.key);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CounterLimiter").start();
    }

    // 封装数据
    private class Data {

        /**
         * 限流值
         */
        AtomicInteger value;
        /**
         * 限流时间
         */
        long interval;

        public Data() {
        }

        public Data(Integer value, long interval) {
            this.value = new AtomicInteger(value);
            this.interval = interval;
        }

        // 自增
        public void incr() {
            value.getAndIncrement();
        }
    }

    // 延迟队列
    private class DataDelay implements Delayed {

        /**
         * 队列的Key
         */
        String key;

        /**
         * 到期时间
         */
        long expireTime;

        public DataDelay() {
        }

        public DataDelay(String key, long time) {
            this.key = key;
            this.expireTime = new Date().getTime() + time;
        }

        public DataDelay(String key) {
            this.key = key;
        }


        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expireTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
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
