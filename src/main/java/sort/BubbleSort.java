package sort;

import java.util.Arrays;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/3/19
 * @ Time: 11:02 AM
 * @ Project: Algorithm-Java-implements
 */
public class BubbleSort {

    public static void bubbleSort(int[] nums) {
        int n = nums.length;
        if(n == 0 || n == 1) return;
        int nextpos = Integer.MAX_VALUE;
        for(int i = 0 ; i < n ; i ++) {
            int end = Math.min(nextpos, n - i - 1);
            for(int j = 0 ; j < end ; j ++) {
                if(nums[j] < nums[j+1]) {
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                    nextpos = j;
                }
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = {1,5,2,4,5,6,7,2,1};
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
