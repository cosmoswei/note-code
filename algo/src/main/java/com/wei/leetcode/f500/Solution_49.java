package com.wei.leetcode.f500;

import java.util.*;

public class Solution_49 {

    public static void main(String[] args) {
        Solution_49 solution19 = new Solution_49();
        args = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("solution19.groupAnagrams(args) = "
                + solution19.groupAnagrams(args));
    }


    /**
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
     * 示例 1:
     * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> orDefault = map.getOrDefault(key, new ArrayList<>());
            orDefault.add(str);
            map.put(key,orDefault);
        }
        return new LinkedList<>(map.values());
    }
}
