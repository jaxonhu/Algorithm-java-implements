package sort;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2019/1/18
 * @Time: 3:29 PM
 * @Project: Algorithm-Java-implements
 */
public class RadixSort {

    /**
     * 基数排序，原题 leetcode 164
     * 基本思路：使用基数排序的思想，先进行粗排(桶排序，类似于数据分析中的z-score)，生成maxs和mins两个数组，保证数组内部除了0都是有序的，而且对应位置上
     * maxs大于mins，然后...我也没懂
     */

    public static int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;
        int len = nums.length, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, res = Integer.MIN_VALUE;
        int[] mins = new int[len + 1], maxs = new int[len + 1];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int diff = max - min;
        if (diff == 0)
            return 0;
        for (int num : nums) {
            int index = (int) ((num - min) * 1.0 / diff * len);
            mins[index] = mins[index] == 0 ? num : Math.min(mins[index], num);
            maxs[index] = Math.max(maxs[index], num);
        }
        int lastMax = maxs[0];
        for (int i = 1; i <= len; i++)
            if (mins[i] != 0) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = {8, 4, 9, 1, 11, 6};
        System.out.println(maximumGap(nums));

    }

}
