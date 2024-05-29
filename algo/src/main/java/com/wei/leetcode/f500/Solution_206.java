package com.wei.leetcode.f500;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.ListNode;

public class Solution_206 {

    public static void main(String[] args) {
        Solution_206 solution19 = new Solution_206();
        int[] nums = {1, 2, 3, 4, 5};
        ListNode listNode = LeetCodeUtils.arrayToList(nums);
        ListNode res = solution19.reverseList2(listNode);
        LeetCodeUtils.printList(res);
    }

    // 定义：输入一个单链表头结点，将该链表反转，返回新的头结点
    public ListNode reverseList(ListNode head) {
        if (null == head|| null == head.next) {
            return head;
        }
        ListNode reverse = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return reverse;
    }

    public ListNode reverseList2(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode prev = null;
        ListNode cur = head;
        while (null != cur) {
            System.out.println("cur.val = " + cur.val);
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
