package com.wei;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂
 * @author Tarzan写bug
 * @since 2022/09/08
 */
public class NameThreadFactory implements ThreadFactory {

    private final AtomicInteger ID = new AtomicInteger(0);

    private String name;

    public NameThreadFactory(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        String threadName = name + ID.getAndDecrement();
        Thread thread = new Thread(r, threadName);
        thread.setDaemon(true);
        return thread;
    }
}
