package com.wei.leetcode.f500;

import com.wei.leetcode.TreeNode;

public class Solution_98 {

    public static void main(String[] args) {
    }

    public boolean isValidBST(TreeNode root) {
        return traverse(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    boolean traverse(TreeNode root, long leftVal, long rightVal) {
        if (root == null) {
            return true;
        }
        int val = root.val;
        return leftVal < val && val < rightVal
                && traverse(root.left, leftVal, val)
                && traverse(root.right, val, rightVal);
    }
}
