package com.wei.leetcode.f1000;

import com.wei.leetcode.TreeNode;

public class Solution_538 {

    public static void main(String[] args) {
    }

    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }

    int sum = 0;

    void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.right);
        sum+=root.val;
        root.val = sum;
        dfs(root.left);
    }
}
