package com.wei.leetcode.f3000;

import java.util.HashMap;
import java.util.Map;

class Solution_2958 {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 3};
        int k = 2;
        int res = maxSubArrayLength3(nums, k);
        System.out.println("res = " + res);
    }

    /**
     * 给你一个整数数组 nums 和一个整数 k 。
     * 一个元素 x 在数组中的 频率 指的是它在数组中的出现次数。
     * 如果一个数组中所有元素的频率都 小于等于 k ，那么我们称这个数组是 好 数组。
     * 请你返回 nums 中 最长好 子数组的长度。
     * 子数组 指的是一个数组中一段连续非空的元素序列。
     * 示例 1：
     * 输入：nums = [1,2,3,1,2,3,1,2], k = 2
     * 输出：6
     * 解释：最长好子数组是 [1,2,3,1,2,3] ，值 1 ，2 和 3 在子数组中的频率都没有超过 k = 2 。[2,3,1,2,3,1] 和 [3,1,2,3,1,2] 也是好子数组。
     * 最长好子数组的长度为 6 。
     * 题解模仿版
     */
    static public int maxSubArrayLength2(int[] nums, int k) {
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int length = nums.length;
        for (int right = 0; right < length; right++) {
//            map.merge(nums[right], 1, Integer::sum);
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            while (map.get(nums[right]) > k) {
//                map.merge(nums[left], -1, Integer::sum);
                map.put(nums[left], map.get(nums[left]) - 1);
                left++;
            }
            result = Math.max(result, right - left + 1);
        }
        return result;
    }


    /**
     * 修正版
     * 要在循环内部获取、更新map.get(nums[right])的值，因为要把前面的影响因素清理干净
     */
    static public int maxSubArrayLength3(int[] nums, int k) {
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int length = nums.length;
        for (int right = 0; right < length; right++) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            while (map.getOrDefault(nums[right], 0) > k && left < right) {
                map.put(nums[left], map.getOrDefault(nums[left], 0) - 1);
                left++;
            }
            result = Math.max(result, right - left + 1);
        }
        return result;
    }

    /**
     * 题解版
     *
     * @param nums
     * @param k
     * @return
     */
    static public int maxSubArrayLength(int[] nums, int k) {
        int ans = 0, left = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int right = 0; right < nums.length; right++) {
            cnt.merge(nums[right], 1, Integer::sum);
            while (cnt.get(nums[right]) > k) {
                cnt.merge(nums[left++], -1, Integer::sum);
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

}