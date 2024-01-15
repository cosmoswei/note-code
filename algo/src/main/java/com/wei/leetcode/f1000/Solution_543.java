package com.wei.leetcode.f1000;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.TreeNode;

public class Solution_543 {

    public static void main(String[] args) {
        Solution_543 solution = new Solution_543();
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        TreeNode treeNode = LeetCodeUtils.arrayToTree(nums);
        LeetCodeUtils.printPreorder(treeNode);
        int i = solution.count(treeNode);
        System.out.println();
        System.out.println("i = " + i);
    }

    // 记录最大直径的长度
    int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        // 对每个节点计算直径，求最大直径
        maxDepth(root);
        return maxDiameter;
    }

    // 遍历二叉树
    void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // 对每个节点计算直径
        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);
        int myDiameter = leftMax + rightMax;
        // 更新全局最大直径
        maxDiameter = Math.max(maxDiameter, myDiameter);

        traverse(root.left);
        traverse(root.right);
    }

    // 计算二叉树的最大深度
    int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);
        int myDiameter = leftMax + rightMax;
        maxDiameter = Math.max(myDiameter, maxDiameter);
        return 1 + Math.max(leftMax, rightMax);
    }

    int count(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 我这个节点关心的是我的两个子树的节点总数分别是多少
        int leftCount = count(root.left);
        int rightCount = count(root.right);
        // 后序位置，左右子树节点数加上自己就是整棵树的节点数
        return leftCount + rightCount + 1;
    }
}
