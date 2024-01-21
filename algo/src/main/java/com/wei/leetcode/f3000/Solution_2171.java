package com.wei.leetcode.f3000;


import java.util.Arrays;

public class Solution_2171 {

    public static void main(String[] args) {
        Solution_2171 solution = new Solution_2171();
        int[] param = {4, 1, 6, 5};
        long i = solution.minimumRemoval2(param);
        System.out.println("i = " + i);
    }
    public long minimumRemoval2(int[] beans) {
        int n = beans.length;
        Arrays.sort(beans);
        long total = 0; // 豆子总数
        for (int i = 0; i < n; i++) {
            total += beans[i];
        }
        long res = total; // 最少需要移除的豆子数
        for (int i = 0; i < n; i++) {
            res = Math.min(res, total - (long) beans[i] * (n - i));
        }
        return res;
    }
}


