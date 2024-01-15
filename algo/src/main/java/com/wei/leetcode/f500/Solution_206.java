package com.wei.leetcode.f500;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.ListNode;

public class Solution_206 {

    public static void main(String[] args) {
        Solution_206 solution19 = new Solution_206();
        int[] nums = {1, 2, 3, 4, 5};
        ListNode listNode = LeetCodeUtils.arrayToList(nums);
        ListNode res = solution19.test(listNode);
        LeetCodeUtils.printList(res);
    }

    public ListNode reverseList(ListNode head) {
        return head;
    }

    // 定义：输入一个单链表头结点，将该链表反转，返回新的头结点
    ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    public ListNode test(ListNode listNode) {
        if (null == listNode || null == listNode.next) {
            return listNode;
        }

        ListNode test = test(listNode.next);
        listNode.next.next = listNode;
        listNode.next = null;
        return test;
    }

}
