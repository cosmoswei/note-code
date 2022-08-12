package com.wei.chapter11;

import com.wei.entity.Shop;

import java.util.Random;

public class Discount {
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(12), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
//        Shop.delay();
        randomDelay();
        return (price * (100 - code.percentage) / 100);
    }

    private static final Random random = new Random();

    public static void randomDelay() {
        int randomDelay = 500 + random.nextInt(2000);
        try {
            Thread.sleep(randomDelay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
