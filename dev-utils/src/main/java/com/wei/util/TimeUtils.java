package com.wei.util;


import java.time.*;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {

    private TimeUtils() {
    }

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

}
