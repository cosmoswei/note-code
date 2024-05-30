package com.wei.leetcode.f500;

import java.util.Arrays;

public class Solution_494 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 9;
        System.out.println("new Solution_494().findTargetSumWays(nums,target) = " + new Solution_494().findTargetSumWays(nums, target));
    }


    private int[] nums;
    private int[][] memo;

    public int findTargetSumWays(int[] nums, int target) {
        for (int num : nums) {
            target += num;
        }
        if (target < 0 || target % 2 == 1) {
            return 0;
        }
        target /= 2;
        this.nums = nums;
        int n = nums.length;
        memo = new int[n][target + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(n - 1, target);
    }

    int dfs(int i, int c) {
        if (i < 0) {
            return c == 0 ? 1 : 0;
        }
        if (memo[i][c] != -1) {
            return memo[i][c];
        }
        if (c < nums[i]) {
            return memo[i][c] = dfs(i - 1, c);
        }
        return memo[i][c] = dfs(i - 1, c) + dfs(i - 1, c - nums[i]);
    }
}
