package com.wei.leetcode.f500;

public class Solution_34 {

    public static void main(String[] args) {
        Solution_34 solution26 = new Solution_34();
        int[] nums = {2, 2};
        int[] ints = solution26.searchRange(nums, 2);
        for (int s = 0; s < ints.length; s++) {
            System.out.println("s = " + ints[s]);
        }
    }

    public int[] searchRange(int[] nums, int target) {
        int length = nums.length - 1;

        int left = 0;
        int right = length;
        int leftInd;
        int rightInd;
        // 找到左边界
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        // 判断 target 是否存在于 nums 中
        if (left < 0 || left >= nums.length) {
            leftInd = -1;
        } else {
            leftInd = nums[left] == target ? left : -1;
        }
        // 判断一下 nums[left] 是不是 target

        left = 0;
        right = length;
        // 找到右边界
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        if (right < 0 || right >= nums.length) {
            rightInd = -1;
        } else {
            rightInd = nums[right] == target ? right : -1;
        }
        return new int[]{leftInd, rightInd};
//        return new int[]{left_bound(nums, target), right_bound(nums, target)};
    }


    // 找到左边界
//        while (left <= right) {
//            int mid = (left + right) / 2;
//            if (nums[mid] == target) {
//                right = mid - 1;
//            } else if (nums[mid] > target) {
//                right = mid - 1;
//            } else if (nums[mid] < target) {
//                left = mid + 1;
//            }
//        }
//        leftInd = (nums[left] == target) ? left : -1;

    int left_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定左侧边界
                right = mid - 1;
            }
        }
        // 判断 target 是否存在于 nums 中
        if (left < 0 || left >= nums.length) {
            return -1;
        }
        // 判断一下 nums[left] 是不是 target
        return nums[left] == target ? left : -1;
    }


    // 找到右边界
//        while (left <= right) {
//        int mid = (left + right) / 2;
//        if (nums[mid] == target) {
//            left = mid + 1;
//        } else if (nums[mid] > target) {
//            right = mid - 1;
//        } else if (nums[mid] < target) {
//            left = mid + 1;
//        }
//    }
    int right_bound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] == target) {
                // 别返回，锁定右侧边界
                left = mid + 1;
            }
        }
        // 判断 target 是否存在于 nums 中
        // if (left - 1 < 0 || left - 1 >= nums.length) {
        //     return -1;
        // }

        // 由于 while 的结束条件是 right == left - 1，且现在在求右边界
        // 所以用 right 替代 left - 1 更好记
        if (right < 0 || right >= nums.length) {
            return -1;
        }
        return nums[right] == target ? right : -1;
    }

    public int[] searchRange1(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

}
