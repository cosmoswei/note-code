package com.wei.leetcode.f500;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.ListNode;
import com.wei.leetcode.TreeNode;

public class Solution_104 {

    ListNode successor = null; // 后驱节点

    public static void main(String[] args) {
        Solution_104 solution19 = new Solution_104();
        int[] nums = {3, 9, 20, 00, 0, 15, 7};
        TreeNode treeNode = LeetCodeUtils.arrayToTree(nums);
        LeetCodeUtils.printPreorder(treeNode);
        int i = solution19.maxDepth2(treeNode);
        System.out.println();
        System.out.println("i = " + i);
    }

    public int maxDepth(TreeNode root) {
        traverse(root);
        return res;
    }

    // 记录最大深度
    int res = 0;
    // 记录遍历到的节点的深度
    int depth = 0;

    void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序位置
        depth++;
        if (root.left == null && root.right == null) {
            // 到达叶子节点，更新最大深度
            res = Math.max(res, depth);
        }
        traverse(root.left);
        traverse(root.right);
        // 后序位置
        depth--;
    }

    // 定义：输入根节点，返回这棵二叉树的最大深度
    int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 利用定义，计算左右子树的最大深度
        int leftMax = maxDepth2(root.left);
        int rightMax = maxDepth2(root.right);
        // 整棵树的最大深度等于左右子树的最大深度取最大值，
        // 然后再加上根节点自己
        int res = Math.max(leftMax, rightMax) + 1;
        return res;
    }
}
