package com.wei.leetcode.f500;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.ListNode;

public class Solution_21 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 4};
        int[] nums2 = new int[]{1, 2, 3};
        ListNode listNode = LeetCodeUtils.arrayToList(nums);
        ListNode listNode2 = LeetCodeUtils.arrayToList(nums2);
        LeetCodeUtils.printList(mergeTwoLists(listNode, listNode2));
    }

    static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 虚拟头结点
        ListNode dummy = new ListNode(-1), p = dummy;
        ListNode p1 = l1, p2 = l2;

        while (p1 != null && p2 != null) {
            // 比较 p1 和 p2 两个指针
            // 将值较小的的节点接到 p 指针
            if (p1.val > p2.val) {
                p.next = p2;
                p2 = p2.next;
            } else {
                p.next = p1;
                p1 = p1.next;
            }
            // p 指针不断前进
            p = p.next;
            LeetCodeUtils.printList(p);
        }

        if (p1 != null) {
            p.next = p1;
        }

        if (p2 != null) {
            p.next = p2;
        }
        LeetCodeUtils.printList(dummy);
        return dummy.next;
    }
}
