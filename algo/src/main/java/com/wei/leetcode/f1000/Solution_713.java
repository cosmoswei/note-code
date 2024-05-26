package com.wei.leetcode.f1000;

class Solution_713 {
    public static void main(String[] args) {
        int[] nums = {10, 5, 2, 6};
        int k = 100;
        int res = numSubarrayProductLessThanK(nums, k);
        System.out.println(res);
    }

    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
     * 示例 1：
     * 输入：nums = [10,5,2,6], k = 100
     * 输出：8
     * 解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2],、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
     * 需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
     */
    static public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int res = 0;
        int left = 0;
        int multi = 1;// 乘积
        for (int right = 0; right < nums.length; right++) {
            multi = multi * nums[right];
            while (multi >= k) {
                multi /= nums[left];
                left += 1;
            }
            res += right - left + 1;
        }
        return res;
    }
}