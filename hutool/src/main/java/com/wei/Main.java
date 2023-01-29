package com.wei;


import cn.hutool.Hutool;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.stream.StreamUtil;
import cn.hutool.core.util.BooleanUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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
        List<String> of1 = ListUtil.of("1", "2", "3");
        List<String> of2 = ListUtil.of("1", "2", "3");

        String join = StreamUtil.join(of1.stream(), "1");
        System.out.println(join);

    }
}