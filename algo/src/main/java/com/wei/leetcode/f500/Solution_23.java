package com.wei.leetcode.f500;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution_23 {

    public static void main(String[] args) {

        int[] int1 = {1, 4, 5};
        int[] int2 = {1, 3, 4};
        int[] int3 = {2, 6, 9};
        ListNode[] listNodes = LeetCodeUtils.arrayToLists(int3, int1, int2);
        Solution_23 solution23 = new Solution_23();
        ListNode listNode = solution23.mergeKLists(listNodes);
        LeetCodeUtils.printList(listNode);
    }

    ListNode mergeKLists(ListNode[] lists) {
        if (null == lists || lists.length == 0) {
            return null;
        }
        ListNode dump = new ListNode(-1);
        ListNode res = dump;
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(lists.length, Comparator.comparingInt(x -> x.val));
        for (ListNode list : lists) {
            if (null != list) {
                priorityQueue.add(list);
            }
        }
        while (!priorityQueue.isEmpty()) {
            ListNode poll = priorityQueue.poll();
            res.next = poll;
            if (null != poll.next) {
                priorityQueue.add(poll.next);
            }
            res = res.next;
        }
        return dump.next;
    }

}
