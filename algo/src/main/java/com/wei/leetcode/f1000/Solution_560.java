package com.wei.leetcode.f1000;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_560 {

    public static void main(String[] args) {
        Solution_560 solution = new Solution_560();
        int[] nums = new int[]{1, 1, 1, 1};
        System.out.println("solution = "
                + solution.subarraySum(nums, 2));
    }

    public int subarraySum(int[] nums, int k) {
        int result = 0;
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        System.out.println("sum = " + Arrays.toString(sum));
        Map<Integer, Integer> map = new HashMap<>(n + 1);
        for (int sj : sum) {
            result += map.getOrDefault(sj - k, 0);
            map.merge(sj, 1, Integer::sum);
        }
        return result;
    }
}
