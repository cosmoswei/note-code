package wei.demo08;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author cosmoswei
 */
public class AtomicIntegerArrayTest {
    static AtomicIntegerArray array = new AtomicIntegerArray(10);

    public static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int k = 0; k < 10000; k++) {
                array.getAndIncrement(k % array.length());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int k = 0; k < threads.length; k++) {
            threads[k] = new Thread(new AddThread());
        }
        for (int k = 0; k < threads.length; k++) {
            threads[k].start();
        }
        for (int k = 0; k < threads.length; k++) {
            threads[k].join();
        }
        System.out.println(array);
    }
}
