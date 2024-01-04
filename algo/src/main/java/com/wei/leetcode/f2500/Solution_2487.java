package com.wei.leetcode.f2500;


import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.ListNode;

public class Solution_2487 {

    public static ListNode removeNodes2(ListNode head) {
        if (head == null) {
            return null;
        }
        head.next = removeNodes(head.next);
        if (head.next != null && head.val < head.next.val) {
            return head.next;
        } else {
            return head;
        }
    }

    public static void main(String[] args) {
        int[] nums = {5, 2, 13, 3, 8};
        ListNode listNode = LeetCodeUtils.arrayToList(nums);
        ListNode res = removeNodes2(listNode);
        LeetCodeUtils.printList(res);
    }

    static public ListNode removeNodes(ListNode head) {
        ListNode res0 = new ListNode();
        ListNode res1 = res0;
        while (null != head) {
            int maxValue = findMaxValue(head);
            if (head.val >= maxValue) {
                res0.next = head;
                res0 = res0.next;
            }
            head = head.next;
        }
        return res1.next;
    }

    static int findMaxValue(ListNode head) {
        int res = 0;
        while (null != head) {
            if (head.val > res) {
                res = head.val;
            }
            head = head.next;
        }
        return res;
    }
}
