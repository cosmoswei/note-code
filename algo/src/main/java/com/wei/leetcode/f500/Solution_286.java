package com.wei.leetcode.f500;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution_286 {
    public int missingNumber(int[] nums) {
        List<Integer> collect = Arrays.stream(nums).boxed().sorted().collect(Collectors.toList());
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (collect.get(i) != i) {
                return i;
            }
        }
        return nums.length;
    }

    public static void main(String[] args) {
        Solution_286 test = new Solution_286();
        int[] num = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println(test.officialSolution3(num));
    }

    public int officialSolution(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }
        int missing = -1;
        for (int i = 0; i <= n; i++) {
            if (!set.contains(i)) {
                missing = i;
                break;
            }
        }
        return missing;
    }

    public int officialSolution2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return n;
    }

    public int officialSolution3(int[] nums) {
        int xor = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            xor ^= nums[i];
        }
        for (int i = 0; i <= n; i++) {
            xor ^= i;
        }
        return xor;
    }
}

