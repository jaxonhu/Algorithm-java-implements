package arraytype;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/13
 * @Time: 下午2:19
 * @Project: Algorithm-Java-implements
 */
public class NextPermutation {


    /**
     *   1 2 3 ——>  1 3 2
     *   1 3 2 ——>  2 1 3
     *   思路：从后往前遍历，找到第一个nums[i] < nums[j]的 ，然后交换i和j，随后将nums[i-j]的数字
     *   倒置
     *
     *   我写的这个是求下一个更大的排列，原题是求下一个排列，也就是说，3 2 1的下一个排列是1 2 3
     *
     *   写错了，应该是比较相邻两个元素
     */
    public void nextPermutation(int[] nums) {

        int n = nums.length;
        if(n == 0) return;

        int index = n - 2;

        while(index >= 0 && nums[index] >= nums[index + 1]) {
            index -= 1;
        }

        if(index != -1) {
            int j = n - 1;
            while(j >= 0 && nums[j] <= nums[index]) {
                j -= 1;
            }
            swap(nums, index, j);
        }
        reverse(nums, index +1 , n-1);

    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start, int end) {
        if(start >= end) return;
        for(int i = start, j = end ; i <= j ; ++ i, --j) {
            swap(nums, i, j);
        }
    }

    /**
     * 这里写求下一个排列：
     */


}
