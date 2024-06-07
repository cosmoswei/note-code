package com.wei.leetcode.f500;

class Solution_153 {
    public static void main(String[] args) {
        Solution_153 solution = new Solution_153();
        int[] nums = {3, 4, 5, 1, 2};
        System.out.println("solution.findMin(nums) = " + solution.findMin(nums));
    }

    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid - 1;
            }
        }
        return nums[left];
    }
}