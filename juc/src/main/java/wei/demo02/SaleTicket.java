package wei.demo02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cosmoswei
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}

class Ticket {
    private int numner = 600;

    Lock lock =new ReentrantLock();

    public synchronized void sale() {
        lock.lock();
        //    synchronized：本质就是排队
        try{
            if (numner > 0) {
                System.out.println(Thread.currentThread().getName() +
                        "卖出了第" + (numner--) + "张票，剩余：" + numner);
            }}finally {
            lock.unlock();
        }
    }
}
