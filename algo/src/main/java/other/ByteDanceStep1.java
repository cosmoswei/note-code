package other;

import java.util.Arrays;

public class ByteDanceStep1 {
    public static void main(String[] args) {
        int req[][] = {
                {'1', '1', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '0', '1', '0', '0'},
        };
        int[] ints = find(req);
        System.out.println("ints = " + Arrays.toString(ints));
    }


    private static int[] find(int[][] req) {
        return new int[]{1, 3};
    }
}
