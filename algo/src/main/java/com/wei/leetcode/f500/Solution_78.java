package com.wei.leetcode.f500;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution_78 {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(subsets(nums));
    }

    static List<List<Integer>> res = new ArrayList<>();

    static LinkedList<Integer> track = new LinkedList<>();

    static public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    static void backtrack(int[] nums, int start) {
        System.out.println("track = " + track);
        res.add(new ArrayList<>(track));
        System.out.println("start = " + start);
        for (int i = start; i < nums.length; i++) {
            track.add(nums[i]);
            backtrack(nums, i + 1);
            track.removeLast();
        }
    }
}
