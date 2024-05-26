package com.wei.leetcode.f500;

public class Solution_11 {

    public static void main(String[] args) {
        Solution_11 solution = new Solution_11();
        int[] nums = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int res = solution.maxArea(nums);
        System.out.println("res = " + res);
    }

    public int maxArea(int[] height) {
        int res = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int min = Math.min(height[left], height[right]);
            int area = min * (right - left);
            res = Math.max(res, area);
            if (height[left] < height[right]) {
                left++;
            } else right--;
        }
        return res;
    }
}


