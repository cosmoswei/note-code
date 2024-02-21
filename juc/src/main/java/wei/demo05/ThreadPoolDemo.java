package wei.demo05;

import java.util.concurrent.*;

/**
 * @author cosmoswei
 */
public class ThreadPoolDemo {
    public static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + "  Thread ID :"
                    + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        MyTask myTask = new MyTask();
//        ExecutorService service = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 12; i++) {
//           // service.submit(myTask);
//        }
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
//        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                    System.out.println(System.currentTimeMillis() / 1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, 0, 2, TimeUnit.SECONDS);
//
        MyTask myTask1 = new MyTask();
        ExecutorService es = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setDaemon(true);
                        System.out.println("create:" + thread);
                        return thread;
                    }
                },
                new ThreadPoolExecutor.AbortPolicy()
        );
        for (int i = 0; i < 5; i++) {
            es.submit(myTask1);
        }
        Thread.sleep(2000);
    }
}
