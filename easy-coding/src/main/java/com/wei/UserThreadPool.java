package com.wei;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UserThreadPool {
    public static void main(String[] args) {
        LinkedBlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(2);
        UserThreadFactory factory1 = new UserThreadFactory("第一机房");
        UserThreadFactory factory2 = new UserThreadFactory("第二机房");

        UserRejectHandler handler = new UserRejectHandler();

        ThreadPoolExecutor threadPoolFirst = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS,
                blockingQueue, factory1, handler);

        ThreadPoolExecutor threadPoolSecond = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS,
                blockingQueue, factory2, handler);

        UserThreadFactory.Task task = new UserThreadFactory.Task();

        for (int i = 0; i < 100; i++) {
            threadPoolFirst.execute(task);
            threadPoolSecond.execute(task);

        }


    }
}
