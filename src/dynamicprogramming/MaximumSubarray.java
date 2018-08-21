package dynamicprogramming;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/8
 * @Time: 下午4:21
 * @Project: Algorithm-Java-implements
 */
public class MaximumSubarray {


    /**
     *  同理 local[i]代表到第i个数为止的局部最大和，包括第i个数
     *  global[i]代表到第i个数的全局最大和
     *  可以得到动态规划方程：
     *  local[i] = Math.max(local[i-1] + nums[i], nums[i])
     *  global[i] = Math.max(global[i-1], local[i])
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        int[] local = new int[n];
        int[] global = new int[n];
        local[0] = nums[0];
        global[0] = nums[0];
        for(int i = 1 ; i < n ; ++ i) {
            local[i] = Math.max(local[i-1] + nums[i], nums[i]);
            global[i] = Math.max(local[i], global[i-1]);
        }
        return global[n-1];
    }

    /**
     * 也可以用分治的思路去做
     */

    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        return divid(nums, 0, n-1, Integer.MIN_VALUE);
    }

    public int divid(int[] nums, int left, int right, int tmax) {

        if(left > right) return Integer.MIN_VALUE;
        int mid = (left + right) >> 1;

        int leftmax = divid(nums, left, mid-1, tmax);
        int rightmax = divid(nums, mid+1, right, tmax);

        tmax = Math.max(tmax, Math.max(leftmax, rightmax));

        int sum = 0;
        int lmax = 0;
        for(int i = mid - 1 ; i >= left; -- i) {
            sum = sum + nums[i];
            lmax = Math.max(lmax, sum);
        }
        sum = 0;
        int rmax = 0;
        for(int i = mid + 1 ; i <= right ; ++ i) {
            sum = sum + nums[i];
            rmax = Math.max(rmax, sum);
        }

        tmax = Math.max(tmax, lmax + rmax + nums[mid]);

        return tmax;

    }

    /**
     * 引申一下，如果是product sum，
     * 晚上可以刷一下
     */
}
