public class MergeSort {
    public void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            // 找到中间索引
            int m = (l + r) / 2;

            // 分别对左右两半进行归并排序
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // 合并排序好的两半
            merge(arr, l, m, r);
        }
    }

    private void merge(int[] arr, int l, int m, int r) {
        // 临时数组
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        // 复制数据到临时数组
        System.arraycopy(arr, l, L, 0, n1);
        System.arraycopy(arr, m + 1, R, 0, n2);

        // 合并临时数组回到 arr[l..r]
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // 复制左边剩余元素
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // 复制右边剩余元素
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(arr, 0, arr.length - 1);

        System.out.println("Sorted array: ");
        for (int value : arr) {
            System.out.print(value + " ");
        }
    }
}