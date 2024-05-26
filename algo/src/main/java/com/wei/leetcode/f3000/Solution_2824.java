package com.wei.leetcode.f3000;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution_2824 {
    static public int countPairs(List<Integer> nums, int target) {
        int res = 0;
        int left = 0;
        int right = nums.size() - 1;
        Collections.sort(nums);
        while (left < right) {
            int s = nums.get(left) + nums.get(right);
            if (s < target) {
                res += right - left;
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Integer[] array = {-1, 1, 2, 3, 1};
        List<Integer> nums = Arrays.asList(array);
        int target = 2;
        int i = countPairs(nums, target);
        System.out.println("i = " + i);
//        Assert.isTrue(i == 3);
    }
}