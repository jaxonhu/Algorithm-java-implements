package binary_search;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2019/8/16
 * @Time: 1:45 AM
 * @Project: Algorithm-Java-implements
 */
public class IntervalSearch {

    public static int getSectionLeft(int leftK, int[] nums) {
        int low = 0, high = nums.length - 1;
        while(low < high) {
            int mid = (low + high) / 2;
            long temp = nums[mid];
            if(temp < leftK) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
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

        int[] nums = {0,0,0,0,1,1,3,4,5,5,5,7,9,9,9,10};
        int left = getSectionLeft(1, nums);
        int right = getSectionRight(-1, nums);
        System.out.println("left: " + left);
        System.out.println("right: " + right);
    }

}
