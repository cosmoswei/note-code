package com.wei.leetcode.f3000;

import com.wei.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution_2641 {

    /*

    作者：力扣官方题解
    链接：https://leetcode.cn/problems/cousins-in-binary-tree-ii/solutions/2626702/er-cha-shu-de-tang-xiong-di-jie-dian-ii-1b9oj/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);
        root.val = 0;
        while (!queue.isEmpty()) {
            Queue<TreeNode> queue2 = new ArrayDeque<TreeNode>();
            int sum = 0;
            for (TreeNode fa : queue) {
                if (fa.left != null) {
                    queue2.offer(fa.left);
                    sum += fa.left.val;
                }
                if (fa.right != null) {
                    queue2.offer(fa.right);
                    sum += fa.right.val;
                }
            }
            for (TreeNode fa : queue) {
                int childSum = (fa.left != null ? fa.left.val : 0) +
                        (fa.right != null ? fa.right.val : 0);
                if (fa.left != null) {
                    fa.left.val = sum - childSum;
                }
                if (fa.right != null) {
                    fa.right.val = sum - childSum;
                }
            }
            queue = queue2;
        }
        return root;
    }
}
