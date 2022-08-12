package com.wei.chapter12;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.TimeZone;

@Slf4j
public class LocalDateTimeExplore {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2022, 8, 12);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("date.getYear() = " + date.getYear());
        System.out.println("date.getYear() = " + date.getMonth());
        System.out.println("date.getYear() = " + date.getDayOfYear());
        System.out.println("date.getYear() = " + date.getDayOfMonth());
        System.out.println("date.getYear() = " + date.lengthOfMonth());
        System.out.println("date.getYear() = " + date.getDayOfYear());
        System.out.println("date.getYear() = " + date.getDayOfWeek());
        System.out.println("date.getYear() = " + date.isLeapYear());
        System.out.println("LocalDateTime.now() = " + LocalDateTime.now());
        log.info(String.valueOf(date.get(ChronoField.YEAR)));
        log.info(String.valueOf(date.get(ChronoField.MONTH_OF_YEAR)));
        System.out.println("localDateTime.getHour() = " + localDateTime.getHour());


        LocalTime time1 = LocalTime.now();
        LocalTime time2 = time1.plusHours(1);
        Duration duration = Duration.between(time1, time2);
        System.out.println(duration.get(ChronoUnit.SECONDS));
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = date1.plusDays(1);
        LocalDate date3 = date2.plusMonths(4);
        LocalDate date4 = date3.plus(2, ChronoUnit.YEARS);
        Period period = Period.between(date2, date1);
        System.out.println(period.getDays());
        System.out.println(date3);
        System.out.println(date4);
        System.out.println("date4.withYear(2222) = " + date4.withYear(2222));
        LocalDate date5 = date4.withYear(2222);
        System.out.println(date5);


        LocalDate with1 = date1.with(temporal -> {
                    DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
                    int dayToAdd = 1;
                    if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
                    else if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
                    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
                }
        );
        TemporalAdjuster adjuster = TemporalAdjusters.ofDateAdjuster(temporal -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
            else if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
        LocalDate with2 = date1.with(adjuster);
        System.out.println(with1);
        System.out.println(with2);
        String format1 = date1.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String format2 = date1.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(format1);


        LocalDateTime localDateTime2 = LocalDateTime.now();
        ZoneId z = ZoneId.of("Europe/Rome");
        ZonedDateTime zonedDateTime = localDateTime2.atZone(z);
        Instant instant = Instant.now();
        ZonedDateTime zonedDateTime1 = instant.atZone(z);
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime1);
        System.out.println("Instant.now() = " + Instant.now());
        System.out.println("TimeZone.getDefault().toZoneId() = " + TimeZone.getDefault().toZoneId());
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(instant, z);
        System.out.println(localDateTime1);
        Instant instant1 = localDateTime1.toInstant(ZoneOffset.ofHours(-8));
        System.out.println(instant1);
    }
}
