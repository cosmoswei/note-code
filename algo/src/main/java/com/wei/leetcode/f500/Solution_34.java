package com.wei.leetcode.f500;

public class Solution_34 {

    public static void main(String[] args) {
        Solution_34 solution26 = new Solution_34();
        int[] nums = {2, 2};
        int[] ints = solution26.searchRange(nums, 3);
        for (int s = 0; s < ints.length; s++) {
            System.out.println("s = " + ints[s]);
        }
    }

    public int[] searchRange(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) {
            return new int[]{-1, -1};
        }
        if (length == 1) {
            if (nums[0] == target) {
                return new int[]{0, 0};

            } else {
                return new int[]{-1, -1};
            }
        }
        int left = 0;
        int right = length;
        int leftInd;
        int rightInd;
        // 找到左边界
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        leftInd = (nums[left] == target) ? left : -1;

        left = 0;
        right = length;
        // 找到右边界
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        if (right == -1 || left == -1) {
            return new int[]{-1, -1};
        }
        rightInd = (nums[right] == target) ? right : -1;
        return new int[]{leftInd, rightInd};
    }


    public int[] searchRange1(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

}
