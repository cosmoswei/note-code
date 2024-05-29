package com.wei.leetcode.f3000;

import java.util.ArrayList;
import java.util.List;

class Solution_2951 {

    public static void main(String[] args) {
        int[] nums = {2, 4, 4};
        List<Integer> peaks = new Solution_2951().findPeaks(nums);
        System.out.println("peaks = " + peaks);
    }

    /**
     * 给你一个下标从 0 开始的数组 mountain 。你的任务是找出数组 mountain 中的所有 峰值。
     * 以数组形式返回给定数组中 峰值 的下标，顺序不限 。
     * 注意：
     * 峰值 是指一个严格大于其相邻元素的元素。
     * 数组的第一个和最后一个元素 不 是峰值。
     * 示例 1：
     * 输入：mountain = [2,4,4]
     * 输出：[]
     * 解释：mountain[0] 和 mountain[2] 不可能是峰值，因为它们是数组的第一个和最后一个元素。
     * mountain[1] 也不可能是峰值，因为它不严格大于 mountain[2] 。
     * 因此，答案为 [] 。
     */
    public List<Integer> findPeaks(int[] mountain) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < mountain.length - 1; i++) {
            if (i == mountain.length - 1) {
                continue;
            }
            if (mountain[i] > mountain[i -1 ]&&mountain[i] > mountain[i + 1]) {
                res.add(i);
            }

        }
        return res;
    }
}