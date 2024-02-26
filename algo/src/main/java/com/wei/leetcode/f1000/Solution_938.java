package com.wei.leetcode.f1000;

import com.wei.leetcode.TreeNode;

class Solution_938 {
    
    /*
    作者：力扣官方题解
    链接：https://leetcode.cn/problems/range-sum-of-bst/solutions/746069/er-cha-sou-suo-shu-de-fan-wei-he-by-leet-rpq7/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }
}

