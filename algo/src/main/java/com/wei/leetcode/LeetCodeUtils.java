package com.wei.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCodeUtils {
    // 将数组转化为链表
    public static ListNode arrayToList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        ListNode head = new ListNode(arr[0]);
        ListNode curr = head;

        for (int i = 1; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }

        return head;
    }

    public static ListNode[] arrayToLists(int[]... complexArray) {
        int length = complexArray.length;
        ListNode[] res = new ListNode[length];
        for (int i = 0; i < complexArray.length; i++) {
            int[] array = complexArray[i];
            ListNode node = LeetCodeUtils.arrayToList(array);
            res[i] = node;
        }
        return res;
    }

    // 将链表转化为数组
    public static int[] listToArray(ListNode head) {
        if (head == null) {
            return new int[0];
        }

        ListNode curr = head;
        int size = 0;

        while (curr != null) {
            size++;
            curr = curr.next;
        }

        int[] arr = new int[size];
        curr = head;
        int index = 0;

        while (curr != null) {
            arr[index++] = curr.val;
            curr = curr.next;
        }

        return arr;
    }

    // 将数组转化为二叉树
    public static TreeNode arrayToTree(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 1;

        while (!queue.isEmpty() && index < arr.length) {
            TreeNode curr = queue.poll();

            if (arr[index] != -1) {
                curr.left = new TreeNode(arr[index]);
                queue.offer(curr.left);
            }
            index++;

            if (index < arr.length && arr[index] != -1) {
                curr.right = new TreeNode(arr[index]);
                queue.offer(curr.right);
            }
            index++;
        }

        return root;
    }

    // 将二叉树转化为数组
    public static int[] treeToArray(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        LinkedList<Integer> list = new LinkedList<>();

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();

            if (curr != null) {
                list.add(curr.val);
                queue.offer(curr.left);
                queue.offer(curr.right);
            } else {
                list.add(-1);
            }
        }

        while (!list.isEmpty() && list.getLast() == -1) {
            list.removeLast();
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    // 遍历打印链表
    public static void printList(ListNode head) {
        ListNode curr = head;

        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }

        System.out.println("null");
    }

    // 前序遍历打印二叉树
    public static void printPreorder(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.val + " -> ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    // 中序遍历打印二叉树
    public static void printInorder(TreeNode root) {
        if (root == null) {
            return;
        }

        printInorder(root.left);
        System.out.print(root.val + " -> ");
        printInorder(root.right);
    }

    // 后序遍历打印二叉树
    public static void printPostorder(TreeNode root) {
        if (root == null) {
            return;
        }

        printPostorder(root.left);
        printPostorder(root.right);
        System.out.print(root.val + " -> ");
    }
}
