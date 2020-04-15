package arraytype;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/4/15
 * @ Time: 10:05 下午
 * @ Project: Algorithm-Java-implements
 */
public class LongestMountain {



    /**
     *
     * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
     *
     * B.length >= 3
     * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
     * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
     *
     * 给出一个整数数组 A，返回最长 “山脉” 的长度。
     *
     * 如果不含有 “山脉” 则返回 0。
     *
     *  
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-mountain-in-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    /**
     *
     * 应为题目中要求山脉中相邻两个必须升序或者降序，不能前后相等，所以需要用三个状态记录
     * 0：前后相等
     * 1：升序
     * 2：降序
     *
     * 用prev记录山谷位置，peek记录是否出现山峰。则触发计算的条件如下：
     *
     * 当前升序，之前降序： 如果经过peek，触发计算
     * 当前相等，之前降序，如果经过peek，出发计算
     *
     *
     */
    public int longestMountain(int[] A) {
        int n = A.length;
        if(n <= 2) return 0;
        int direction = 0; // 不升不降
        if(A[0] < A[1]) direction = 1;  //up
        if(A[0] > A[1]) direction = 2; //down
        int MaxL = 0;
        int prev = 0;
        boolean peek = false; //是否经过山峰
        for(int i = 0 ; i < n - 1 ; i ++) {
            if(A[i] == A[i+1]) {
                if(direction == 2) { //如果之前是下降，当peek为true时触发计算
                    MaxL = peek?Math.max(MaxL, i - prev+1):MaxL;
                    peek = false;
                }
                prev = i+1;
                direction = 0;
            } else if(A[i+1] > A[i]) { //开始上升
                if(direction == 2) { //如果之前下降，根据peek判断是否触发计算
                    MaxL = peek?Math.max(MaxL, i - prev+1):MaxL;
                    prev = i;
                    peek = false;
                }
                direction = 1;
            } else {
                if(direction == 1) {    //如果之前上升，现在下降，则peek为true
                    peek = true;
                }
                direction = 2;
            }
        }
        if(peek) MaxL = Math.max(n-prev, MaxL);
        return MaxL;
    }

}
