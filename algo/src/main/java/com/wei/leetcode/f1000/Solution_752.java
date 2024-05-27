package com.wei.leetcode.f1000;

import java.util.*;

class Solution_752 {
    public static void main(String[] args) {
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        int res = new Solution_752().openLock(deadends, target);
        System.out.println(res);
    }

    /**
     * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
     * 示例 1:
     * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
     * 输出：6
     * 解释：
     * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
     * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
     * 因为当拨动到 "0102" 时这个锁就会被锁定。
     */
    public int openLock(String[] deadends, String target) {
        Set<String> deadendSet = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        int steps = 0;
        queue.offer("0000");
        visited.add("0000");
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (null == cur) {
                    continue;
                }

                if (deadendSet.contains(cur)) {
                    continue;
                }
                if (target.equals(cur)) {
                    return steps;
                }
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        queue.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    String plusOne(String s, int k) {
        char[] charArray = s.toCharArray();
        if ('9' == charArray[k]) {
            charArray[k] = '0';
        } else {
            charArray[k] += 1;
        }
        return new String(charArray);
    }

    String minusOne(String s, int k) {
        char[] charArray = s.toCharArray();
        if ('0' == charArray[k]) {
            charArray[k] = '9';
        } else {
            charArray[k] -= 1;
        }
        return new String(charArray);
    }

    void bfs(String target) {
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        while (!queue.isEmpty()) {
            int size = queue.size();
            String cur = queue.poll();
            for (int i = 0; i < size; i++) {
                String up = plusOne(cur, i);
                String down = minusOne(cur, i);
                queue.offer(up);
                queue.offer(down);
            }
        }
    }
}