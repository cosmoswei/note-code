package com.wei.leetcode.f500;


import com.wei.leetcode.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution_429 {


    public static void main(String[] args) {
        Integer[] arrays = {3, 9, 20, null, null, 15, 7};

    }


    /*
    作者：力扣官方题解
    链接：https://leetcode.cn/problems/n-ary-tree-level-order-traversal/solutions/1400623/n-cha-shu-de-ceng-xu-bian-li-by-leetcode-lxdr/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Queue<Node> queue = new ArrayDeque<Node>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int cnt = queue.size();
            List<Integer> level = new ArrayList<Integer>();
            for (int i = 0; i < cnt; ++i) {
                Node cur = queue.poll();
                level.add(cur.value);
                for (Node child : cur.children) {
                    queue.offer(child);
                }
            }
            ans.add(level);
        }

        return ans;
    }
}

