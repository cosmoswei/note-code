package com.wei.leetcode.f2000;

import java.util.HashSet;
import java.util.Set;

class Solution_1695 {

    public static void main(String[] args) {
        int[] nums = {187, 470, 25, 436, 538, 809, 441, 167, 477, 110, 275, 133, 666, 345, 411, 459, 490, 266, 987, 965, 429, 166, 809, 340, 467, 318, 125, 165, 809, 610, 31, 585, 970, 306, 42, 189, 169, 743, 78, 810, 70, 382, 367, 490, 787, 670, 476, 278, 775, 673, 299, 19, 893, 817, 971, 458, 409, 886, 434};
        Solution_1695 solution = new Solution_1695();
        int i = solution.maximumUniqueSubarray(nums);
        System.out.println("i = " + i);
    }

    /**
     * 给你一个正整数数组 nums ，请你从中删除一个含有 若干不同元素 的子数组。删除子数组的 得分 就是子数组各元素之 和 。
     * 返回 只删除一个 子数组可获得的 最大得分 。
     * 如果数组 b 是数组 a 的一个连续子序列，即如果它等于 a[l],a[l+1],...,a[r] ，那么它就是 a 的一个子数组。
     * 示例 1：
     * 输入：nums = [4,2,4,5,6]
     * 输出：17
     * 解释：最优子数组是 [2,4,5,6]
     */
    public int maximumUniqueSubarray(int[] nums) {
        int res = 0;
        int sum = 0;
        int left = 0;
        Set<Integer> list = new HashSet<>();
        for (int num : nums) {
            if (!list.contains(num)) {
                list.add(num);
                sum += num;
                res = Math.max(sum, res);
            } else {
                while (num != nums[left]) {
                    sum -= nums[left];
                    list.remove(nums[left]);
                    left++;
                }
                left++;
            }
        }
        return res;
    }
}

