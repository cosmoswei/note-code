package com.wei.leetcode.f500;

import com.wei.leetcode.TreeNode;

public class Solution_110 {


    public static void main(String[] args) {
    }


    int getHeight(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int leftHeight = getHeight(head.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = getHeight(head.right);
        if (rightHeight == -1) {
            return -1;
        }
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }
}
