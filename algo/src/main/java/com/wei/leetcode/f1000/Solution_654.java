package com.wei.leetcode.f1000;


import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.TreeNode;

public class Solution_654 {

    public static void main(String[] args) {
        Solution_654 solution876 = new Solution_654();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        TreeNode treeNode = new Solution_654().constructMaximumBinaryTree(nums);
        LeetCodeUtils.printInorder(treeNode);
    }

    private TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    public TreeNode build(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        if (nums == null || nums.length == 0) {
            return null;
        }

        int index = -1;
        int maxValue = Integer.MIN_VALUE;
        for (int i =start; i <= end; i++) {
            int num = nums[i];
            if (num > maxValue) {
                maxValue = num;
                index = i;
            }
        }
        TreeNode root = new TreeNode(maxValue);
        root.left = build(nums, start, index - 1);
        root.right = build(nums, index + 1, end);
        return root;

    }

}
