import co.paralleluniverse.fibers.Fiber;
import java.util.concurrent.CountDownLatch;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDown = new CountDownLatch(1);
        Fiber fiber = new Fiber(() -> {
            System.out.println(1);
        });
        fiber.start();
        System.out.println(2);
        countDown.await();
    }
}