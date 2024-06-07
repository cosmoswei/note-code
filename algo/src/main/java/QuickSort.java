public class QuickSort {
    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 找到分区索引
            int partitionIndex = partition(arr, low, high);

            // 对基准左边的子数组进行快速排序
            quickSort(arr, low, partitionIndex - 1);
            // 对基准右边的子数组进行快速排序
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];  // 选择基准元素
        int i = (low - 1);      // i 指向比基准小的区域的最后一个元素

        for (int j = low; j < high; j++) {
            // 如果当前元素小于或等于 pivot
            if (arr[j] <= pivot) {
                i++;

                // 交换 arr[i] 和 arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // 交换 arr[i+1] 和 arr[high] (或 pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(arr, 0, arr.length - 1);

        System.out.println("Sorted array: ");
        for (int value : arr) {
            System.out.println(value + " ");
        }
    }
}