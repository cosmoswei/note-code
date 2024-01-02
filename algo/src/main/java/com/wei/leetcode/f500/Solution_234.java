package com.wei.leetcode.f500;

import com.wei.leetcode.LeetCodeUtils;
import com.wei.leetcode.ListNode;

public class Solution_234 {

    public static void main(String[] args) {
        Solution_234 solution234 = new Solution_234();
        boolean isPalindrome = solution234.isPalindrome("abaxfxaba");
        System.out.println("isPalindrxme = " + isPalindrome);

        String res = solution234.palindrome("abaxfxaba", 2, 8);
        System.out.println("res = " + res);

        int[] nums = {1, 2, 3, 4, 5, 4, 3, 2, 1};
        ListNode listNode = LeetCodeUtils.arrayToList(nums);

        boolean traverse = solution234.isPalindrome(listNode);
        System.out.println("traverse = " + traverse);


        int[] nums2 = {1, 2, 3, 4, 5};
        ListNode listNode2 = LeetCodeUtils.arrayToList(nums2);
        traverse(listNode2);

    }


    // 在 s 中寻找以 s[left] 和 s[right] 为中心的最长回文串
    String palindrome(String s, int left, int right) {
        // 防止索引越界
        while (left >= 0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            // 双指针，向两边展开
            left--;
            right++;
        }
        // 返回以 s[left] 和 s[right] 为中心的最长回文串
        return s.substring(left + 1, right);
    }

    boolean isPalindrome(String s) {
        // 一左一右两个指针相向而行
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    boolean isPalindrome(ListNode head) {
        ListNode slow, fast;
        slow = fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null)
            slow = slow.next;

        ListNode left = head;
        ListNode right = reverse(slow);
        while (right != null) {
            if (left.val != right.val)
                return false;
            left = left.next;
            right = right.next;
        }

        return true;
    }

    ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /* 倒序打印单链表中的元素值 */
    private static void traverse(ListNode head) {
        if (head == null) return;
        traverse(head.next);
        // 后序遍历代码
        System.out.println("head = " + head.val);
    }

}
