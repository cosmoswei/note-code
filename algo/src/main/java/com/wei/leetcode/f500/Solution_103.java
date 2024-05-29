package com.wei.leetcode.f500;


import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.TreeNode;

import java.util.*;

public class Solution_103 {

    public static void main(String[] args) {

        Solution_103 solution103 = new Solution_103();
        Integer[] arrays = {3, 9, 20, null, null, 15, 7};

        Integer[] nums = {1, 2, 3, null, 4, null, 5};
        TreeNode root = LeetCodeUtils.buildTree(arrays);

        List<List<Integer>> res = solution103.zigzagLevelOrder(root);
        System.out.println("res = " + res);
    }

    /*
    作者：力扣官方题解
    链接：https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/solutions/530400/er-cha-shu-de-ju-chi-xing-ceng-xu-bian-l-qsun/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;
        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<>(levelList));
            isOrderLeft = !isOrderLeft;
        }
        return ans;
    }

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        boolean event = false;
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
            if (event) {
                Collections.reverse(vals);
            }
            res.add(vals);
            event = !event;
        }
        return res;
    }
}
