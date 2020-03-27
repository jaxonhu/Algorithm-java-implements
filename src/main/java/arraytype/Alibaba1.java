package arraytype;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/19
 * @Time: 下午10:19
 * @Project: Algorithm-Java-implements
 */
public class Alibaba1 {

    /**
     *     5 6 1 3.0 3.2
     *
     *  1. start 0  end 4  mid = 2
     *  2. start 0  end 2  mid = 1
     *  3. start 1  end 2  mid = 1
     *  4. start 2  end 2  mid = 2
     *
     *
     *
     */

    public double getKth(double[] nums, int k) {
        int n = nums.length;
        if(n < k) return -1;
        int start = 0;
        int end = n-1;
        int index_temp = 0;
        while( start <= end  ) {
            if(nums[start] < nums[end] || start == end) {
                index_temp = start;
                break;
            }
            int mid = (start + end) >> 1;
            if(nums[mid] < nums[end]) {
                end = mid;
            }else if(nums[mid] > nums[end]) {
                start = mid+1;
            }else{
                end --;
            }
        }
        System.out.println(index_temp);
        return nums[(index_temp + k - 1) % n];
    }


    int getKth2(int[] nums, int k) {
        int n = nums.length;
        if(n == 0) return -1;
        int start = 0 ;
        int end = n-1;
        while(start < end) {
            int mid = (start+end) / 2;
            if(nums[mid] > nums[end]) {
                start = mid + 1;
            } else if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                end --;
            }
        }
        return nums[(end + k - 1) %n];
    }

    public static void main(String[] args) {
        int[] array = {1, 1, 5, 6, 1, 1, 1};
        Alibaba1 a = new Alibaba1();
        System.out.println(a.getKth2(array, 6));
    }
}
