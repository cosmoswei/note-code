package com.wei.leetcode.f1000;

public class Solution_700 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int search = search(nums, 4);
        System.out.println("search = " + search);
    }

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }
}
