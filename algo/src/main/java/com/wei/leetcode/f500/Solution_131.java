package com.wei.leetcode.f500;

import java.util.ArrayList;
import java.util.List;

class Solution_131 {

    List<List<String>> res = new ArrayList<>();
    List<String> path = new ArrayList<>();
    String s;

    public List<List<String>> partition(String s) {
        this.s = s;
//        dfs(0, 0);
        dfs2(0);
        return res;
    }

    // start 表示当前这段回文子串的开始位置，i表示逗号的位置
    private void dfs(int i, int start) {
        if (i == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 不选 i 和 i+1 之间的逗号（i=n-1 时一定要选）
        if (i < s.length() - 1) {
            dfs(i + 1, start);
        }

        // 选 i 和 i+1 之间的逗号（把 s[i] 作为子串的最后一个字符）
        if (isPalindrome(start, i)) {
            path.add(s.substring(start, i + 1));
            dfs(i + 1, i + 1);
            path.remove(path.size() - 1);
        }
    }


    private void dfs2(int i) {
        if (i == s.length()) {
            res.add(new ArrayList<>(path)); // 复制 path
            return;
        }
        for (int j = i; j < s.length(); ++j) { // 枚举子串的结束位置
            if (isPalindrome(i, j)) {
                path.add(s.substring(i, j + 1));
                dfs2(j + 1);
                path.remove(path.size() - 1); // 恢复现场
            }
        }
    }

    private boolean isPalindrome(int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution_131 solution = new Solution_131();
        String str = "aab";
        List<List<String>> res = solution.partition(str);
        res.forEach(System.out::println);
    }
}