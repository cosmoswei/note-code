package com.wei;


import cn.hutool.Hutool;
import cn.hutool.core.map.MapUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
        Hutool.getAllUtils().forEach(System.out::println);
    }
    public static <K, X, Y, V> Map<K, V> merge(Map<K, X> map1, Map<K, Y> map2, BiFunction<X, Y, V> merge) {
        if (MapUtil.isEmpty((Map)map1) && MapUtil.isEmpty((Map)map2)) {
            return MapUtil.newHashMap(0);
        } else {
            if (MapUtil.isEmpty((Map)map1)) {
                map1 = MapUtil.newHashMap(0);
            } else if (MapUtil.isEmpty((Map)map2)) {
                map2 = MapUtil.newHashMap(0);
            }

            Set<K> key = new HashSet();
            key.addAll(((Map)map1).keySet());
            key.addAll(((Map)map2).keySet());
            Map<K, V> map = MapUtil.newHashMap(key.size());
            Iterator var5 = key.iterator();

            while(var5.hasNext()) {
                K t = (K) var5.next();
                X x = (X) ((Map)map1).get(t);
                Y y = (Y) ((Map)map2).get(t);
                V z = merge.apply(x, y);
                if (z != null) {
                    map.put(t, z);
                }
            }

            return map;
        }
    }
}