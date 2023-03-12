package com.wei;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程工具类
 * @author Tarzan写bug
 * @since 2022/09/08
 */
public class ThreadUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadUtil.class);

    private ThreadUtil() {}

    /**
     * 程序关闭时添加回调线程
     * @param runnable
     */
    public static void addShutdownHook(Runnable runnable) {
        Runtime.getRuntime().addShutdownHook(new Thread(runnable));
    }

    /**
     * 关闭线程池
     * @param executorService 线程池
     */
    public static void shutdownThreadPool(ExecutorService executorService) {
        executorService.shutdown();
        int retry = 3;
        if (retry > 0) {
            retry--;
            try {
                if (executorService.awaitTermination(1, TimeUnit.SECONDS)) {
                    return;
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
                Thread.interrupted();
            } catch (Throwable ex) {
                LOGGER.error("shutdown thread pool has error: {}", ex);
            }
        }
        executorService.shutdownNow();
    }
}
