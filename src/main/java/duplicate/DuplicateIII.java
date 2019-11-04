package duplicate;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2019/11/4
 * @ Time: 7:58 PM
 * @ Project: Algorithm-Java-implements
 */
public class DuplicateIII {


    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(t < 0) {
            return false;
        }
        TreeSet<Integer> sortedK = new TreeSet<>();
        for(int i = 0; i < nums.length; i++) {
            if(i > k) {
                // Remove takes O(logk)
                sortedK.remove(nums[i - k - 1]);
            }
            Integer ceil = sortedK.ceiling(nums[i]);
            if(ceil != null && (ceil - t <= nums[i])) {
                return true;
            }
            Integer floor = sortedK.floor(nums[i]);
            if(floor != null && (floor + t >= nums[i])) {
                return true;
            }
            sortedK.add(nums[i]);
        }
        return false;
    }


    public static void main(String[] args) {

    }
}
