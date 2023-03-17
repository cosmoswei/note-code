package com.wei.util;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {

    private TimeUtils() {
    }

    public static final String SIMPLE_TIME_FORMAT = "yyyy:MM:dd HH:mm:ss";

    public static final String CHINESE_TIME_FORMAT = "yyyy 年 MM 月 dd 日 E HH 点 mm 分 ss 秒";

    public static LocalDateTime of(Instant instant) {
        return of(instant, ZoneId.systemDefault());
    }

    public static LocalDateTime ofUTC(Instant instant) {
        return of(instant, ZoneId.of("UTC"));
    }

    public static LocalDateTime of(ZonedDateTime zonedDateTime) {
        return null == zonedDateTime ? null : zonedDateTime.toLocalDateTime();
    }

    public static LocalDateTime of(Instant instant, ZoneId zoneId) {
        return null == instant ? null : LocalDateTime.ofInstant(instant, (ZoneId) ObjectUtils.defaultIfNull(zoneId, ZoneId::systemDefault));
    }

    public static LocalDateTime of(Instant instant, TimeZone timeZone) {
        return null == instant ? null : of(instant, ((TimeZone) ObjectUtils.defaultIfNull(timeZone, TimeZone::getDefault)).toZoneId());
    }

    public static LocalDateTime of(long epochMilli) {
        return of(Instant.ofEpochMilli(epochMilli));
    }

    public static LocalDateTime ofUTC(long epochMilli) {
        return ofUTC(Instant.ofEpochMilli(epochMilli));
    }

    public static LocalDateTime of(long epochMilli, ZoneId zoneId) {
        return of(Instant.ofEpochMilli(epochMilli), zoneId);
    }

    public static LocalDateTime of(long epochMilli, TimeZone timeZone) {
        return of(Instant.ofEpochMilli(epochMilli), timeZone);
    }

    public static LocalDateTime of(Date date) {
        if (null == date) {
            return null;
        } else {
            return of(date.toInstant());
        }
    }

    /**
     * 转为标准时间格式
     *
     * @param date
     * @return
     */
    public static String standardFormat(Date date) {
        LocalDateTime time = of(date);
        return null == time ? "" : time.format(DateTimeFormatter.ofPattern(SIMPLE_TIME_FORMAT));
    }
}
