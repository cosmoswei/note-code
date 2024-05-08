package com.wei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {
    public static void bucketSort(int[] arr, int numBuckets) {
        // 创建桶
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(new ArrayList<>());
        }

        // 将元素分配到桶中
        for (int num : arr) {
            int bucketIndex = num / numBuckets;
            buckets.get(bucketIndex).add(num);
        }

        // 对每个桶中的元素进行排序
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
        }

        // 将桶中的元素依次取出，得到有序序列
        int index = 0;
        for (List<Integer> bucket : buckets) {
            for (int num : bucket) {
                arr[index++] = num;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {29, 25, 10, 7, 49, 36, 17};
        int numBuckets = 8;

        bucketSort(arr, numBuckets);

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
