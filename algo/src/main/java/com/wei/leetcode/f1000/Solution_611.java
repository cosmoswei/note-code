package com.wei.leetcode.f1000;

import java.util.Arrays;

public class Solution_611 {

    public static void main(String[] args) {
        Solution_611 solution = new Solution_611();
        int[] nums = {2, 2, 3, 4};
        int res = solution.triangleNumber(nums);
        System.out.println("i = " + res);
    }

    public int triangleNumber(int[] nums) {
        int res = 0;
        int len = nums.length;
        if (len < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 2; i < len; i++) {
            int x = nums[i];
            if (nums[len - 1] + nums[len - 2] <= x) {
                continue;
            }
            if (nums[0] + nums[1] > x) {
                res += ((i + 1) * i * (i - 1)) / 6;
                continue;
            }
            int left = 0;
            int right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > x) {
                    res += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }

    public int triangleNumber2(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int k = 2; k < nums.length; k++) {
            int c = nums[k];
            int i = 0; // a=nums[i]
            int j = k - 1; // b=nums[j]
            while (i < j) {
                if (nums[i] + nums[j] > c) {
                    // 由于 nums 已经从小到大排序
                    // nums[i]+nums[j] > c 同时意味着：
                    // nums[i+1]+nums[j] > c
                    // nums[i+2]+nums[j] > c
                    // ...
                    // nums[j-1]+nums[j] > c
                    // 从 i 到 j-1 一共 j-i 个
                    ans += j - i;
                    j--;
                } else {
                    // 由于 nums 已经从小到大排序
                    // nums[i]+nums[j] <= c 同时意味着
                    // nums[i]+nums[j-1] <= c
                    // ...
                    // nums[i]+nums[i+1] <= c
                    // 所以在后续的内层循环中，nums[i] 不可能作为三角形的边长，没有用了
                    i++;
                }
            }
        }
        return ans;
    }
}
