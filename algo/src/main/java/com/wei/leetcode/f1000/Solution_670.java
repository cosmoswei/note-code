package com.wei.leetcode.f1000;


public class Solution_670 {

    public static void main(String[] args) {
        Solution_670 solution876 = new Solution_670();
        System.out.println("Solution_567 = " + solution876.maximumSwap2(9236));
    }

    public int maximumSwap(int num) {
        char[] charArray = String.valueOf(num).toCharArray();
        int l = charArray.length;
        int max = num;
        for (int i = 0; i < l; i++) {
            for (int j = i + 1; j < l; j++) {
                swap(charArray, i, j);
                max = Math.max(max, Integer.parseInt(new String(charArray)));
                swap(charArray, i, j);
            }
        }
        return max;
    }

    private void swap(char[] charArray, int i, int j) {
        char c = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = c;
    }

    public int maximumSwap2(int num) {
        char[] charArray = String.valueOf(num).toCharArray();
        int n = charArray.length;
        int maxIdx = n - 1;
        int idx1 = -1, idx2 = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (charArray[i] > charArray[maxIdx]) {
                maxIdx = i;
            } else if (charArray[i] < charArray[maxIdx]) {
                idx1 = i;
                idx2 = maxIdx;
            }
        }
        if (idx1 >= 0) {
            swap(charArray, idx1, idx2);
            return Integer.parseInt(new String(charArray));
        } else {
            return num;
        }
    }

}
