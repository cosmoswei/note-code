package com.wei.leetcode.f500;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.ListNode;

class Solution_143 {
    public void reorderList(ListNode head) {
        ListNode middleNode = middleNode(head);
        ListNode head2 = reverseList(middleNode);

        while (head2.next != null) {
            ListNode next = head.next;
            ListNode next2 = head2.next;
            head.next = head2;
            head2.next = next;
            head = next;
            head2 = next2;
        }
    }

    /**
     *
     作者：灵茶山艾府
     链接：https://leetcode.cn/problems/reorder-list/solutions/1999276/mei-xiang-ming-bai-yi-ge-shi-pin-jiang-t-u66q/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public void reorderList2(ListNode head) {
        ListNode mid = middleNode(head);
        ListNode head2 = reverseList(mid);
        while (head2.next != null) {
            ListNode nxt = head.next;
            ListNode nxt2 = head2.next;
            head.next = head2;
            head2.next = nxt;
            head = nxt;
            head2 = nxt2;
        }
    }



    // 定义：输入一个单链表头结点，将该链表反转，返回新的头结点
    public ListNode reverseList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode reverse = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return reverse;
    }

    ListNode middleNode(ListNode head) {
        // 快慢指针初始化指向 head
        ListNode slow = head, fast = head;
        // 快指针走到末尾时停止
        while (fast != null && fast.next != null) {
            // 慢指针走一步，快指针走两步
            slow = slow.next;
            fast = fast.next.next;
        }
        // 慢指针指向中点
        return slow;
    }

    public static void main(String[] args) {
        Solution_143 solution = new Solution_143();
        int[] nums = {1, 2, 3, 4};
        ListNode listNode = LeetCodeUtils.arrayToList(nums);
        solution.reorderList(listNode);
        LeetCodeUtils.printList(listNode);
    }
}