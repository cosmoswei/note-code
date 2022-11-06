package com.wei;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class UserThreadFactory implements ThreadFactory {

    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger(1);

    UserThreadFactory(String whatFeatureOfGroup) {
        namePrefix = "UserThreadFactory" + whatFeatureOfGroup + "worker -";
    }

    @Override
    public Thread newThread(Runnable task) {
        String name = namePrefix + nextId.getAndIncrement();
        Thread thread = new Thread(null, task, name, 0);
        System.out.println(thread.getName());
        return thread;
    }

    static class Task implements Runnable {

        private final AtomicLong count = new AtomicLong(0L);

        @Override
        public void run() {
            System.out.println("running_" + count.getAndIncrement());
        }
    }
}
