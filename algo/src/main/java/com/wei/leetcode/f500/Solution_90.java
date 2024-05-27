package com.wei.leetcode.f500;

import java.util.ArrayList;
import java.util.List;

class Solution_90 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        System.out.println(new Solution_90().subsetsWithDup(nums));
    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> track = new ArrayList<>();

    /**
     * 相关标签
     * 相关企业
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的
     * 子集
     * （幂集）。
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,2]
     * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    private void backtrack(int[] nums, int start) {

        res.add(new ArrayList<>(track));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            track.add(nums[i]);
            backtrack(nums, i + 1);
            track.remove(track.size() - 1);
        }
    }
}