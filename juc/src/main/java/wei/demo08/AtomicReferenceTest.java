package wei.demo08;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author cosmoswei
 */
public class AtomicReferenceTest {

    static AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19, 0);

    //ABA 问题

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            final int timesStamp = money.getStamp();
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        while (true) {
                            Integer m = money.getReference();
                            if (m < 20) {
                                if (money.compareAndSet(m, m + 20, timesStamp, timesStamp + 1)) {
                                    System.out.println("余额小于 20 元，充值成功，余额：" + money.getReference() + "元");
                                    break;
                                }
                            } else {
                                // m > 20
                                break;
                            }
                        }
                    }
                }
            }.start();
        }

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    while (true) {
                        int timeStamp = money.getStamp();
                        Integer m = money.getReference();
                        if (m > 10) {
                            System.out.println("大于 10 元");
                            if (money.compareAndSet(m, m - 10, timeStamp, timeStamp + 1)) {
                                System.out.println("成功消费 10 元，余额：" + money.getReference() + "元");
                                break;
                            }
                        } else {
                            System.out.println("余额不足 10 元");
                            break;
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
