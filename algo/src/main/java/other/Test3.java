package other;

public class Test3 {
    public static void main(String[] args) {

        int[] nums = {1, 2, 34, 55, 66, 77, 88, 99};
        int i = binarySearch(nums, 55);
        System.out.println("i = " + i);
        boolean aba = isPalindrome("aabxxaxbaa");
        System.out.println("aba = " + aba);

    }

    static int binarySearch(int[] nums, int target) {
        // 一左一右两个指针相向而行
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (right + left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid - 1;
        }
        return -1;
    }

    static boolean isPalindrome(String req) {
        int left = 0, right = req.length() - 1;
        while (left < right) {
            if (req.charAt(left) != req.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
