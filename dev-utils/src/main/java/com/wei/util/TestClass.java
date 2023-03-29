package com.wei.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestClass {
    public static void main(String[] args) {
        Map<String, List<String>> map1 = new HashMap<>();
        List<String> list1_1 = new ArrayList<>();
        list1_1.add("value1_1_1");
        list1_1.add("value1_1_2");
        map1.put("key1", list1_1);
        map1.put("key2", list1_1);

        Map<String, List<String>> map2 = new HashMap<>();
        List<String> list2_1 = new ArrayList<>();
        list2_1.add("value2_1_1");
        list2_1.add("value2_1_2");
        list2_1.add("value2_1_2");
        map2.put("key2", list2_1);

        Map<String, List<String>> mergedMap = merge(map1, map2);

        System.out.println(mergedMap);
    }

    private static Map<String, List<String>> merge(Map<String, List<String>> map1, Map<String, List<String>> map2) {
        Map<String, List<String>> mergedMap = new HashMap<>(map1);
        for (Map.Entry<String, List<String>> entry : map2.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            if (mergedMap.containsKey(key)) {
                List<String> mergedValue = new ArrayList<>();
                mergedValue.addAll(mergedMap.get(key));
                mergedValue.addAll(value);
                mergedMap.put(key, mergedValue);
            } else {
                mergedMap.put(key, value);
            }
        }
        return mergedMap;
    }

    public static void main2(String[] args) {
        Map<String, Map<String, String>> map1 = new HashMap<>();
        Map<String, String> map1_1 = new HashMap<>();
        map1_1.put("key1_1", "value1_1");
        map1_1.put("key1_2", "value1_2");
        map1.put("key1", map1_1);

        Map<String, Map<String, String>> map2 = new HashMap<>();
        Map<String, String> map2_1 = new HashMap<>();
        map2_1.put("key2_1", "value2_1");
        map2_1.put("key2_2", "value2_2");
        map2.put("key2", map2_1);

        Map<String, Map<String, String>> mergedMap = new HashMap<>();
        mergedMap.putAll(map1);
        for (Map.Entry<String, Map<String, String>> entry : map2.entrySet()) {
            String key = entry.getKey();
            Map<String, String> value = entry.getValue();
            if (mergedMap.containsKey(key)) {
                Map<String, String> mergedValue = new HashMap<>();
                mergedValue.putAll(mergedMap.get(key));
                mergedValue.putAll(value);
                mergedMap.put(key, mergedValue);
            } else {
                mergedMap.put(key, value);
            }
        }

        System.out.println(mergedMap);
    }
}