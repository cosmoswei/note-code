package com.wei.leetcode.f3500;

class Solution_3038 {

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 4, 5};
        System.out.println("new Solution_3038().maxOperations(nums) = "
                + new Solution_3038().maxOperations(nums));
    }

    public int maxOperations(int[] nums) {
        final int n = nums.length;
        if (n < 2) {
            return 0;
        }
        int first = nums[0] + nums[1];
        int res = 1;
        for (int i = 2; i < n - 1; i += 2) {
            int num1 = nums[i];
            int num2 = nums[i + 1];
            if (num1 + num2 != first) {
                break;
            }
            res++;
        }
        return res;
    }
}