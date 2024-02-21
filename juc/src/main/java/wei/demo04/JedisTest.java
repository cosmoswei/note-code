package wei.demo04;

import redis.clients.jedis.Jedis;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author cosmoswei
 */
public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println(jedis.ping());
        Semaphore semaphore = new Semaphore(4);
        for (int i = 0; i < 16; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开车位");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

            }, String.valueOf(i)).start();
        }
    }
}
