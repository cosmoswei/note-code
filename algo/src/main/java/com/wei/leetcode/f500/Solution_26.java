package com.wei.leetcode.f500;

public class Solution_26 {

    public static void main(String[] args) {
        Solution_26 solution26 = new Solution_26();
        int[] nums = {1, 2, 3, 4, 5, 5, 6, 7, 7, 7, 7, 8,9,10,11,12,13,14,15};
        int i = solution26.removeDuplicates(nums);
        System.out.println("i = " + i);
    }

    int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                // 维护 nums[0..slow] 无重复
                nums[slow] = nums[fast];
            }
            fast++;
        }
        // 数组长度为索引 + 1
        return slow + 1;
    }
}
