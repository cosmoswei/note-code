package com.wei;


import cn.hutool.Hutool;
import cn.hutool.core.map.MapUtil;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Hutool.getAllUtils().forEach(System.out::println);
        Map<Integer, List<BigPerson>> map1 = Stream.of(new BigPerson("11", 1),
                new BigPerson("12", 1),
                new BigPerson("21", 2),
                new BigPerson("22", 2),
                new BigPerson("31", 3)
        ).collect(Collectors.groupingBy(BigPerson::getAge));

        Map<Integer, List<BigPerson>> map2 = Stream.of(
                new BigPerson("32", 3),
                new BigPerson("41", 4),
                new BigPerson("42", 4),
                new BigPerson("51", 5)
        ).collect(Collectors.groupingBy(BigPerson::getAge));


        System.out.println(map1);
        System.out.println(map2);
        map1.forEach((k, v) -> map2.merge(k, v, (v1, v2) -> v2));
        System.out.println(map2);

        //将map1和map2收集成一个流
        Stream<Map.Entry<Integer, List<BigPerson>>> concat = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream());
        //然后将其收集成一个新的map
        Map<Integer, List<BigPerson>> collect = concat.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v2));
        collect.forEach((key, value) -> System.out.println(key + ": " + value));

    }

    private static void mergeTest() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "str1");
        map.put(2, "str2");
        map.merge(3, "My", String::concat);
        System.out.println(map);  //result: {1=str1, 2=str2, 3=My}

        Map<Integer, String> map3 = new HashMap<>();
        map3.put(1, "str1");
        map3.put(2, "str2");
        map3.merge(1, "mmm", String::concat);
        System.out.println(map3);  //result: {1=str1mmm, 2=str2}

        Map<Integer, Integer> map4 = new HashMap<>();
        map4.put(1, 1);
        map4.put(2, 2);
        map4.merge(1, 1, Integer::sum);
        System.out.println(map4);  //result: {1=2, 2=2}
    }

    public static <K, X, Y, V> Map<K, V> merge(Map<K, X> map1, Map<K, Y> map2, BiFunction<X, Y, V> merge) {
        if (MapUtil.isEmpty(map1) && MapUtil.isEmpty(map2)) {
            return MapUtil.newHashMap(0);
        } else {
            if (MapUtil.isEmpty(map1)) {
                map1 = MapUtil.newHashMap(0);
            } else if (MapUtil.isEmpty(map2)) {
                map2 = MapUtil.newHashMap(0);
            }

            Set<K> key = new HashSet<>();
            key.addAll((map1).keySet());
            key.addAll((map2).keySet());
            Map<K, V> map = MapUtil.newHashMap(key.size());
            Iterator var5 = key.iterator();

            while (var5.hasNext()) {
                K t = (K) var5.next();
                X x = (X) (map1).get(t);
                Y y = (Y) (map2).get(t);
                V z = merge.apply(x, y);
                if (z != null) {
                    map.put(t, z);
                }
            }
            return map;
        }
    }
}