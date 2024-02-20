package com.wei.leetcode.f1000;

import com.wei.leetcode.Node;

import java.util.ArrayList;
import java.util.List;

public class Solution_589 {

    public static void main(String[] args) {
        Solution_589 solution = new Solution_589();
    }

    // 遍历二叉树
    void traverse(Node node) {
        if (node == null) {
            return;
        }
        // 对每个节点计算直径
        List<Node> children = node.children;
        for (Node child : children) {
            traverse(child);
        }
    }


    public List<Integer> preorder(Node node) {
        List<Integer> res = new ArrayList<>();
        helper(node, res);
        return res;
    }

    public void helper(Node node, List<Integer> res) {
        // 对每个节点计算直径
        if (node == null) {
            return;
        }
        List<Node> children = node.children;
        res.add(node.value);
        for (Node child : children) {
            helper(child, res);
        }
    }
}
