package com.wei.leetcode.f3000;


import java.util.ArrayList;
import java.util.List;

public class Solution_2696 {
    public static void main(String[] args) {
        String s = "ABFCACDB";
        System.out.println("minLength(s) = " + minLength2(s));
    }

    public static int minLength(String s) {
        List<Character> stack = new ArrayList<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            stack.add(c);
            int m = stack.size();
            if (m >= 2 &&
                    (stack.get(m - 2) == 'A' && stack.get(m - 1) == 'B' ||
                            stack.get(m - 2) == 'C' && stack.get(m - 1) == 'D')) {
                stack.remove(m - 1);
                stack.remove(m - 2);
            }
        }
        return stack.size();
    }

    public static int minLength2(String s) {
        int i = 0, j = 1;

        int n = s.length();

        char[] cs = s.toCharArray();

        int ans = n;

        int min = 0;

        while (j < n) {
            if ((cs[i] == 'A' && cs[j] == 'B') || (cs[i] == 'C' && cs[j] == 'D')) {
                cs[i] = '#';
                cs[j] = '#';

                if (i == min) {
                    min = j + 1;

                    i = j + 1;
                    j = j + 2;
                } else {
                    i -= 1;

                    while (i >= 0 && cs[i] == '#') {
                        i -= 1;
                    }

                    j += 1;
                }

                ans -= 2;
            } else if (j == i + 1) {
                i++;
                j++;
            } else {
                i = j;
                j = j + 1;
            }
        }

        return ans;
    }


}
