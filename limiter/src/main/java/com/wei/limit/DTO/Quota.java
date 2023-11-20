package com.wei.limit.DTO;

import lombok.Data;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Data
public class Quota {

    /**
     * 用于不同具体限流实现
     */
    public final String key;

    public final int interval;

    private Queue<Long> requestTimestamps = new ConcurrentLinkedQueue<>();

    public Quota(String key, int limit) {
        this.key = key;
        this.interval = limit;
    }
}
