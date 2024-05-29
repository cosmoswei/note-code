package com.wei.leetcode.f2500;


import java.util.*;
import java.util.stream.Collectors;

public class Solution_2225 {

    public static void main(String[] args) {
        Solution_2225 solution = new Solution_2225();
        int[][] nums = {{1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5}, {4, 8}, {4, 9}, {10, 4}, {10, 9}};
        List<List<Integer>> res = solution.findWinners(nums);
        System.out.println("res = " + res);
    }

    /**
     * 给你一个整数数组 matches 其中 matches[i] = [winneri, loseri] 表示在一场比赛中 winneri 击败了 loseri 。
     * 返回一个长度为 2 的列表 answer ：
     * answer[0] 是所有 没有 输掉任何比赛的玩家列表。
     * answer[1] 是所有恰好输掉 一场 比赛的玩家列表。
     * 两个列表中的值都应该按 递增 顺序返回。
     * 注意：
     * 只考虑那些参与 至少一场 比赛的玩家。
     * 生成的测试用例保证 不存在 两场比赛结果 相同 。
     * 示例 1：
     * 输入：matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
     * 输出：[[1,2,10],[4,5,7,8]]
     * 解释：
     * 玩家 1、2 和 10 都没有输掉任何比赛。
     * 玩家 4、5、7 和 8 每个都输掉一场比赛。
     * 玩家 3、6 和 9 每个都输掉两场比赛。
     * 因此，answer[0] = [1,2,10] 和 answer[1] = [4,5,7,8] 。
     */
    public List<List<Integer>> findWinners(int[][] matches) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> winner = new ArrayList<>();
        List<Integer> loserList = new ArrayList<>();
        Map<Integer, Integer> loserMap = new HashMap<>();
        for (int[] match : matches) {
            int win = match[0];
            int loss = match[1];
            winner.add(win);
            loserMap.put(loss, loserMap.getOrDefault(loss, 0) + 1);
        }
        Set<Integer> loserKeySet = loserMap.keySet();
        winner.removeIf(loserKeySet::contains);
        for (Integer i : loserKeySet) {
            Integer i1 = loserMap.get(i);
            if (i1 < 2) {
                loserList.add(i);
            }
        }
        res.add(winner.stream().distinct().sorted().collect(Collectors.toList()));
        res.add(loserList.stream().sorted().collect(Collectors.toList()));
        return res;
    }
}
