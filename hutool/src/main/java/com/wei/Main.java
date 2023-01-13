package com.wei;


import cn.hutool.Hutool;

public class Main {
    public static void main(String[] args) {
        Hutool.getAllUtils().forEach(System.out::println);
    }
}