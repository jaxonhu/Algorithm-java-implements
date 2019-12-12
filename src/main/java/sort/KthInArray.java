package sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2019/10/30
 * @ Time: 4:42 PM
 * @ Project: Algorithm-Java-implements
 */
public class KthInArray {

    /**
     *
     * 堆排序
     */
    public static int findKthLargest(int[] nums, int k) {

        for(int i = nums.length / 2 - 1 ; i >= 0 ; i--) {
            adjust(nums, i,  nums.length);
        }
        for(int j = nums.length - 1 ; j > nums.length - k ; j--) {
            int temp = nums[0];
            nums[0] = nums[j];
            nums[j] = temp;
            adjust(nums, 0, j);
        }
        return nums[0];
    }


    public static void adjust(int[] nums, int i, int length) {
        int temp = nums[i];
        for(int k = 2 * i + 1; k < length ; k = 2 * k + 1) {
            if(k + 1 < length  && nums[k+1] > nums[k])
                k ++;
            if(temp < nums[k]) {
                nums[i] = nums[k];
                i = k;
            } else {
                break;
            }
        }
        nums[i] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {8, 2, 6, 1, 6};
        System.out.println(findKthLargest(nums,1));
    }
}
