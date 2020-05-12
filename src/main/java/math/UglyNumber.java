package math;

public class UglyNumber {

    /**
     *  把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
     *  习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
     *
     *  思路： 根据规律丑数可以从两个丑数相乘而来
     */
    public int GetUglyNumber_Solution(int index) {
       int[] store = new int[index];
       if(index == 0) return 0;
       store[0] = 1;
       if(index == 1)
           return store[index-1];
       int count = 0;
       count ++;
       while(count < index) {
           int twoMin = Integer.MAX_VALUE;
           for(int i = 0 ; i < count ; ++ i) {
               if(store[i] * 2 > store[count-1])
                   twoMin = Math.min(twoMin, store[i]*2);
           }
           int threeMin = Integer.MAX_VALUE;
           for(int i = 0 ; i < count ; ++ i) {
               if(store[i] * 3 > store[count-1])
                   twoMin = Math.min(twoMin, store[i]*3);
           }
           int fiveMin = Integer.MAX_VALUE;
           for(int i = 0 ; i < count ; ++ i) {
               if(store[i] * 5 > store[count-1])
                   twoMin = Math.min(twoMin, store[i]*5);
           }
           store[count] = Math.min(twoMin, Math.min(threeMin, fiveMin));
           count ++;
       }
       return store[index-1];
    }

    /**
     *  动态规划解法
     */
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int n2 = 0, n3 = 0, n5 = 0;
        for(int i = 1 ; i < n ; i ++) {
            int nx2 = dp[n2] * 2;
            int nx3 = dp[n3] * 3;
            int nx5 = dp[n5] * 5;
            dp[i] = Math.min(nx2, Math.min(nx3, nx5));
            if(dp[i] == nx2) n2 ++;
            if(dp[i] == nx3) n3 ++;
            if(dp[i] == nx5) n5 ++;
        }
        return dp[n-1];
    }
}
