package other;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int k = scan.nextInt();
        int[] a = new int[1000010];
        for (int i = 1; i <= n; i++) {
            a[i] = scan.nextInt();//正常读入
        }
        int l = 0, r = 100000001, m;//r=100000001
        //l是能取到的最小值（所以是0，也方便最后直接输出，而不要一堆代码特判），r是绝对取不到的值
        while (l + 1 < r)//模板
        {
            m = (l + r) / 2;
            int cnt = 0;//以m为长度能够切出木板的段数
            for (int i = 1; i <= n; i++)
                cnt += a[i] / m;//c++自带整除，也可以用floor
            //枚举每段木材能切出多少木板
            if (cnt >= k) l = m;//切得多或刚好等于，则说明还有可能长度更长
            else r = m;//切得少，说明长度太大了
        }
        System.out.println(l);
        scan.close();
    }
}