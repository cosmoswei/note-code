//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.wei;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MathGame {
    private static Random random = new Random();
    private int illegalArgumentCount = 0;

    public MathGame() {
    }

    public static void main(String[] args) throws InterruptedException {
        MathGame game = new MathGame();

        while(true) {
            game.run();
            TimeUnit.SECONDS.sleep(1L);
        }
    }

    public void run() throws InterruptedException {
        try {
            int number = random.nextInt() / 10000;
            List<Integer> primeFactors = this.primeFactors(number);
            print(number, primeFactors);
        } catch (Exception var3) {
            System.out.println(String.format("illegalArgumentCount:%3d, ", this.illegalArgumentCount) + var3.getMessage());
        }

    }

    public static void print(int number, List<Integer> primeFactors) {
        StringBuffer sb = new StringBuffer(number + "=");
        Iterator var3 = primeFactors.iterator();

        while(var3.hasNext()) {
            int factor = (Integer)var3.next();
            sb.append(factor).append('*');
        }

        if (sb.charAt(sb.length() - 1) == '*') {
            sb.deleteCharAt(sb.length() - 1);
        }

        System.out.println(sb);
    }

    public List<Integer> primeFactors(int number) {
        if (number < 2) {
            ++this.illegalArgumentCount;
            throw new IllegalArgumentException("number is: " + number + ", need >= 2");
        } else {
            List<Integer> result = new ArrayList();
            int i = 2;

            while(i <= number) {
                if (number % i == 0) {
                    result.add(i);
                    number /= i;
                    i = 2;
                } else {
                    ++i;
                }
            }

            return result;
        }
    }
}
