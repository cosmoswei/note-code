package com.wei.leetcode.f500;


import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution_102 {

    public static void main(String[] args) {
        Solution_102 solution103 = new Solution_102();
        Integer[] arrays = {3, 9, 20, null, null, 15, 7};
        TreeNode root = LeetCodeUtils.buildTree(arrays);
        List<List<Integer>> res = solution103.levelOrder(root);
        res.forEach(System.out::println);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<TreeNode> cur = Collections.singletonList(root);
        while (!cur.isEmpty()) {
            List<TreeNode> nxt = new ArrayList<>(cur.size());
            List<Integer> vals = new ArrayList<>();
            for (TreeNode treeNode : cur) {
                vals.add(treeNode.val);
                if (treeNode.left != null) {
                    nxt.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    nxt.add(treeNode.right);
                }
            }
            cur = nxt;
            res.add(vals);
        }

        return res;
    }
}
