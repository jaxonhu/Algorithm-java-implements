package dynamicprogramming;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/8
 * @Time: 下午8:41
 * @Project: Algorithm-Java-implements
 */
public class JumpGame {

    /**
     *  大疆的笔试题，我的第一思路是用回溯，大川用dp做，看来我还是太年轻了
     *  dp的做法思路是维护一个局部解local和全局解global，
     *  local ->  i + nums[i] 表示从位置i开始可以到达的最远点
     *  global -> Math.max(local, global)
     *
     */

    public boolean canJump(int[] nums) {
        int n = nums.length;
        if(n == 0) return false;
        if(n == 1) return true;
        boolean res = true;
        int local;
        int global = nums[0];
        for(int i = 0 ; i < n ; ++ i) {
            if(global < i) {
                res = false;
                break;
            }
            local = i + nums[i];
            global = Math.max(global, local);
        }
        return res;
    }

    /**
     * 还有一个思路是O(n)的遍历，维护区间left和right，以及跳跃的次数，当right
     * 覆盖到最远端时跳出循环。
     */

    public boolean canJump2(int[] nums) {
        int n = nums.length;
        if(n == 0) return false;
        if(n == 1) return true;
        boolean res = false;
        int left = 0, right = 0;
        while(right < n -  1) {
            int tempr = right;
            for(int i = left ; i <= right ; ++ i) {
                int new_left = i + nums[i] == 0 ? 0 : 1;
                int new_right = i + nums[i];
                left = Math.max(left, new_left);
                right = Math.max(right, new_right);
                if(right >= n - 1) {
                    return true;
                }
            }
            if(right <= tempr) {
                return false;
            }
        }
        return true;
    }

    /**
     * JumpGame II
     */

    public int jump(int[] nums) {
        int n = nums.length;
        if(n == 0) return -1;
        if(n == 1) return 0;
        boolean res = false;
        int left = 0, right = 0;
        int jump = 0;
        while(right < n -  1) {
            int templ = left;
            int tempr = right;
            for(int i = templ ; i <= tempr ; ++ i) {
                int new_left = i + nums[i] == 0 ? 0 : 1;
                int new_right = i + nums[i];
                left = Math.max(left, new_left);
                right = Math.max(right, new_right);
                if(right >= n - 1) {
                    return jump+1;
                }
            }
            jump += 1;
            if(right <= tempr) {
                return -1;
            }
        }
        return jump;
    }
}
