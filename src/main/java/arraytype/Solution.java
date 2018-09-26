package arraytype;

import java.util.Scanner;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/22
 * @Time: 下午7:29
 * @Project: Algorithm-Java-implements
 */
public class Solution {


    public static int longestMountain(int[] A) {
        int max=0;
        int n = A.length;
        for (int i = 0; i < n - 1; i++) {
            if(A[i]>A[i+1]){
                int tempMax=1;
                //如果上升，变大1->4->7
                while (i<n-1 &&A[i]>A[i+1]){
                    i++;
                    tempMax++;
                }
                //如果下降变小7->3->2
                while (i<n-1 &&A[i]<A[i+1] ){
                    i++;
                    tempMax++;
                }
                //如果退出while循环，即A[i]>A[i+1]不成立，即A[i]<A[i+1]，现在又上升2->5，又A[i-1]>A[i],3->2,即3-2-5
                if(A[i-1]<A[i])//1-4-7-3-2-5
                    max=Math.max(max,tempMax);
                i--;//
            }
        }
        return max;
    }


    public static  void main(String[] args) {
        int[] A = {4, 3, 2, 5, 3};
        System.out.println(Solution.longestMountain(A));
    }
}
