package com.wei.leetcode.f1500;

import com.wei.leetcode.Difference;

public class Solution_1109 {

    public static void main(String[] args) {
        int[][] nums = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        Solution_1109 solution370 = new Solution_1109();
        System.out.println("Solution_1109.corpFlightBookings(nums,5 ) = "
                + solution370.corpFlightBookings(nums, 5));
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        // nums 初始化为全 0
        int[] nums = new int[n];
        // 构造差分解法
        Difference df = new Difference(nums);

        for (int[] booking : bookings) {
            // 注意转成数组索引要减一哦
            int i = booking[0] - 1;
            int j = booking[1] - 1;
            int val = booking[2];
            // 对区间 nums[i..j] 增加 val
            df.increment(i, j, val);
        }
        // 返回最终的结果数组
        return df.result();
    }

    int[] getModifiedArray(int length, int[][] updates) {
        // nums 初始化为全 0
        int[] nums = new int[length];
        // 构造差分解法
        Difference df = new Difference(nums);

        for (int[] update : updates) {
            int i = update[0];
            int j = update[1];
            int val = update[2];
            df.increment(i, j, val);
        }

        return df.result();
    }

}
