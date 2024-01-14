package com.wei.leetcode.f2500;


public class Solution_2182 {

    public static void main(String[] args) {
        Solution_2182 solution2085 = new Solution_2182();
        String res = solution2085.repeatLimitedString("cczazcc", 3);
        System.out.println("i = " + res);
    }

    public String repeatLimitedString(String s, int repeatLimit) {
        int max = 26;
        int[] count = new int[max];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        int m = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = max - 1, j = max - 2; i >= 0 && j >= 0; ) {
            if (count[i] == 0) {
                m = 0;
                i--;
            } else if (m < repeatLimit) {
                count[i]--;
                stringBuilder.append((char) ('a' + i));
                m++;
            } else if (j >= i || count[j] == 0) {
                j--;
            } else {
                count[j]--;
                stringBuilder.append((char) ('a' + j));
                m = 0;
            }
        }

        return stringBuilder.toString();
    }
}
