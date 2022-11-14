package com.wei.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamSkip {

    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            integerList.add(i);
        }

        System.out.println(integerList);

        List<Integer> collect0 = integerList.stream().skip(Math.multiplyExact(0,20)).limit(20).collect(Collectors.toList());
        List<Integer> collect1 = integerList.stream().skip(1 * 20).limit(20).collect(Collectors.toList());
        List<Integer> collect2 = integerList.stream().skip(2 * 20).limit(20).collect(Collectors.toList());

        System.out.println(collect0);
        System.out.println(collect1);
        System.out.println(collect2);
    }
}
