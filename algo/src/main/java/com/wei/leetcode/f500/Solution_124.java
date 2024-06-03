package com.wei.leetcode.f500;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.TreeNode;

public class Solution_124 {

    public static void main(String[] args) {
        Solution_124 solution = new Solution_124();
        int[] nums = {2, -1};
        TreeNode treeNode = LeetCodeUtils.arrayToTree(nums);
        LeetCodeUtils.printPreorder(treeNode);
        int i = solution.maxPathSum(treeNode);
        System.out.println("i = " + i);
    }

    int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        traverse(root);
        return res;
    }

    public int traverse(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = Math.max(traverse(root.left), 0);
        int rightMax = Math.max(traverse(root.right), 0);
        // 上面的写法比下面的写法慢1ms
//        res = Math.max(leftMax + rightMax + root.val, res);
        res = Math.max(root.val + leftMax + rightMax, res);
        return Math.max(rightMax, leftMax) + root.val;
    }
}
