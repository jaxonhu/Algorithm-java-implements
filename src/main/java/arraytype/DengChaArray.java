package arraytype;

public class DengChaArray {

    /**
     *
     *  ﻿给定一个数组，找出其等差数列的个数。
     *
     * 等差数列定义：数组中连续三个（或三个以上）的数字组成的等差序列。
     *
     * 输入：[1,2,3,4,6,7,8]
     *
     * 输出: 4
     *
     * 分析：输入数组中含有的等差数列为：[1,2,3]、[2,3,4]、[1,2,3,4]、[6,7,8]
     *
     * 所以输出4
     */
    public int getDengChaArrays(int[] nums) {

        int[] res = new int[1];
        int n = nums.length;
        for(int i = 0 ; i < n-2 ; ++ i) {
            int delta = nums[i+1] - nums[i];
            recursive(nums, 2, delta, i, res);
        }
        return res[0];
    }

    public void recursive(int[] nums, int length, int delta, int index, int[] res) {
        int n = nums.length;
        if(index >= n-1)
            return;
        int curDelta = nums[index+1] - nums[index];
        if(curDelta == delta) {
            if(length >= 3) {
                res[0] = res[0] + 1;
            }
            recursive(nums, length + 1, delta, index+1, res);
        }
    }


    public static void main(String[] args) {

        DengChaArray d = new DengChaArray();
        int[] nums = {1,2,3,4,6,7,8};
        System.out.println(d.getDengChaArrays(nums));

    }
}
