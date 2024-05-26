package com.wei.leetcode.f500;

public class Solution_209 {

    public static void main(String[] args) {
        Solution_209 solution = new Solution_209();
        int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;
        int res = minSubArrayLen(target, nums);
        System.out.println("res = " + res);
    }


    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * <p>
     * 找出该数组中满足其总和大于等于 target 的长度最小的 连续
     * 子数组
     * [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：target = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     */
    static public int minSubArrayLen(int target, int[] nums) {
        int res = nums.length + 1;
        int left = 0;
        int sum = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum - nums[left] >= target) {
                sum -= nums[left];
                left++;
            }
            if (sum >= target) {
                res = Math.min(res, right - left + 1);
            }
        }
        return res > nums.length ? 0 : res;
    }
}
