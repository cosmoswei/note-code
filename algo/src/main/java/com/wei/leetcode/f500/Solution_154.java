package com.wei.leetcode.f500;

class Solution_154 {
    public static void main(String[] args) {
        Solution_154 solution = new Solution_154();
        int[] nums = {2, 2, 2, 0, 1};
        System.out.println("solution.findMin(nums) = " + solution.findMin(nums));
    }

    public int findMin(int[] nums) {
        int left = -1, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] > nums[right]) {
                left = mid;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                --right;
            }
        }
        return nums[right];
    }
}