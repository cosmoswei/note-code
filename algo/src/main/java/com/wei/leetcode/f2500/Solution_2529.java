package com.wei.leetcode.f2500;

class Solution_2529 {
    public int maximumCount(int[] nums) {
        int pos1 = lowerBound(nums, 0);
        int pos2 = lowerBound(nums, 1);
        return Math.max(pos1, nums.length - pos2);
    }

    public int lowerBound(int[] nums, int val) {
        int l = 0, r = nums.length;
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] >= val) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
}