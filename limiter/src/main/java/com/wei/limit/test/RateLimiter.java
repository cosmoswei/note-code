package com.wei.limit.test;

import java.util.concurrent.TimeUnit;

public class RateLimiter {
    private final double permitsPerSecond;
    private double storedPermits;
    private long nextFreeTicketMicros;

    private RateLimiter(double permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
        this.storedPermits = 0.0;
        this.nextFreeTicketMicros = 1L;
    }

    public static RateLimiter create(double permitsPerSecond) {
        return new RateLimiter(permitsPerSecond);
    }

    public boolean tryAcquire() {
        return tryAcquire(1);
    }

    public boolean tryAcquire(int permits) {
        long nowMicros = TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis());

        if (nextFreeTicketMicros > nowMicros) {
            return false; // Not enough time has passed since the last request.
        }

        synchronized (this) {
            long waitTimeMicros = (long) (permits * 1000000.0 / permitsPerSecond);

            if (nowMicros - nextFreeTicketMicros > waitTimeMicros) {
                storedPermits = 10;
                nextFreeTicketMicros = nowMicros;
            }
            if (storedPermits >= permits) {
                storedPermits -= permits;
                return true;
            }

            return false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = RateLimiter.create(10); // Allow 2 requests per second
        for (int i = 1; i <= 100; i++) {
            Thread.sleep(10);
            if (limiter.tryAcquire()) {
                System.out.println("Request " + i + ": Allowed");
            } else {
                System.out.println("Request " + i + ": Denied");
            }
        }
    }
}
