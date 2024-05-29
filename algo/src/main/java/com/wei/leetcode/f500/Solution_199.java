package com.wei.leetcode.f500;

import com.wei.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_199 {

    public static void main(String[] args) {
    }


    List<Integer> res = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (depth == res.size()) {
            res.add(root.val);
        }
        dfs(root.right, depth + 1);
        dfs(root.left, depth + 1);
    }
}
