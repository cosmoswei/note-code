package com.wei.leetcode.f3000;


import java.util.*;

public class Solution_2670 {

    public static void main(String[] args) {
        Solution_2670 solution = new Solution_2670();
        int[] forts = {3, 2, 3, 4, 2};
        int[] i = solution.distinctDifferenceArray2(forts);
        System.out.println("i = " + Arrays.toString(i));
    }

    public int[] distinctDifferenceArray(int[] nums) {
        int length = nums.length;

        int[] res = new int[length];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (int i = 0; i < length; i++) {
            int prefix = 1;
            int suffix = 0;
            for (int j = 0; j < i; j++) {
                prefix += map.get(nums[j]);
            }
            for (int j = i; j < length; j++) {
                suffix += map.get(nums[j]);
            }
            res[length - i - 1] = suffix - prefix;
        }
        return res;
    }

    public int[] distinctDifferenceArray2(int[] numb) {
        int n = numb.length;
        Set<Integer> set = new HashSet<>();
        int[] sufCnt = new int[n + 1];
        for (int i = n - 1; i > 0; i--) {
            set.add(numb[i]);
            sufCnt[i] = set.size();
        }
        int[] res = new int[n];
        set.clear();
        for (int i = 0; i < n; i++) {
            set.add(numb[i]);
            res[i] = set.size() - sufCnt[i + 1];
        }
        return res;
    }

}

