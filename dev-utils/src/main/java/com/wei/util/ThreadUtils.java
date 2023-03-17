package com.wei.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;


/**
 * 线程工具类
 *
 * @author huangxuwei
 */
public class ThreadUtils {

    private ThreadUtils() {

    }

    public static void safeSleep(long millis) {
        long spendTime;
        for (long done = 0L; done >= 0L && done < millis; done += spendTime) {
            long before = System.currentTimeMillis();
            if (!sleep(millis - done)) {
                return;
            }

            spendTime = System.currentTimeMillis() - before;
            if (spendTime <= 0L) {
                break;
            }
        }

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

    public static void threadLog(String message) {

        String s = new StringJoiner(" | ")
                .add(getCurrentTime())
                .add(String.format("%2d", Thread.currentThread().getId()))
                .add(String.valueOf(Thread.currentThread().getName()))
                .add(message)
                .toString();
        System.out.println(s);
    }

    private static String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(TimeUtils.CHINESE_TIME_FORMAT));
    }
}
