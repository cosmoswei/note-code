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

    public static final String TIME_FORMAT = "yyyy:MM:dd HH:mm:ss";

    public static final String CHINESE_TIME_FORMAT = "yyyy 年 MM 月 dd 日 E HH 点 mm 分 ss 秒";

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

    public static void log(String message) {

        String s = new StringJoiner(" | ")
                .add(getCurrent())
                .add(String.format("%2d", Thread.currentThread().getId()))
                .add(String.valueOf(Thread.currentThread().getName()))
                .add(message)
                .toString();
        System.out.println(s);
    }

    private static String getCurrent() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(CHINESE_TIME_FORMAT));
    }
}
