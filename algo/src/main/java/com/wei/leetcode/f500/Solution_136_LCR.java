package com.wei.leetcode.f500;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.ListNode;

class Solution_136_LCR {


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        ListNode listNode = LeetCodeUtils.arrayToList(nums);
        ListNode res = new Solution_136_LCR().deleteNode(listNode, 5);
        LeetCodeUtils.printList(res);
    }

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        if (head.val == val) {
            return head.next;
        }
        ListNode prev = new ListNode(-1);
        prev.next = head;
        while (head.next != null) {
            ListNode next = head.next;
            if (next.val == val) {
                head.next = next.next;
            }
            head = next;
        }
        return prev.next;
    }
}