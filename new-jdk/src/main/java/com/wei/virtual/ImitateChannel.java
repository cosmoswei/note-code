package com.wei.virtual;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ImitateChannel {
    static void sum(int[] s, int start, int end, BlockingQueue<Integer> queue) throws InterruptedException {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum += s[i];
        }
        queue.put(sum);
    }


    public static void main(String[] args) throws InterruptedException {
        int[] s = {7, 2, 8, -9, 4, 0};
        var queue = new ArrayBlockingQueue<Integer>(1);
        Thread.startVirtualThread(() -> {
            try {
                sum(s, 0, s.length / 2, queue);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread.startVirtualThread(() -> {
            try {
                sum(s, s.length / 2, s.length, queue);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        int x = queue.take();
        int y = queue.take();

        System.out.printf("%d %d %d\n", x, y, x + y);
    }
}