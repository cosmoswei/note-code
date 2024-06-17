package com.wei.leetcode.f500;

import java.util.HashMap;
import java.util.Map;

public class Solution_169 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9,9};
        Solution_169 solution = new Solution_169();
        System.out.println("solution.majorityElement(nums) = "
                + solution.majorityElement(nums));
    }


    public int majorityElement(int[] sums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < sums.length; i++) {
            map.put(sums[i], map.getOrDefault(sums[i], 0) + 1);
        }
        int res = 0;
        int max = 0;
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            Integer key = integerIntegerEntry.getKey();
            Integer value = integerIntegerEntry.getValue();
            if (value > max) {
                res = key;
                max = value;
            }
        }
        return res;
    }

}
