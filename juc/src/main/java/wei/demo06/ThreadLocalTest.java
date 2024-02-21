package wei.demo06;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author cosmoswei
 */
public class ThreadLocalTest {
    public static final int GEN_COUNT = 100000;
    public static final int THREAD_COUNT = 100;
    static ExecutorService service = Executors.newFixedThreadPool(THREAD_COUNT);
    public static Random random = new Random(123);

    public static ThreadLocal<Random> tRnd = new ThreadLocal<Random>() {
        @Override
        protected Random initialValue() {
            return new Random(123);
        }
    };

    public static class RndTask implements Callable<Long> {

        private int mode = 0;

        public RndTask(int mode) {
            this.mode = mode;
        }

        public Random getRandom() {
            if (mode == 0) {
                return random;
            } else if (mode == 1) {
                return tRnd.get();
            } else {
                return null;
            }
        }

        @Override
        public Long call() throws Exception {
            long b = System.currentTimeMillis();
            for (int i = 0; i < GEN_COUNT; i++) {
                getRandom().nextInt();
            }
            long e = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "  spend  " + (e - b) + "ms");
            return e - b;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Future<Long>[] futures = new Future[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            futures[i] = service.submit(new RndTask(0));
        }

        long totalTime = 0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            totalTime += futures[i].get();
        }
        System.out.println("多线程访问同一个 Random 实例：" + totalTime + "ms");

        for (int i = 0; i < THREAD_COUNT; i++) {
            futures[i] = service.submit(new RndTask(1));
        }
        totalTime = 0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            totalTime += futures[i].get();
        }
        System.out.println("使用ThreadLocal包装 Random 实例：" + totalTime + "ms");
        service.shutdown();
    }
}
