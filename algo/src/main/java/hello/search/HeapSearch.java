package hello.search;

import java.util.HashMap;
import java.util.Map;

public class HeapSearch {


    /* 方法二：辅助哈希表 */
    int[] twoSumHashTable(int[] nums, int target) {
        int size = nums.length;
        // 辅助哈希表，空间复杂度为 O(n)
        Map<Integer, Integer> dic = new HashMap<>();
        // 单层循环，时间复杂度为 O(n)
        for (int i = 0; i < size; i++) {
            if (dic.containsKey(target - nums[i])) {
                return new int[]{dic.get(target - nums[i]), i};
            }
            dic.put(nums[i], i);
        }
        return new int[0];
    }
}
