package com.wei.future;

import java.util.concurrent.*;

public class CustomGlobalExecutor {

    private static final ExecutorService THREAD_POOL = new ThreadPoolExecutor(200, 200,
            1L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(3),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    private CustomGlobalExecutor() {
    }

    public static ExecutorService getExecutor() {
        return THREAD_POOL;
    }

}
