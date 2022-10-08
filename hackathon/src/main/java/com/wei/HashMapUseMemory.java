package com.wei;
 
import java.util.HashMap;
 
/**
 * @author ranbo
 * @version V1.0
 * @Title:
 * @Package Java2
 * @Description:
 * @date 2018/7/26 下午10:55
 */
public class HashMapUseMemory {
 
 
 
    public static void main(String[] args) {
        double start = 0;
        double end = 0;
        System.gc();
        start = Runtime.getRuntime().freeMemory();  // 在java程序运行的过程的，内存总是慢慢的从操作系统那里挖的，基本上是用多少挖多少，但是java虚拟机100％的情况下是会稍微多挖一点的，
                                                    // 这些挖过来而又没有用上的内存，实际上就是freeMemory()
        System.out.println("start: " + start);
        System.out.println("end: " + end);
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            hashMap.put(i, 128);   // value 为128 和 127 就是int和Integer的区别，(16 byte - 4 byte) * 100w就是 12 M的样子  IntegerCache
            if(i % 10000 == 0) {
                end = Runtime.getRuntime().freeMemory();
                System.out.println("start: " + start);
                System.out.println("end: " + end);
                System.out.println("HashMap对象占内存：" + (end - start) + " byte");
                System.out.println("HashMap对象占内存：" + (end - start) / (1024 * 1024) + " M");
            }
        }
        end = Runtime.getRuntime().freeMemory();
        System.out.println("start: " + start);
        System.out.println("end: " + end);
        System.out.println("HashMap对象占内存：" + (end - start) + " byte");
        System.out.println("HashMap对象占内存：" + (end - start) / (1024 * 1024) + " M");
        System.out.println("----------清除了没有对象引用的对象");  // 即不可达对象
        System.gc(); // 清除了没有对象引用的对象，比如里面的临时变量，个人觉得 hash 也是
 
        end = Runtime.getRuntime().freeMemory();
        System.out.println("start: " + start);
        System.out.println("end: " + end);
        System.out.println("HashMap对象占内存：" + (end - start) + " byte");
        System.out.println("HashMap对象占内存：" + (end - start) / (1024 * 1024) + " M");
    }
 
}