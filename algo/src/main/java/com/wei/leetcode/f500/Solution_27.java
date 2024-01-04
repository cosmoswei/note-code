package com.wei.leetcode.f500;

public class Solution_27 {

    public static void main(String[] args) {
        Solution_27 solution26 = new Solution_27();
        int[] nums = {3, 2, 1, 3};
        int i = solution26.removeElement2(nums, 3);
        System.out.println("i = " + i);
    }


    int removeElement1(int[] nums, int val) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    int removeElement2(int[] nums, int val) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
