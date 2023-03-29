package com.wei.virtual;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = null;
        try {
//            executorService = Executors.newVirtualThreadPerTaskExecutor();
            executorService = Executors.newFixedThreadPool(5);
            executorService.execute(new MyTask());
            executorService.execute(new MyTask());
            executorService.execute(new MyTask());
            executorService.execute(new MyTask());
            executorService.execute(new MyTask());
            executorService.execute(new MyTask());
            executorService.submit(new MyTask());
            executorService.submit(new MyTask());
            executorService.submit(new MyTask());
            executorService.submit(new MyTask());
            executorService.submit(new MyTask());
            executorService.submit(new MyTask());
            executorService.submit(new MyTask());
            executorService.submit(new MyTask());
            Thread.sleep(Duration.ofSeconds(10));
        } finally {
            if (Objects.nonNull(executorService)) {
                executorService.close();
            }
        }

    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread());
            sleep();
            System.out.println(LocalDateTime.now());
        }
    }

    static void sleep() {
        try {
            Thread.sleep(Duration.ofSeconds(1)
            );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
