package com.wei.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: kevin
 * @Date: 2019-04-11 13:15
 */
public class ThreadPool {
    /**
     * 构造一个任务池
     * 参数说明：
     * corePoolSize - 池中所保存的线程数，包括空闲线程。
     * maximumPoolSize - 池中允许的最大线程数。
     * keepAliveTime - 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
     * unit - keepAliveTime 参数的时间单位。
     * workQueue - 执行前用于保持任务的队列。此队列仅保持由 execute 方法提交的 Runnable 任务。
     * threadFactory - 执行程序创建新线程时使用的工厂。
     * handler - 由于超出线程范围和队列容量而使执行被阻塞时所使用的处理程序
     */
    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));
    private static ThreadPool instance = null;

    private ThreadPool() {
    }

    /**
     * 使用线程执行任务
     *
     * @param runnable
     */
    public void executor(Runnable runnable) {
        executor.execute(runnable);
    }

    /**
     * 关闭线程池
     */
    public void shutdown() {
        executor.shutdown();
    }

    /**
     * 获取单例的线程池对象：双锁检查实现单例线程池
     *
     * @return
     */
    public static ThreadPool getThreadPool() {
        if (instance == null) {
            synchronized (ThreadPool.class) {
                if (instance == null) {
                    instance = new ThreadPool();
                }
            }
        }
        return instance;
    }
}
