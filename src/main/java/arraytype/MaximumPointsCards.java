package arraytype;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/4/28
 * @ Time: 8:21 下午
 * @ Project: Algorithm-Java-implements
 */
public class MaximumPointsCards {

    /**
     *
     * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
     *
     * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
     *
     * 你的点数就是你拿到手中的所有卡牌的点数之和。
     *
     * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：cardPoints = [1,2,3,4,5,6,1], k = 3
     * 输出：12
     * 解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
     * 示例 2：
     *
     * 输入：cardPoints = [2,2,2], k = 2
     * 输出：4
     * 解释：无论你拿起哪两张卡牌，可获得的点数总是 4 。
     * 示例 3：
     *
     * 输入：cardPoints = [9,7,7,9,7,7,9], k = 7
     * 输出：55
     * 解释：你必须拿起所有卡牌，可以获得的点数为所有卡牌的点数之和。
     * 示例 4：
     *
     * 输入：cardPoints = [1,1000,1], k = 1
     * 输出：1
     * 解释：你无法拿到中间那张卡牌，所以可以获得的最大点数为 1 。
     * 示例 5：
     *
     * 输入：cardPoints = [1,79,80,1,1,1,200,1], k = 3
     * 输出：202
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */

    /**
     *   算法复杂度为O(n^2) 超出时间限制
     */
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int max = 0;
        for(int p = 0 ; p <= k ; p ++) {
            int q = k - p;
            int left = 0, right = 0;
            for(int m = 0 ; m < p ; m ++) {
                left += cardPoints[m];
            }
            for(int m = 0; m < q ; m++){
                right += cardPoints[n-m-1];
            }
            if(right + left > max)
                max = right + left;
        }

        return max;
    }


    /**
     * 题目中 1 <= cardPoints.length <= 10^5 这个范围比较大，所以应该至少O(logn)的复杂度才能通过测试
     *
     */
    public int maxScore2(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int cur = 0;
        for(int i = 0 ; i < k ; i ++) {
            cur = cur + cardPoints[i];
        }
        int max = cur;
        for(int i = 0 ; i < k ; i ++) {
            cur = cur - cardPoints[k-i-1];
            cur = cur + cardPoints[n-i-1];
            max = Math.max(max, cur);
        }
        return max;
    }
}
