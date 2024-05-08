package com.wei;

//作者：JavaBuild
//链接：https://www.zhihu.com/question/20733617/answer/3411480260
//来源：知乎
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
public class XorTest {
    public static void main(String[] args) {
        test1();
        test2();
    }
    public static void test1() {
        int number = Integer.MAX_VALUE;
        int a = 1;
        long start = System.currentTimeMillis();

        for (int i = 1; i < number; i++) {
            a %= i;
        }
        long end = System.currentTimeMillis();
        System.out.println("第1种" +(end - start) + "毫秒");
    }

    public static void test2() {
        int number = Integer.MAX_VALUE;
        int a = 1;
        long start2 = System.currentTimeMillis();
        for (int i = 1; i < number; i++) {
            a &= i;
        }
        long end2 = System.currentTimeMillis();
        System.out.println("第2种" + (end2 - start2) + "毫秒");
    }
}