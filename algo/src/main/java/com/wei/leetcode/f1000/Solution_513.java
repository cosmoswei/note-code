package com.wei.leetcode.f1000;

import com.wei.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_513 {

    public static void main(String[] args) {
    }

    public int findBottomLeftValue(TreeNode root) {
        TreeNode node = root;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.right != null) {
                queue.add(node.right);
            }
            if (node.left != null) {
                queue.add(node.left);
            }
        }
        return node.val;
    }

}
