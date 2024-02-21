package com.wei.leetcode.f2500;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Solution_2085 {

    public static void main(String[] args) {
        String[] strings1 = {"a", "ab"};
        String[] strings2 = {"a", "a", "a", "ab"};
        Solution_2085 solution2085 = new Solution_2085();
        int i = solution2085.countWords(strings1, strings2);
        System.out.println("i = " + i);
    }

    public int countWords(String[] words1, String[] words2) {
        int res = 0;
        if (null == words1 || null == words2) {
            return res;
        }

        Map<String, Integer> map1 = new HashMap<>();
        for (String string : words1) {
            map1.put(string, map1.getOrDefault(string, 0) + 1);
        }
        Map<String, Integer> map2 = new HashMap<>();
        for (String string : words2) {
            map2.put(string, map2.getOrDefault(string, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            String key = entry.getKey();
            Integer i = map2.get(key);
            if (Objects.nonNull(i) && entry.getValue() == 1 && i == 1) {
                res++;
            }
        }
        return res;
    }
}
