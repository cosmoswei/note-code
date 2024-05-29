package com.wei.leetcode.f500;

import com.wei.leetcode.TreeNode;

public class Solution_101 {

    public static void main(String[] args) {
    }

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }

        return p.val==q.val&&isSymmetric(p.left,q.right) && isSymmetric(p.right,q.left);
    }
}
