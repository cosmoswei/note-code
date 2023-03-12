package com.wei;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 全局线程池
 *
 * @author Tarzan写bug
 * @since 2022/09/08
 */
public class GlobalExecutor {

    /**
     * 普通线程池
     */
    private static final ExecutorService COMMON_EXECUTOR = ExecutorFactory.Managed
            .newFixedExecutorService(Runtime.getRuntime().availableProcessors(),
                    new NameThreadFactory("com.wei"));

    /**
     * 定时线程池
     */
    private static final ScheduledExecutorService COMMON_SCHEDULE_EXECUTOR = ExecutorFactory.Managed
            .newScheduleExecutorService(Runtime.getRuntime().availableProcessors(),
                    new NameThreadFactory("com.wei"));

    private GlobalExecutor() {
    }

    /**
     * 提交任务到线程池中执行
     *
     * @param runnable 线程任务
     */
    public static void submitCommonExecutor(Runnable runnable) {
        COMMON_EXECUTOR.submit(runnable);
    }

    /**
     * 提交任务到线程池中延迟执行
     *
     * @param runnable 线程任务
     * @param delay    延迟时间
     * @param unit     时间单位
     */
    public static void scheduleCommonExecutor(Runnable runnable, long delay, TimeUnit unit) {
        COMMON_SCHEDULE_EXECUTOR.schedule(runnable, delay, unit);
    }

    /**
     * 提交任务到线程池中定时执行
     *
     * @param runnable 线程任务
     * @param delay    延迟时间
     * @param fixed    定时时间
     * @param unit     时间单位
     */
    public static void scheduleWithFixedDelay(Runnable runnable, long delay, long fixed, TimeUnit unit) {
        COMMON_SCHEDULE_EXECUTOR.scheduleWithFixedDelay(runnable, delay, fixed, unit);
    }
}
