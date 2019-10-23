package binary_search;

import java.util.List;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2019/8/16
 * @Time: 1:45 AM
 * @Project: Algorithm-Java-implements
 */
public class IntervalSearch {

    public static int getSectionLeft(int leftK, int[] nums) {
        int low = 0, high = nums.length - 1, mid;
        while (low <= high && leftK >= nums[low] && leftK <= nums[high]) {
//            mid = (low + high) / 2 + (low + high) % 2;
            if (low == high)
            {
                if (nums[low] == low) return low;
                return -1;
            }
            mid = low + (int)((high - low) * ((float)(leftK - nums[low]) / (float)(nums[high] - nums[low])));
            if (nums[mid] < leftK) {
                low = mid + 1;
            } else if(nums[mid] > leftK){
                high = mid - 1;
            }else {
                return mid;
            }
            System.out.println("low: " + low + " high" + high);
        }
//        if(high > 0 && nums[high] > leftK) {
//            high--;
//        }
        if(leftK < nums[high])
            high --;
        return high;
    }

    /**
     *  递归右查询
     * @param rightK 区间右端值 闭区间
     * @return
     */
    public static int getSectionRight(long rightK, int[] nums) {

        int low = 0, high = nums.length - 1;
        while(low < high) {
            int mid = (low + high) / 2 + (low + high) % 2;
            long temp = nums[mid];
            if(temp > rightK) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }
        return high;
    }



    public static void main(String[] args) {

        List<Integer> r = null;
        r = null;
        int[] nums1 = {0, 202, 404, 606, 808, 1010, 1212, 1414, 1616, 1818, 2020};
        int[] nums2 = {5000, 5202, 5404, 5606, 5808, 6010, 6212, 6414, 6616, 6818, 7020};

//        int[] nums = {0,2};
        int left = getSectionLeft(6413, nums2);
        int right = getSectionRight(27, nums2);
        System.out.println("left: " + left);
        System.out.println("right: " + right);
    }

}
