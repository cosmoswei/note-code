package com.wei;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 线程池生命周期管理
 * @author Tarzan写bug
 * @since 2022/09/08
 */
public class ThreadPoolManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolManager.class);

    /**
     * 线程池集合
     */
    private static Set<ExecutorService> managedExecutors = new HashSet<>();

    /**
     * 对象锁，防并发写
     */
    private static final Object LOCK = new Object();

    /**
     * CAS
     */
    private static final AtomicBoolean CLOSED = new AtomicBoolean(false);

    /**
     * 单例
     */
    private static final ThreadPoolManager INSTANCE = new ThreadPoolManager();

    private ThreadPoolManager() {}

    static {
        // 应用关闭时，优雅关闭线程池
        ThreadUtil.addShutdownHook(new Runnable() {
            @Override
            public void run() {
                LOGGER.warn("start shutdown thread pool");
                shutdown();
                LOGGER.warn("shutdown thread pool finish");
            }
        });
    }

    /**
     * 单例
     * @return
     */
    public static ThreadPoolManager getInstance() {
        return INSTANCE;
    }

    /**
     * 注册线程池
     * @param executorService
     */
    public static void register(ExecutorService executorService) {
        synchronized (LOCK) {
            managedExecutors.add(executorService);
        }
    }

    /**
     * 注销线程池
     * @param executorService
     */
    public static void deregister(ExecutorService executorService) {
        synchronized (LOCK) {
            managedExecutors.remove(executorService);
        }
    }

    /**
     * 关闭线程池
     */
    private static void shutdown() {
        // CAS防止多次调用
        if (!CLOSED.compareAndSet(false, true)) {
            return;
        }
        for (ExecutorService managedExecutor : managedExecutors) {
            ThreadUtil.shutdownThreadPool(managedExecutor);
        }
    }
}
