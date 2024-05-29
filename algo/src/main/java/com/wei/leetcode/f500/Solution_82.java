package com.wei.leetcode.f500;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.ListNode;

public class Solution_82 {

    public static void main(String[] args) {
        Solution_82 solution = new Solution_82();
        int[] nums = {1, 2, 3, 3, 4, 4, 5, 6, 7, 8, 9, 12, 12};
        ListNode listNode = LeetCodeUtils.arrayToList(nums);
        ListNode listNode1 = solution.deleteDuplicates2(listNode);
        LeetCodeUtils.printList(listNode1);
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 头节点重复
        if (head.val == head.next.val) {
            int val = head.val;
            while (head != null && head.val == val) {
                head = head.next;
            }
            return deleteDuplicates(head);
        }
        // 头节点不重复
        head.next = deleteDuplicates(head.next);
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            int val = cur.next.val;
            if (val == cur.next.next.val) {
                while (cur.next != null && val == cur.next.val) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
