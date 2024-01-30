package com.wei.leetcode.f3000;


import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution_2808 {

    public static void main(String[] args) {
        Solution_2808 solution = new Solution_2808();
        int i = solution.minimumSeconds(Lists.newArrayList(1, 2, 1, 2));
        System.out.println("i = " + i);
    }

    public int minimumSeconds(List<Integer> nums) {
        HashMap<Integer, List<Integer>> mp = new HashMap<>();
        int n = nums.size(), res = n;
        for (int i = 0; i < n; ++i) {
            mp.computeIfAbsent(nums.get(i), k -> new ArrayList<>()).add(i);
        }
        for (List<Integer> positions : mp.values()) {
            int mx = positions.get(0) + n - positions.get(positions.size() - 1);
            for (int i = 1; i < positions.size(); ++i) {
                mx = Math.max(mx, positions.get(i) - positions.get(i - 1));
            }
            res = Math.min(res, mx / 2);
        }
        return res;
    }
}

