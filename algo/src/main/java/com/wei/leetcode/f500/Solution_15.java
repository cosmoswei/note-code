package com.wei.leetcode.f500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_15 {

    public static void main(String[] args) {
        Solution_15 solution = new Solution_15();
        int[] nums = {-2, 0, 1, 1, 2};
        List<List<Integer>> lists = solution.threeSum2(nums);
        System.out.println("lists = " + lists);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        int end = n - 2;
        for (int i = 0; i < end; i++) {
            int x = nums[i];
            if (i > 0 && x == nums[i - 1]) {
                continue;
            }
            if (x + nums[i + 1] + nums[i + 2] > 0) {
                break;
            }
            if (x + nums[n - 2] + nums[n - 1] < 0) {
                continue;
            }

            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int s = nums[j] + nums[k] + x;
                if (s < 0) {
                    j++;
                } else if (s > 0) {
                    k--;
                } else {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    k--;
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                }
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            int x = nums[i];
            if (i > 0 && x == nums[i - 1]) {
                continue;
            }
            if (x + nums[i + 1] + nums[i + 2] > 0) {
                break;
            }
            if (x + nums[n - 2] + nums[n - 1] < 0) {
                continue;
            }
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int s = nums[j] + nums[k] + x;
                if (s > 0) {
                    --k;
                } else if (s < 0) {
                    j++;
                } else {
                    res.add(Arrays.asList(x, nums[j], nums[k]));
                    for (++j; j < k && nums[j] == nums[j - 1]; j++) ;
                    for (--k; k > j && nums[k] == nums[k + 1]; k--) ;
                }
            }
        }
        return res;
    }
}

