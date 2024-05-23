package com.wei.leetcode.f500;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.ListNode;

class Solution_160 {
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // p1 指向 A 链表头结点，p2 指向 B 链表头结点
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            // p1 走一步，如果走到 A 链表末尾，转到 B 链表
            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }
            // p2 走一步，如果走到 B 链表末尾，转到 A 链表
            if (p2 == null) {
                p2 = headA;
            } else {
                p2 = p2.next;
            }
        }
        return p1;
    }

    public static void main(String[] args) {
        Solution_160 solution = new Solution_160();
        int[] nums = {100, 4, 200, 1, 3, 2};
        ListNode listNode = solution.getIntersectionNode(LeetCodeUtils.arrayToList(nums),
                LeetCodeUtils.arrayToList(new int[]{1, 2, 3, 4, 5}));
        LeetCodeUtils.printList(listNode);
    }
}