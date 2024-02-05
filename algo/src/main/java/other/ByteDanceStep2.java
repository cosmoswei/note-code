package other;

import java.util.Arrays;

public class ByteDanceStep2 {
    public static void main(String[] args) {
        // Scanner input=new Scanner(System.in);
        // String str=input.next();
        int nums[] = {1, 2, 3, 4, 5, 6};
        func_name(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void func_name(int[] input) {
        int len = input.length;
        int handleIndex = 0;
        // 移动余为0
        for (int i = 0; i < len; i++) {
            if (input[i] % 3 == 0) {
                int temp = input[i]; // 1
                input[i] = input[handleIndex];
                input[handleIndex] = temp;
                handleIndex++;
            }
        }

        // 移动余为1
        for (int i = handleIndex; i < len; i++) {
            if (input[i] % 3 == 1) {
                int temp = input[i]; // 1
                input[i] = input[handleIndex];
                input[handleIndex] = temp;
                handleIndex++;
            }
        }

        // 移动余为2
//        for (int i = handleIndex; i < len; i++) {
//            if (input[i] % 3 == 2) {
//                int temp = input[i]; // 1
//                input[i] = input[handleIndex];
//                input[handleIndex] = temp;
//                handleIndex++;
//            }
//        }

    }
}