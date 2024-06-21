//package com.wei;
//
//public class BitMap {
//    public static void count(Integer inputSize) {
//        RoaringBitMap BitMap = new RoaringBitMap();
//        BitMap.add(0L, inputSize);
//        //获取BitMap个数
//        //
//        int cardinality = BitMap.getCardinality();
//        //获取BitMap压缩大小
//        int compressSizeIntBytes = BitMap.getSizeInBytes();
//        //删除压缩（移除行程编码，将container退化为BitMapContainer 或 ArrayContainer）
//        BitMap.removeRunCompression();
//        //获取BitMap不压缩大小
//        int uncompressSizeIntBytes = BitMap.getSizeInBytes();
//        System.out.println("Roaring BitMap个数：" + cardinality);
//        System.out.println("最好情况，BitMap压缩大小：" + compressSizeIntBytes / 1024 + "KB");
//        System.out.println("最坏情况，BitMap不压缩大小：" + uncompressSizeIntBytes / 1024 / 1024 + "MB");
//        BitSet bitSet = new BitSet();
//        for (int i = 0; i < inputSize; i++) {
//            bitSet.set(i);
//        }
//        //获取BitMap大小
//        int size = bitSet.size();
//        System.out.println("BitMap个数：" + bitSet.length());
//        System.out.println("BitMap大小：" + size / 8 / 1024 / 1024 + "MB");
//    }
//}
