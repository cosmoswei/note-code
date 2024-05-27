package com.wei.leetcode.f500;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution_47 {

    public static void main(String[] args) {
        Solution_47 solution = new Solution_47();
        int[] int1 = {1, 2, 2, 2};
        List<List<Integer>> res = solution.permuteUnique(int1);
        System.out.println("res" + res);
    }

    List<List<Integer>> res = new LinkedList<>();
    // 记录「路径」
    LinkedList<Integer> track = new LinkedList<>();

    /* 主函数，输入一组不重复的数字，返回它们的全排列 */
    List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        // 「路径」中的元素会被标记为 true，避免重复使用
        boolean[] used = new boolean[nums.length];
        backtrack(nums, track, used);
        return res;
    }

    // 路径：记录在 track 中
    // 选择列表：nums 中不存在于 track 的那些元素（used[i] 为 false）
    // 结束条件：nums 中的元素全都在 track 中出现
    void backtrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
        // 触发结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选择
            if (used[i]) {
                // nums[i] 已经在 track 中，跳过
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            // 做选择
            track.add(nums[i]);
            used[i] = true;
            // 进入下一层决策树
            backtrack(nums, track, used);
            // 取消选择
            track.removeLast();
            used[i] = false;
        }
    }
}
