package com.wei.leetcode.f500;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCode1 {

    public static void main(String[] args) {

        LeetCode1 leetCode1 = new LeetCode1();
        int[] nums = {2, 7, 11, 15};
        int target = 26;
        System.out.println(Arrays.toString(leetCode1.twoSum(nums, target)));
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int sub = target - nums[i];
            Integer o = map.get(sub);
            if (null != o) {
                return new int[]{i, o};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{};
    }
}
