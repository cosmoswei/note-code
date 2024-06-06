package com.wei.leetcode.f500;

import java.util.ArrayList;
import java.util.List;

public class Solution_442 {

    public static void main(String[] args) {
        Solution_442 solution = new Solution_442();
        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println("new Solution_442().findDuplicates(nums) = "
                + new Solution_442().findDuplicates(nums));
    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            while (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < n; ++i) {
            if (nums[i] - 1 != i) {
                res.add(nums[i]);
            }
        }
        return res;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
