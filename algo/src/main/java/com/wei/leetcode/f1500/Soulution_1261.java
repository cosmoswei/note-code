package com.wei.leetcode.f1500;

import com.wei.leetcode.TreeNode;

import java.util.HashSet;
import java.util.Set;


class Soulution_1261 {
    class FindElements {
        private Set<Integer> valSet;

        public FindElements(TreeNode root) {
            this.valSet = new HashSet<>();
            dfs(root, 0);
        }

        public boolean find(int target) {
            return valSet.contains(target);
        }

        private void dfs(TreeNode node, int val) {
            if (node == null) {
                return;
            }
            node.val = val;
            valSet.add(val);
            dfs(node.left, val * 2 + 1);
            dfs(node.right, val * 2 + 2);
        }
    }
}
