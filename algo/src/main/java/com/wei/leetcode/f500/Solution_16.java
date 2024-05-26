package com.wei.leetcode.f500;

import java.util.Arrays;

public class Solution_16 {

    public static void main(String[] args) {
        int[] nums = {1, 1, -1};
        int res = threeSumClosest(nums, 2);
        System.out.println("res = " + res);
    }

    /**
     * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
     * 返回这三个数的和。
     *
     * @param nums
     * @param target
     * @return
     */
    static public int threeSumClosest(int[] nums, int target) {
        int minValue = Integer.MAX_VALUE;
        int res = 0;
        int n = nums.length - 1;
        int end = n - 1;
        Arrays.sort(nums);
        for (int i = 0; i < end; i++) {
            int x = nums[i];
            int left = i + 1;
            int right = n;
            if (i > 0 && x == nums[i - 1]) {
                continue; // 优化三
            }

            int s = x + nums[i + 1] + nums[i + 2];
            if (s > target) {
                if (s - target < minValue) {
                    res = s;
                }
                break;
            }
            s = x + nums[n - 1] + nums[n];
            if (s < target) {
                if (target - s < minValue) {
                    minValue = target - s;
                    res = s;
                }
                continue;
            }
            while (left < right) {
                s = x + nums[left] + nums[right];
                if (s == target) {
                    return s;
                }
                if (s > target) {
                    if (s - target < minValue) {
                        minValue = s - target;
                        res = s;
                    }
                    right--;
                } else {
                    if (minValue > target - s) {
                        minValue = target - s;
                        res = s;
                    }
                    left++;
                }
            }
        }
        return res;
    }
}


