package com.wei.leetcode.f500;

import java.util.*;

public class Solution_39 {


    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 6, 7};
        List<List<Integer>> res = new Solution_39().combinationSum(nums, 7);
        res.forEach(System.out::println);
    }

    List<List<Integer>> res = new ArrayList<>();

    LinkedList<Integer> track = new LinkedList<>();

    int trackSum = 0;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        backtrack(candidates, 0, target);
        return res;
    }

    private void backtrack(int[] candidates, int i, int target) {
        if (trackSum == target) {
            res.add(new ArrayList<>(track));
            return;
        }
        if (trackSum > target) {
            return;
        }

        for (int j = i; j < candidates.length; j++) {
            trackSum += candidates[j];
            track.add(candidates[j]);
            backtrack(candidates, j, target);
            trackSum -= candidates[j];
            track.removeLast();
        }
    }

    /**
     * 输入：candidates = [2,3,6,7], target = 7
     * 输出：[[2,2,3],[7]]
     * 解释：
     * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
     * 7 也是一个候选， 7 = 7 。
     * 仅有这两种组合。
     */
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }

    /**
     * @param candidates 候选数组
     * @param begin      搜索起点
     * @param len        冗余变量，是 candidates 里的属性，可以不传
     * @param target     每减去一个元素，目标值变小
     * @param path       从根结点到叶子结点的路径，是一个栈
     * @param res        结果集列表
     */
    private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        // target 为负数和 0 的时候不再产生新的孩子结点
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 重点理解这里从 begin 开始搜索的语意
        for (int i = begin; i < len; i++) {
            path.addLast(candidates[i]);
            // 注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            dfs(candidates, i, len, target - candidates[i], path, res);
            // 状态重置
            path.removeLast();
        }
    }
}
