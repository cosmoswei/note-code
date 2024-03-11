package com.wei.leetcode.f2000;

class Solution_2129 {
    public String capitalizeTitle(String title) {
        StringBuilder sb = new StringBuilder(title);
        int n = title.length();
        int l = 0, r = 0;   // 单词左右边界（左闭右开）
        while (r < n) {
            while (r < n && sb.charAt(r) != ' ') {
                ++r;
            }
            // 对于每个单词按要求处理
            if (r - l > 2) {
                sb.setCharAt(l, Character.toUpperCase(sb.charAt(l)));
                ++l;
            }
            while (l < r) {
                sb.setCharAt(l, Character.toLowerCase(sb.charAt(l)));
                ++l;
            }
            l = r + 1;
            ++r;
        }
        return sb.toString();
    }
}
