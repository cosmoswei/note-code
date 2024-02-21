package wei.demo01;

public class OOMTest {
    public static void main(String[] args) {
        long max = Runtime.getRuntime().maxMemory();
        long total = Runtime.getRuntime().totalMemory();
        long avai = Runtime.getRuntime().availableProcessors();
        long free = Runtime.getRuntime().freeMemory();

        System.out.println(max / (1024 * 1024) + "MB");
        System.out.println(total / (1024 * 1024) + "MB");
        System.out.println(avai);
        System.out.println(free / (1024 * 1024) + "MB");
    }
}
