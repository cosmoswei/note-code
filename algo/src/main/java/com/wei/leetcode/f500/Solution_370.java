package com.wei.leetcode.f500;

import com.wei.leetcode.Difference;

public class Solution_370 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 5, 6, 7, 7, 7, 7, 8, 8, 8, 9};
        Solution_370 solution370 = new Solution_370();
        for (int i : solution370.getDiff(nums)) {
            System.out.println("i = " + i);
        }
    }

    private int[] getDiff(int[] nums) {
        int[] diff = new int[nums.length];
        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] - diff[i - 1];
        }
        return diff;
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
