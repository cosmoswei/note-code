package com.wei.leetcode.f3000;


import java.util.Stack;

public class Solution_2645 {

    public static void main(String[] args) {
        int i = addMinimum0("aabc");
        System.out.println("i = " + i);
    }

    public static int addMinimum(String word) {
        if (null == word || word.isEmpty()) {
            return 3;
        }
        int length = word.length();
        int res = 1;
        for (int i = 1; i < length; i++) {
            if (word.charAt(i) <= word.charAt(i - 1)) {
                res++;
            }
        }

        return res * 3 - length;
    }

    public static int addMinimum0(String word) {
        int n = word.length();
        int[] d = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            d[i] = d[i - 1] + 2;
            if (i > 1 && word.charAt(i - 1) > word.charAt(i - 2)) {
                d[i] = d[i - 1] - 1;
            }
        }
        return d[n];
    }


    public static int addMinimum3(String word) {
        if (null == word || word.isEmpty()) {
            return 3;
        }
        int length = word.length();
        int res = 1;
        for (int i = 1; i < length; i++) {
            if (word.charAt(i) <= word.charAt(i - 1)) {
                res++;
            }
        }

        return res * 3 - length;
    }

    public static int addMinimum2(String word) {
        Stack<Character> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < word.length(); i++) {
            int count = 0;
            while (!stack.isEmpty() && word.charAt(i) <= stack.peek()) {
                stack.pop();
                count++;
            }
            if (count > 0)
                res += 3 - count;
            stack.push(word.charAt(i));
        }
        if (!stack.isEmpty()) {
            res += 3 - stack.size();
        }
        return res;
    }
}

