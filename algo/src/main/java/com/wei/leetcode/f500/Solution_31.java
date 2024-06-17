package com.wei.leetcode.f500;

import java.util.Arrays;

class Solution_31 {
    public static void main(String[] args) {
        Solution_31 solution = new Solution_31();
        int[] nums = {1, 1, 1, 1, 1, 2, 1, 1, 1};
        solution.nextPermutation(nums);
        System.out.println("solution.nextPermutation(nums) = " + Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return;
        }
        int pos = -1;
        for (int i = n - 1; i >= 1; i--) {
            if (nums[i - 1] < nums[i]) {
                pos = i - 1;
                break;
            }
        }
        if (pos == -1) {
            reverse(nums, 0);
            return;
        }
        for (int i = n - 1; i > pos; i--) {
            if (nums[pos] < nums[i]) {
                swap(nums, pos, i);
                break;
            }
        }
        reverse(nums, pos + 1);
    }

    private void reverse(int[] nums, int pos) {
        for (int i = pos, j = nums.length - 1; i < j; i++, j--) {
            swap(nums, j, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}