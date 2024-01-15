package com.wei.leetcode.f500;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.TreeNode;

public class Solution_230 {

    public static void main(String[] args) {
        Solution_230 solution = new Solution_230();
        int[] nums = {3, 1, 4, 0, 2};
        TreeNode treeNode = LeetCodeUtils.arrayToTree(nums);
        LeetCodeUtils.printPreorder(treeNode);
        int i = solution.kthSmallest(treeNode, 1);
        System.out.println();
        System.out.println("i = " + i);
    }

    int res = 0;
    int rank = 0;

    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return res;
    }

    public void traverse(TreeNode treeNode, int k) {
        if (treeNode == null) {
            return;
        }
        traverse(treeNode.left, k);
        rank++;
        if (rank == k) {
            res = treeNode.val;
            return;
        }
        traverse(treeNode.right, k);
    }
}
