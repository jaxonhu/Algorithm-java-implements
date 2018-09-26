package arraytype;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/7
 * @Time: 下午2:46
 * @Project: Algorithm-Java-implements
 */
public class TrappingRainWater {

    /**
     * 第一种做法：类似暴力
     */

    public int trap(int[] height) {
        int n = height.length;
        int sum = 0;
        if(n == 0) return 0;
        if(n == 1) return 0;
        int[] newheight = new int[n+2];
        newheight[0] = height[0];
        newheight[n+1] = height[n-1];
        for(int i = 1 ; i <= n ; ++ i) {
            newheight[i] = height[i-1];
        }
        for(int i = 1 ; i < n ; ++ i) {
            int q = i-1, p = i+1;
            int left_max = newheight[i];
            int right_max = newheight[i];
            while(q >= 0) {
                if(newheight[q] > left_max){
                    left_max = Math.max(left_max, newheight[q]);
                }
                q --;
            }
            while(p <= n+1) {
                if(newheight[p] > right_max) {
                    right_max = Math.max(right_max, newheight[p]);
                }
                p ++;
            }
            sum += Math.min(right_max,left_max) - newheight[i];
        }
        return sum;
    }

    /**
     * 第二种做法，两个指针
     */

    public int trap2(int[] height) {
        int sum = 0;
        int n = height.length;
        if(n == 0) return 0;
        if(n == 1) return 0;
        int l = 0;
        int r = n - 1;
        int left_max = height[0];
        int right_max = height[n-1];
        while(l < r) {
            if(height[l] < height[r]){
                left_max = Math.max(left_max, height[l]);
                sum += left_max - height[l];
                l ++;
            }else {
                right_max = Math.max(right_max, height[r]);
                sum += right_max - height[r];
                r --;
            }
        }
        return sum;
    }
}
