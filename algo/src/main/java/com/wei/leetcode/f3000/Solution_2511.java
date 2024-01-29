package com.wei.leetcode.f3000;


public class Solution_2511 {

    public static void main(String[] args) {
        Solution_2511 solution = new Solution_2511();
        int[] forts = {1, 0, 0, -1, 0, 0, 0, 0, 1};
        int i = solution.captureForts(forts);
        System.out.println("i = " + i);

    }

    public int captureForts(int[] forts) {
        int res = 0;
        int len = forts.length;
        int pre = -1;
        for (int i = 0; i < len; i++) {
            if (forts[i] == 1 || forts[i] == -1) {
                if (pre != -1 && forts[i] != forts[pre]) {
                    res = Math.max(res, i - pre - 1);
                }
                pre = i;
            }

        }
        return res;
    }
}

