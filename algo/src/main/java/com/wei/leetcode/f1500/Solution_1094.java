package com.wei.leetcode.f1500;

import com.wei.leetcode.Difference;

public class Solution_1094 {

    public static void main(String[] args) {
        int[][] nums = {{2, 1, 5}, {3, 3, 7}};
        Solution_1094 solution370 = new Solution_1094();
        System.out.println("Solution_1094.carPooling(nums,4 ) = "
                + solution370.carPooling(nums, 4));
    }

    public boolean carPooling1(int[][] trips, int capacity) {
        return true;
    }

    boolean carPooling(int[][] trips, int capacity) {
        // 最多有 1001 个车站
        int[] nums = new int[1001];
        // 构造差分解法
        Difference df = new Difference(nums);

        for (int[] trip : trips) {
            // 乘客数量
            int val = trip[0];
            // 第 trip[1] 站乘客上车
            int i = trip[1];
            // 第 trip[2] 站乘客已经下车，
            // 即乘客在车上的区间是 [trip[1], trip[2] - 1]
            int j = trip[2] - 1;
            // 进行区间操作
            df.increment(i, j, val);
        }

        int[] res = df.result();

        // 客车自始至终都不应该超载
        for (int i = 0; i < res.length; i++) {
            if (capacity < res[i]) {
                return false;
            }
        }
        return true;
    }
}
