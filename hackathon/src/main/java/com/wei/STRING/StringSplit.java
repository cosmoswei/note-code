package com.wei.STRING;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class StringSplit {
    public static void main(String[] args) {
        List<String> path = new ArrayList<>();
        path.add("huangxuwei_1");
        path.add("huangxuwei_2");
        path.add("huangxuwei_3");
        path.add("huangxuwei_08");
        path.add("huangxuwei_04");
        path.add("huangxuwei_09");
        path.add("huangxuwei_05");
        path.add("huangxuwei_06");
        path.add("huangxuwei_07");
        path.add("huang_5");
        String name = "_huangxueihuangxuwei_huangxuwei";
        long l = System.currentTimeMillis();
        System.out.println(name.indexOf("_"));
        Map<String, List<String[]>> collect = path.stream()
                .map(s -> s.split("_"))
                .sorted(Comparator.comparing(a -> Long.valueOf(a[1])))
                .collect(Collectors.groupingBy(a -> a[0]));
        System.out.println(System.currentTimeMillis() - l);//33

        Map<String, List<List<String>>> collectSplitUtil = path.stream()
                .map(s -> SplitUtils.split(s,"_"))
                .sorted(Comparator.comparing(a -> Long.valueOf(a.get(1))))
                .collect(Collectors.groupingBy(a -> a.get(0)));
        System.out.println(System.currentTimeMillis() - l);//33

        collect.forEach((k, v) -> {
            List<String> collect1 = v.stream().map(e -> e[0] + "_" + e[1]).collect(Collectors.toList());
            System.out.println("collect = " + collect1);
            System.out.println();

        });//
    }
}
