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
        if(n <= 1) return;
        int i = n-1;
        while(i > 0 && (nums[i] <= nums[i-1])) {
            i--;
        }
        if(i > 0) {
            int j = n-1;
            i--;
            while(j >= i && nums[j] <= nums[i]) j--;
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
            reverse(nums, i+1, n-1);
        } else
            reverse(nums, i, n-1);

    }

    void reverse(int[] nums, int start, int end) {
        if(start >= end) return;
        int i = start;
        int j = end;
        while(i < j) {
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
            i++;j--;
        }
    }


}
