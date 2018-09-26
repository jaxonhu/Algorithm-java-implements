package binary_search;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/6/19
 * @Time: 下午4:56
 * @Project: Algorithm-Java-implements
 */
public class Minimum_Size_Subarray_Sum {

    public static int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        if(n == 1) return nums[0] >= s ? 1 : 0;
        int p = 0, q = 1;
        int min = Integer.MAX_VALUE;
        while(q <= n - 1) {
            if(p == q) {
                if(nums[p] >= s) {
                    min = 1;
                    break;
                }
            }else {
                int sum = 0;
                for(int i = p ; i <= q ; ++i) sum += nums[i];
                if(p <= q && sum >= s) {
                    min = Math.min(min, q - p + 1);
                    p ++;
                    continue;
                }
            }
            q ++;
        }
        return min;
    }

    /*
        滑动窗口解法
    */
    public int minSubArrayLen2(int s, int[] nums) {
        int l=0,r=-1; //nums[l...r]为滑动窗口
        int sum = 0 ;
        int res = nums.length + 1;
        while(l>=0&&l<nums.length){
            if(r+1<nums.length&&sum < s){
                r++;
                sum += nums[r];
            }else{
                sum -= nums[l];
                l++;
            }
            if(sum >= s){
                res = Math.min(res, r-l+1);
            }
        }
        if(res == nums.length + 1) return 0;
        return res;
    }

    /*
        更加简洁的一种答案，也很直观
    */
    public int minSubArrayLen3(int s, int[] nums) {
        int min_len=Integer.MAX_VALUE;
        int left=0;
        int right=0;
        int sum=0;
        for(int i=0;i<nums.length;i++) {
            sum+=nums[i];
            while(sum>=s) {
                min_len=Math.min(min_len, i-left+1);
                sum-=nums[left++];
            }
        }

        return min_len==Integer.MAX_VALUE?0:min_len;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen(7, nums));

    }
}
