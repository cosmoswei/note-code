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

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum2(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);
        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewpath = node.val + leftGain + rightGain;
        // 更新答案
        maxSum = Math.max(maxSum, priceNewpath);
        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }

}
