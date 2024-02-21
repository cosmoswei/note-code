package com.wei.leetcode.f500;


public class Solution_303 {

    private static int[] nums;

    private int[] preSum;

//    public Solution_303(int[] nums) {
//        this.nums = nums;
//    }

    public Solution_303(int[] nums) {
        preSum = new int[nums.length + 1];
        for (int i = 1; i < nums.length + 1; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
    }


    public static void main(String[] args) {

        int[] nums = {2, 7, 11, 15};
        Solution_303 solution303 = new Solution_303(nums);
        System.out.println("sumRange = " + solution303.sumRange(1, 3));
    }

    public int sumRange(int left, int right) {
        return preSum[right + 1] - preSum[left];
    }
}
