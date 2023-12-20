package com.wei.leetcode;

public class Main {
    public static void main(String[] args) {
        int[] arr = {9985, 2, 3, 4, 5};

        // 数组转为链表
        ListNode head = LeetCodeUtils.arrayToList(arr);
        // 链表转为数组
        int[] arrFromList = LeetCodeUtils.listToArray(head);

        for (int num : arrFromList) {
            System.out.print(num + " ");
        }
        System.out.println();

        // 数组转为二叉树
        TreeNode root = LeetCodeUtils.arrayToTree(arr);
        // 二叉树转为数组
        int[] arrFromTree = LeetCodeUtils.treeToArray(root);

        for (int num : arrFromTree) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
