package com.wei;

import org.roaringbitmap.IntConsumer;
import org.roaringbitmap.RoaringBitmap;

public class Basic {

    public static void main(String[] args) {
        RoaringBitmap rr = RoaringBitmap.bitmapOf(1, 2, 3, 1000);
        RoaringBitmap rr2 = new RoaringBitmap();
        rr2.add(4000L, 4255L);
        rr.select(3); // 返回RBM中的第4个值,索引从0开始
        rr.rank(2); // 返回<=x的元素个数
        rr.contains(1000); // RBM是否包含1000,返回true
        rr.contains(7); // 返回false

        RoaringBitmap rror = RoaringBitmap.or(rr, rr2);// new bitmap
        rr.or(rr2); //两个RBM合并
        boolean equals = rror.equals(rr);// true
        if (!equals) throw new RuntimeException("bug");
        // 打印元素的个数
        long cardinality = rr.getLongCardinality();
        System.out.println(cardinality);
        rr.forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        });
    }
}
