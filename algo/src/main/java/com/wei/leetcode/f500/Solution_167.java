package com.wei.leetcode.f500;

class Solution_167 {

    public static void main(String[] args) {
        Solution_167 solution128 = new Solution_167();
//        int[] nums = {100, 4, 200, 1, 3, 2};
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int i[] = solution128.twoSum2(nums, 5);
        for (int j = 0; j < i.length; j++) {
            System.out.println("i = " + i[j]);
        }
    }

    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            int sum = numbers[left] + numbers[right];
            if (target == sum) {
                return new int[]{left + 1, right + 1};
            } else if (target > sum) {
                right--;
            } else if (target < sum) {
                left++;
            }
        }
        return new int[]{-1, -1};
    }

    public int[] twoSum2(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            int sum = numbers[left] + numbers[right];
            if (target == sum) {
                return new int[]{left + 1, right + 1};
            } else if (target > sum) {
                left++;
            } else if (target < sum) {
                right--;
            }
        }
        return new int[]{-1, -1};
    }
}