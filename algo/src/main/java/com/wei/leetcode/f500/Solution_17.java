package com.wei.leetcode.f500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_17 {

    public static void main(String[] args) {
        List<String> res = new Solution_17().letterCombinations("23");
        System.out.println("res = " + res);
    }


    String[] keyboard9 = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    List<String> res = new ArrayList<>();

    char[] path;
    char[] digits;

    /**
     * 示例 1：
     * <p>
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     */
    public List<String> letterCombinations(String digits) {
        int length = digits.length();
        if (length == 0) {
            return res;
        }
        this.digits = digits.toCharArray();
        path = new char[length];
        dfs(0);
        return res;
    }

    void dfs(int index) {
        if (index == digits.length) {
            res.add(new String(path));
            return;
        }
        char[] charArray = keyboard9[digits[index] - '0'].toCharArray();
        System.out.println("charArray = " + Arrays.toString(charArray));
        for (char c : charArray) {
            path[index] = c;
            dfs(index + 1);
        }
    }
}


