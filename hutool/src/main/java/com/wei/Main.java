package com.wei;


import cn.hutool.Hutool;
import cn.hutool.core.util.BooleanUtil;

public class Main {
    public static void main(String[] args) {
        Hutool.getAllUtils().forEach(System.out::println);
        boolean aFalse = BooleanUtil.isFalse(true);
    }
}