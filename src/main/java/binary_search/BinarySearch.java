package binary_search;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/5/10
 * @ Time: 12:03 下午
 * @ Project: Algorithm-Java-implements
 */
public class BinarySearch {

    /**
     * base 二分查找
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n;
        while(left < right ) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] < target) {
                left = mid + 1;
            } else
                right = mid - 1;
        }
        if(left >= n) return -1;
        return nums[left] == target ? left:-1;
    }

    /**
     * 二分查找左边界
     */
    public int binarySearch(int[] nums, int target) {
        // write your code here
        int n = nums.length;
        int left = 0;
        int right = n-1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                right = mid;
            } else if(nums[mid] < target) {
                left = mid + 1;
            } else
                right = mid - 1;
        }
        if(left >= n) return -1;
        return nums[left] == target ? left : -1;
    }

    /**
     * 二分搜索寻找右边界
     */
    public int lastPosition(int[] nums, int target) {
        // write your code here
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while(left < right ) {
            int mid = left + (right - left) / 2 + ((right - left) % 2);
            if(nums[mid] == target) {
                left = mid;
            } else if(nums[mid] < target) {
                left = mid + 1;
            } else
                right = mid - 1;
        }
        if(left >= n) return -1;
        return nums[left] == target ? left : -1;
    }

}
