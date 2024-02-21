package com.wei.leetcode.f3000;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_2788 {

    public static void main(String[] args) {
        Solution_2788 solution = new Solution_2788();
        List<String> req = Arrays.asList("$easy$", "$problem$");
        List<String> res = solution.splitWordsBySeparator(req, '$');
        res.forEach(System.out::println);
    }

    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> res = new ArrayList<>();
        int start = 0;
        int end = 0;
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char charAt = word.charAt(i);
                if (charAt == separator) {
                    String substring = word.substring(start, end);
                    if (substring.length() != 0) {
                        res.add(substring);
                    }
                    start = i + 1;
                }
                if (i == word.length() - 1) {
                    String substring = word.substring(start, end + 1);
                    if (substring.length() != 0) {
                        res.add(substring);
                    }
                }
                end++;
            }
            start = 0;
            end = 0;
        }
        return res;
    }
}


