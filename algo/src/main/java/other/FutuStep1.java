package other;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class FutuStep1 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 20};
        //  12 10
        int target = 1;
        List<Integer> integers = get(nums, target);
        integers.forEach(System.out::println);
    }

    // 给定一个排序后的整数数组，找到两个数的 差 等于目标值。你需要返回一个包含两个数字的列表 [num1, num2], 使得 num1 与 num2 的差为 target，同时 num1 必须小于 num2。
    // x1 - x2 = target
    private static List<Integer> get(int[] nums, int target) {
        List<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        // 构建一个
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
//            System.out.println("num = " + num);
//            int resultAdd = target - num;
//            if (resultAdd < 0) {
//                resultAdd = Math.abs(resultAdd);
//            }
//            System.out.println("resultAdd = " + resultAdd);
            map.putIfAbsent(num, i);
        }
        for (int i = nums.length - 1; i > 0; i--) {
            int i1 = nums[i] - target;
            Integer index = map.get(i1);
            if (Objects.nonNull(index)) {
                return Lists.newArrayList(nums[i], nums[index]);
            }
        }
        return res;
    }
}
