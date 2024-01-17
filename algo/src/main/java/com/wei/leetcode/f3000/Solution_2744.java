package com.wei.leetcode.f3000;


import java.util.ArrayList;
import java.util.List;

public class Solution_2744 {

    public static void main(String[] args) {
        Solution_2744 solution = new Solution_2744();
        String[] param = {"cd", "ac", "dc", "ca", "zz"};
        int i = solution.maximumNumberOfStringPairs(param);
        System.out.println("i = " + i);
    }

    public int maximumNumberOfStringPairs(String[] words) {
        int res = 0;
        List<String> strings = new ArrayList<>();
        for (String word : words) {
            if (strings.contains(word)) {
                res++;
            }
            String resoveStr = getFlipsStr(word);
            strings.add(resoveStr);
        }
        return res;
    }

    private String getFlipsStr(String src) {
        if (null == src) {
            return src;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = src.length() - 1; i >= 0; i--) {
            char charAt = src.charAt(i);
            stringBuilder.append(charAt);
        }
        return stringBuilder.toString();
    }
}


