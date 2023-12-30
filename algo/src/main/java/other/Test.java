package other;

public class Test {

    public static void main(String[] args) {

        int[] int1 = {1, 4, 5};
        int[] int2 = {1, 3, 4};
        int[] int3 = {2, 6, 9};
        Test solution23 = new Test();
        solution23.test(int2, 2);
    }

    public static void test(int[] que, int bignum) {
        int n = que.length;
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
    }
}
