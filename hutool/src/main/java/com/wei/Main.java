package com.wei;


import cn.hutool.Hutool;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.BooleanUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Hutool.getAllUtils().forEach(System.out::println);
        boolean aFalse = BooleanUtil.isFalse(true);
        LocalDateTime of = LocalDateTimeUtil.of(new Date());
        Date date = new Date();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(of.toLocalDate());
        System.out.println(date);
    }
}