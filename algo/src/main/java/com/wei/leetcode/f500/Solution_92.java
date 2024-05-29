package com.wei.leetcode.f500;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.ListNode;

public class Solution_92 {

    ListNode successor = null; // 后驱节点

    public static void main(String[] args) {
        Solution_92 solution19 = new Solution_92();
        int[] nums = {1, 2, 3, 4, 5};
        ListNode listNode = LeetCodeUtils.arrayToList(nums);
        ListNode res = solution19.reverseBetween(listNode, 2, 4);
        LeetCodeUtils.printList(res);
    }

    // 反转以 head 为起点的 n 个节点，返回新的头结点
    ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;

    }

    ListNode reverseBetween(ListNode head, int m, int n) {
        // base case
        if (m == 1) {
            return reverseN(head, n);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }
}
