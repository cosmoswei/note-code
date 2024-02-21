package wei.demo06;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author cosmoswei
 */
public class ThreadLocalDemo {

//    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    public static class ParseDate implements Runnable {
//
//        int i = 0;
//
//        public ParseDate(int i) {
//            this.i = i;
//        }
//
//        @Override
//        public void run() {
//            try {
//                Date date = sdf.parse("2021-8-2 19:04: " + i % 60);
//                System.out.println(i + ":" + date);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<SimpleDateFormat>();

    public static class ParseDate implements Runnable {
        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                if (tl.get() == null) {
                    tl.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                }
                Date date = tl.get().parse("2021-8-3 17:04: " + i % 60);
                System.out.println(i + ":" + date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            es.execute(new ParseDate(i));
        }
    }
}
