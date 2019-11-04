package duplicate;

import java.util.HashMap;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2019/11/1
 * @ Time: 3:40 PM
 * @ Project: Algorithm-Java-implements
 */
public class DuplicateII {


    /**
     * Given an array of integers and an integer k,
     * find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j]
     * and the absolute difference between i and j is at most k.
     */

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        int len = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0 ; i < len ; i++) {
            if(map.containsKey(nums[i])) {
                int delta = i - map.get(nums[i]);
                map.put(nums[i], i);
                if(delta <= k) return true;
            } else
                map.put(nums[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1,2,3};
        System.out.println(containsNearbyDuplicate(nums, 2));
    }
}
