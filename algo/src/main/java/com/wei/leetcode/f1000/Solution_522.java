package com.wei.leetcode.f1000;

public class Solution_522 {

    public static void main(String[] args) {
        Solution_522 solution = new Solution_522();
        String[] strings = {"aaa","aaa","aa"};
        System.out.println("solution.findLUSlength(strings) = " + solution.findLUSlength(strings));
    }


    /**
     * 给定字符串列表 strs ，返回其中 最长的特殊序列 的长度。如果最长特殊序列不存在，返回 -1 。
     * 特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。
     * s 的 子序列可以通过删去字符串 s 中的某些字符实现。
     * 例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。"aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。
     * 示例 1：
     * 输入: strs = ["aba","cdc","eae"]
     * 输出: 3
     * 示例 2:
     * 输入: strs = ["aaa","aaa","aa"]
     * 输出: -1
     */
    public int findLUSlength(String[] strs) {
        int res = -1;
        next:
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() <= res) {
                continue;
            }
            for (int j=0; j < strs.length; j++) {
                if (i != j && isSubString(strs[i], strs[j])) {
                    continue next;
                }
            }
            res = strs[i].length();
        }
        return res;
    }

    private boolean isSubString(String s, String t) {
        int i = 0;
        for (char c : t.toCharArray()) {
            if (s.charAt(i) == c
                    && ++i == s.length()) {
                return true;
            }
        }
        return false;
    }
}
