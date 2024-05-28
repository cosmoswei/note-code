package com.wei.leetcode.f500;

public class Solution_3 {

    public static void main(String[] args) {
        System.out.println("res = " + new Solution_3().lengthOfLongestSubstring("aaabcabcdabcbbabccabcd"));
    }

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长
     * 子串
     * 的长度。
     * 示例 1:
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        if (s == null || s.isEmpty()) {
            return res;
        }
        char[] charArray = s.toCharArray();
        boolean[] has = new boolean[128];
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = charArray[right];
            while (has[c]) {
                has[charArray[left]] = false;
                left++;
            }
            has[c] = true;
            res = Math.max(res, right - left + 1);
            System.out.println("left = " + left + ", right = " + right);
        }
        return res;
    }
}
