package dynamicprogramming;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/4/17
 * @ Time: 2:47 下午
 * @ Project: Algorithm-Java-implements
 */
public class NumberWaysPaintN3Grid {


    /**
     *
     * 你有一个 n x 3 的网格图 grid ，你需要用 红，黄，绿 三种颜色之一给每一个格子上色，且确保相邻格子颜色不同（也就是有相同水平边或者垂直边的格子颜色不同）。
     *
     * 给你网格图的行数 n 。
     *
     * 请你返回给 grid 涂色的方案数。由于答案可能会非常大，请你返回答案对 10^9 + 7 取余的结果。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-ways-to-paint-n-x-3-grid
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 题解：
     *      n=1时， ABC类有6个，ABA类有6个
     *
     *      ABC——>  下一层有2个ABC类型，2个ABA类型
     *      ABA——>  下一层有2个ABC类型，3个ABA类型
     *
     */

    public int numOfWays(int n) {
        long ABC = 6;
        long ABA = 6;
        long mod = 1000000007;
        long res = 0;
        for(int i = 1 ;  i < n ; i ++) {
            long newABC = ((2 * ABC)%mod + (2 * ABA)%mod)%mod;
            long newABA = ((2* ABC)%mod +  (3 * ABA)%mod)%mod;
            ABC = newABC;
            ABA = newABA;
        }
        res = (ABC + ABA)%mod;
        return (int)res;
    }
}
