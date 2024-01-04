package com.wei.leetcode.f500;

public class Solution_283 {

    public static void main(String[] args) {
        Solution_283 solution26 = new Solution_283();
        int[] nums = {0, 1, 0, 3, 12};
        solution26.moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println("nums = " + nums[i]);
        }
    }

    public void moveZeroes(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
