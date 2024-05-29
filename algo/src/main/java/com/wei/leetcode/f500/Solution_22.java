package com.wei.leetcode.f500;

import java.util.ArrayList;
import java.util.List;

public class Solution_22 {

    public static void main(String[] args) {
        Solution_22 solution22 = new Solution_22();
        List<String> res = solution22.generateParenthesis(3);
        res.forEach(System.out::println);
    }


    private int n = 0;
    private char[] path;
    private List<String> res = new ArrayList<>();

    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * 示例 1：
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     */
    public List<String> generateParenthesis(int n) {
        this.n = n;
        path = new char[n * 2];
        dfs(0, 0);
        return res;
    }


    void dfs(int i, int open) {
        if (i == n * 2) {
            res.add(new String(path));
            return;
        }
        if (open < n) {
            path[i] = '(';
            dfs(i + 1, open + 1);
        }
        if (i - open < open) {
            path[i] = ')';
            dfs(i + 1, open);
        }
    }


}
