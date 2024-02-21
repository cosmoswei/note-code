package wei.demo01;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author cosmoswei
 */
public class ThreadPoolTest {
    @Test
    public void FixedThreadPoolTest() {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);

//        自定义线程池
        ExecutorService executorService1 = new ThreadPoolExecutor(
                2, 10, 4,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        try {
            for (int i = 0; i < 26; i++) {
                final int temp = i;
                executorService1.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "  OK!  " + temp);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService1.shutdown();
        }

    }
}
