package com.wei;

import java.util.Random;

public class MedianFinder {
    private static final int MAX_SIZE = 400000000; // 40亿
    private static final int MEMORY_LIMIT = 10 * 1024 * 1024; // 10MB
    private static final int MAX_COUNT = MEMORY_LIMIT / 4; // 10MB内存可以存储的int数量
    private static final int RANGE = 65536; // 随机数范围，根据实际情况调整

    public static void main(String[] args) {
        int[] numbers = generateRandomNumbers(MAX_SIZE); // 生成40亿个随机整数
        int median = findMedianXor(numbers);
        System.out.println("The median is: " + median);
    }

    private static int findMedianXor(int[] numbers) {
        int[] count = new int[RANGE];
        for (int num : numbers) {
            if (num < 0 || num >= RANGE) continue; // 确保数字在范围内
            count[num]++; // 统计每个数字的出现次数
        }

        int total = 0, half = MAX_SIZE / 2;
        for (int i = 0; i < RANGE; i++) {
            total += count[i];
            if (total > half) {
                return i; // 返回当前数字作为中位数的近似值
            }
        }
        return -1; // 如果没有找到，返回-1
    }

    private static int[] generateRandomNumbers(int count) {
        int[] numbers = new int[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            numbers[i] = random.nextInt(RANGE); // 生成随机数
        }
        return numbers;
    }
}