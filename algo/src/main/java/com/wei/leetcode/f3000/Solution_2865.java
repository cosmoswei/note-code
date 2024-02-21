package com.wei.leetcode.f3000;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Solution_2865 {

    public static void main(String[] args) {
        Solution_2865 solution = new Solution_2865();
        Integer[] param = {5, 2, 4, 4};
        List<Integer> maxHeights = Arrays.asList(param);
        long i = solution.maximumSumOfHeights3(maxHeights);
        System.out.println("i = " + i);
    }

    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        long res = 0;
        long[] prefix = new long[n];
        long[] suffix = new long[n];
        Deque<Integer> stack1 = new ArrayDeque<Integer>();
        Deque<Integer> stack2 = new ArrayDeque<Integer>();

        for (int i = 0; i < n; i++) {
            while (!stack1.isEmpty() && maxHeights.get(i) < maxHeights.get(stack1.peek())) {
                stack1.pop();
            }
            if (stack1.isEmpty()) {
                prefix[i] = (long) (i + 1) * maxHeights.get(i);
            } else {
                prefix[i] = prefix[stack1.peek()] + (long) (i - stack1.peek()) * maxHeights.get(i);
            }
            stack1.push(i);
        }
        for (int i = n - 1; i >= 0; i--) {
            while (!stack2.isEmpty() && maxHeights.get(i) < maxHeights.get(stack2.peek())) {
                stack2.pop();
            }
            if (stack2.isEmpty()) {
                suffix[i] = (long) (n - i) * maxHeights.get(i);
            } else {
                suffix[i] = suffix[stack2.peek()] + (long) (stack2.peek() - i) * maxHeights.get(i);
            }
            stack2.push(i);
            res = Math.max(res, prefix[i] + suffix[i] - maxHeights.get(i));
        }
        return res;
    }

    public long maximumSumOfHeights2(List<Integer> maxHeights) {
        long res1 = maximumSumOfHeights(maxHeights, getFMaxIndex(maxHeights));
        long res2 = maximumSumOfHeights(maxHeights, getLMaxIndex(maxHeights));
        // 将List 分为两部分
        return Math.max(res1, res2);
    }

    public long maximumSumOfHeights3(List<Integer> maxHeights) {

        long res = 0;
        for (int i = 0; i < maxHeights.size(); i++) {
            res = Math.max(maximumSumOfHeights(maxHeights, i), res);
        }
        // 将List 分为两部分
        return res;
    }

    private static long maximumSumOfHeights(List<Integer> maxHeights, int maxIndex) {
        long[] resArray = new long[maxHeights.size()];
        resArray[maxIndex] = maxHeights.get(maxIndex);
        int size = maxHeights.size();
        for (int i = maxIndex - 1; i >= 0; i--) {
            if (maxHeights.get(i) < resArray[i + 1]) {
                resArray[i] = maxHeights.get(i);
            } else {
                resArray[i] = resArray[i + 1];
            }
        }

        // {5, 3, 4, 1, 1};
        for (int j = maxIndex + 1; j < size; j++) {
            if (maxHeights.get(j) < resArray[j - 1]) {
                resArray[j] = maxHeights.get(j);
            } else {
                resArray[j] = resArray[j - 1];
            }
        }
        System.out.println("resArray = " + Arrays.toString(resArray));
        return Arrays.stream(resArray).sum();
    }

    private int getLMaxIndex(List<Integer> list) {
        int res = 0;
        int max = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > max) {
                max = list.get(i);
                res = i;
            }
        }
        return res;
    }

    private int getFMaxIndex(List<Integer> list) {
        int res = 0;
        int max = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) >= max) {
                max = list.get(i);
                res = i;
            }
        }
        return res;
    }
}


