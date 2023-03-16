package com.wei.util.function;

/**
 * 线程工具类
 *
 * @author huangxuwei
 */
public class ThreadUtils {
    private ThreadUtils() {

    }

    public static boolean safeSleep(long millis) {
        long spendTime;
        for (long done = 0L; done >= 0L && done < millis; done += spendTime) {
            long before = System.currentTimeMillis();
            if (!sleep(millis - done)) {
                return false;
            }

            spendTime = System.currentTimeMillis() - before;
            if (spendTime <= 0L) {
                break;
            }
        }

        return true;
    }

    public static boolean sleep(long millis) {
        if (millis > 0L) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException var3) {
                return false;
            }
        }

        return true;
    }

    public static void log() {

    }
}
