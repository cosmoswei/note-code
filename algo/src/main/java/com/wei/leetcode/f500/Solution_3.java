package com.wei.leetcode.f500;

import java.util.HashMap;
import java.util.Map;

public class Solution_3 {

    public static void main(String[] args) {
        System.out.println("findAnagrams = " + lengthOfLongestSubstring("abbcddeb"));
    }

    public static String findLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        int n = s.length();
        int left = 0, right = 0;
        int maxLength = 0;
        int start = 0;

        Map<Character, Integer> charIndexMap = new HashMap<>();

        while (right < n) {
            char currentChar = s.charAt(right);
            if (charIndexMap.containsKey(currentChar)) {
                left = Math.max(charIndexMap.get(currentChar) + 1, left);
            }
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                start = left;
            }
            charIndexMap.put(currentChar, right);
            right++;
        }

        return s.substring(start, start + maxLength);
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                window.put(d, window.get(d) - 1);
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    static int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> window = new HashMap<>();

        int left = 0, right = 0;
        int res = 0; // 记录结果
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 进行窗口内数据的一系列更新
            window.put(c, window.getOrDefault(c, 0) + 1);
            // 判断左侧窗口是否要收缩
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                // 进行窗口内数据的一系列更新
                window.put(d, window.get(d) - 1);
            }
            // 在这里更新答案
            res = Math.max(res, right - left);
        }
        return res;
    }
}
