package com.wei;

import java.util.List;

public class ExtensionsTest {
    public static void main(String[] args) {

        String str = "1,2,3,4,5,6";
        String[] split = str.split(",");
        System.out.println(split.toString());
        List<String> strings = List.of("1", "2", "3");
        strings.forEach(System.out::println);

        String[] split2 = str.split(',');
        split2.toList().forEach(System.out::println);

    }
}