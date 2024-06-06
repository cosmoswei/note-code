package com.wei.leetcode.f500;

public class Solution_268 {

    public static void main(String[] args) {

    }

    public int missingNumber(int[] nums) {
        int n = nums.length;
        int expected = (n + 0) * (n + 1) / 2;
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return (int) (expected - sum);
    }


    public int missingNumber2(int[] nums) {
        int n = nums.length;
        int res = 0;
        res ^= n;
        for (int i = 0; i < n; i++) {
            res ^= i ^ nums[i];
        }
        return res;
    }
}
