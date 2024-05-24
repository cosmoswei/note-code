package other;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Test {

    public static void main(String[] args) {
        int[] nums = {3, 9, 20, 00, 0, 15, 7};
        TreeNode treeNode = LeetCodeUtils.arrayToTree(nums);
        System.out.println(treeNode);
        levelTraverse(treeNode);
    }

    // 输入一棵二叉树的根节点，层序遍历这棵二叉树
    static void levelTraverse(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                System.out.print(cur.val + " ");
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
        }
    }
}
