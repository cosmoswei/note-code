package com.wei;

import lombok.AllArgsConstructor;

import java.util.concurrent.Semaphore;

public class CustomCheckWindow {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);

        for (int i = 0; i <= 7; i++) {
            new SecurityCheckThread(i, semaphore).start();
        }
    }

    @AllArgsConstructor
    private static class SecurityCheckThread extends Thread {

        private int seq;
        private Semaphore semaphore;

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("No" + seq + "乘客，正在查验中");
                if (seq % 2 == 0) {
                    Thread.sleep(10000);
                    System.out.println("No" + seq + "乘客，不能出国");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
                System.out.println("No" + seq + "乘客，已完成服务");

            }
        }
    }
}
