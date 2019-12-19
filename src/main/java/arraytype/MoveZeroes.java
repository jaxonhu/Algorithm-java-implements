package arraytype;

import java.util.Arrays;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2019/12/18
 * @ Time: 3:36 PM
 * @ Project: Algorithm-Java-implements
 */
public class MoveZeroes {

    static void moveZeroes(int[] nums) {
        int len = nums.length;
        if(len == 0) return;
        int slow = 0, fast = 0;
        while(fast < len) {
            if(nums[fast] == 0) {
                fast ++;
                continue;
            }
            nums[slow] = nums[fast];
            slow++;
            fast++;
        }
        for(int i = slow ; i < len ; i ++)
            nums[i] = 0;
    }

    public static void main(String[] args) {

        int[] nums = {0 , 1, 0, 3, 1, 2};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
