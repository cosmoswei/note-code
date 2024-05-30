package com.wei.leetcode.f500;

import java.util.Arrays;

public class Solution_198 {

    public static void main(String[] args) {
        System.out.println("new Solution_198().rob(new int[]{1, 2, 3, 1}) = " + new Solution_198().rob(new int[]{1, 2, 3, 1}));
    }

    private int[] nums;
    private int[] memo;

    public int rob(int[] nums) {
        this.nums = nums;
        this.memo = new int[nums.length];
        Arrays.fill(this.memo, -1);
        return dfs(nums.length - 1);
    }

    private int dfs(int i) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        int res = Math.max(dfs(i - 1), dfs(i - 2) + nums[i]);
        memo[i] = res;
        return res;
    }
}
