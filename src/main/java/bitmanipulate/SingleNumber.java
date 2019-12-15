package bitmanipulate;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2019/12/15
 * @ Time: 5:21 PM
 * @ Project: Algorithm-Java-implements
 */
public class SingleNumber {

    /**
     *
     * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
     * Find the two elements that appear only once.
     *
     * Input:  [1,2,1,3,2,5]
     * Output: [3,5]
     *
     */

    public int[] singleNumber(int[] nums) {
        int diff = 0;
        for(int i  = 0 ; i < nums.length ; i ++) {
            diff = diff ^ nums[i];
        }
        diff = diff & (-diff);
        int[] res = {0 , 0};
        for(int i = 0 ; i < nums.length ; i ++) {
            if((nums[i] & diff) == 0) {
                res[0] ^= nums[i];
            }
            if((nums[i] & diff) > 0) {
                res[1] ^= nums[i];
            }
        }
        return res;
    }
}
