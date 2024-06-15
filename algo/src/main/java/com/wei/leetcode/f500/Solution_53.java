package com.wei.leetcode.f500;

public class Solution_53 {

    public static void main(String[] args) {
        Solution_53 solution = new Solution_53();
        int[] nums = new int[]{-3, 1, 3, -1, 2, -4, 2};
        int res = solution.maxSubArray3(nums);
        System.out.println("res = " + res);
    }

    public int maxSubArray(int[] nums) {
        int left = 0;
        int right = 0;
        int windSum = 0;
        int maxSum = Integer.MIN_VALUE;
        while (right < nums.length) {
            windSum += nums[right];
            right++;
            maxSum = Math.max(maxSum, windSum);
            while (windSum < 0) {
                windSum -= nums[left];
                left++;
            }
        }
        return maxSum;
    }

    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        int res = Integer.MIN_VALUE;
        for (int i = n - 2; i >= 0; i--) {
            res = Math.max(res, dp[i]);

        }
        return res;
    }

    public int maxSubArray3(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        int res = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            minVal = Math.min(minVal, preSum[i]);
            res = Math.max(res, preSum[i - 1] - minVal);
        }
        return res;
    }
}
