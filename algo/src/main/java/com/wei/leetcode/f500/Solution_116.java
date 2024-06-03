package com.wei.leetcode.f500;

import com.wei.leetcode.BNode;

public class Solution_116 {

    public static void main(String[] args) {
    }

    public BNode connect(BNode root) {
        if (root == null) {
            return null;
        }
        traverse(root.left, root.right);
        return root;
    }

    private void traverse(BNode node1, BNode node2) {
        if (node1 != null && node2 != null) {
            return;
        }
        node1.next = node2;
        traverse(node1.left, node1.right);
        traverse(node2.right, node2.left);
        traverse(node1.right, node2.left);
    }
}
