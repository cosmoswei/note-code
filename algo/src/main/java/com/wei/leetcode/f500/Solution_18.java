package com.wei.leetcode.f500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_18 {

    public static void main(String[] args) {
        int[] nums = {2, 2, 2, 2};
        List<List<Integer>> lists = fourSum(nums, 8);
        lists.forEach(System.out::println);
    }

    static public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 3; i++) {
            long x = nums[i];
            if (x + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if (x + nums[len - 1] + nums[len - 2] + nums[len - 3] < target) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < len - 2; j++) {
                long y = nums[j];
                int left = j + 1;
                int right = len - 1;
                if (x + y + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if (x + y + nums[len - 2] + nums[len - 1] < target) {
                    continue;
                }
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                while (left < right) {
                    long sum = x + nums[left] + y + nums[right];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                    }
                    if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return res;
    }
}


