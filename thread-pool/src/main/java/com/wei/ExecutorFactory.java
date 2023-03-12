package com.wei;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/**
 * 线程池工厂
 * @author Tarzan写bug
 * @since 2022/09/08
 */
public class ExecutorFactory {

    /**
     * 单线程线程池
     * @return
     */
    public static ExecutorService newSingleExecutorService() {
        return Executors.newFixedThreadPool(1);
    }

    /**
     * 单线程线程池
     * @param threadFactory 线程工厂
     * @return
     */
    public static ExecutorService newSingleExecutorService(ThreadFactory threadFactory) {
        return Executors.newFixedThreadPool(1, threadFactory);
    }

    /**
     * 固定线程先线程池
     * @param threadNum 线程数量
     * @return
     */
    public static ExecutorService newFixedExecutorService(int threadNum) {
        return Executors.newFixedThreadPool(threadNum);
    }

    /**
     * 固定线程线程池
     * @param threadNum 线程数量
     * @param threadFactory 线程工厂
     * @return
     */
    public static ExecutorService newFixedExecutorService(int threadNum, ThreadFactory threadFactory) {
        return Executors.newFixedThreadPool(threadNum, threadFactory);
    }

    /**
     * 单线程定时任务线程池
     * @return
     */
    public static ExecutorService newSingleScheduleExecutorService() {
        return Executors.newScheduledThreadPool(1);
    }

    /**
     * 单线程定时任务线程池
     * @param threadFactory 线程工厂
     * @return
     */
    public static ExecutorService newSingleScheduleExecutorService(ThreadFactory threadFactory) {
        return Executors.newScheduledThreadPool(1, threadFactory);
    }

    /**
     * 固定线程定时任务线程池
     * @param threadNum 线程数量
     * @return
     */
    public static ExecutorService newScheduleExecutorService(int threadNum) {
        return Executors.newScheduledThreadPool(threadNum);
    }

    /**
     * 固定线程定时任务线程池
     * @param threadNum 线程数量
     * @param threadFactory 线程工厂
     * @return
     */
    public static ExecutorService newScheduleExecutorService(int threadNum, ThreadFactory threadFactory) {
        return Executors.newScheduledThreadPool(threadNum, threadFactory);
    }

    /**
     * 带有生命周期的线程池
     */
    public static class Managed {

        /**
         * 单线程线程池
         * @return
         */
        public static ExecutorService newSingleExecutorService() {
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            ThreadPoolManager.register(executorService);
            return executorService;
        }

        /**
         * 单线程线程池
         * @param threadFactory 线程工厂
         * @return
         */
        public static ExecutorService newSingleExecutorService(ThreadFactory threadFactory) {
            ExecutorService executorService = Executors.newFixedThreadPool(1, threadFactory);
            ThreadPoolManager.register(executorService);
            return executorService;
        }

        /**
         * 固定线程线程池
         * @param threadNum 线程数量
         * @return
         */
        public static ExecutorService newFixedExecutorService(int threadNum) {
            ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
            ThreadPoolManager.register(executorService);
            return executorService;
        }

        /**
         * 固定线程线程池
         * @param threadNum 线程数量
         * @param threadFactory 线程工厂
         * @return
         */
        public static ExecutorService newFixedExecutorService(int threadNum, ThreadFactory threadFactory) {
            ExecutorService executorService = Executors.newFixedThreadPool(threadNum, threadFactory);
            ThreadPoolManager.register(executorService);
            return executorService;
        }

        /**
         * 单线程定时任务线程池
         * @return
         */
        public static ExecutorService newSingleScheduleExecutorService() {
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            ThreadPoolManager.register(executorService);
            return executorService;
        }

        /**
         * 单线程定时任务线程池
         * @param threadFactory 线程工厂
         * @return
         */
        public static ExecutorService newSingleScheduleExecutorService(ThreadFactory threadFactory) {
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1, threadFactory);
            ThreadPoolManager.register(executorService);
            return executorService;
        }

        /**
         * 固定线程线程池
         * @param threadNum 线程数量
         * @return
         */
        public static ScheduledExecutorService newScheduleExecutorService(int threadNum) {
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(threadNum);
            ThreadPoolManager.register(executorService);
            return executorService;
        }

        /**
         * 固定线程线程池
         * @param threadNum 线程数量
         * @param threadFactory 线程工厂
         * @return
         */
        public static ScheduledExecutorService newScheduleExecutorService(int threadNum, ThreadFactory threadFactory) {
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(threadNum, threadFactory);
            ThreadPoolManager.register(executorService);
            return executorService;
        }
    }
}
