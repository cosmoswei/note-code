package other;

import java.util.Scanner;

class AliStep1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] que = new int[n + 1];
        que[0] = 0;
        for (int i = 1; i <= n; i++) {
            que[i] = scan.nextInt();
        }
        int bignum = scan.nextInt();
        for (int i = 1; i < n + 1; i++) {
            que[i] += que[i - 1];
        }
        int l = 0, r = 0, al = 0, ar = n;
        while (r <= n) {
            if (que[r] - que[l] < bignum) {
                r++;
            } else {
                if (r - l < ar - al) {
                    ar = r;
                    al = l;
                }
                l++;
            }
        }

        System.out.println((al + 1) + " " + ar);
        scan.close();
    }
}