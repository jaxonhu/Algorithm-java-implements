package math;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/5/12
 * @ Time: 10:55 下午
 * @ Project: Algorithm-Java-implements
 */
public class SuperUglyNumber {

    /**
     *
     * 编写一段程序来查找第 n 个超级丑数。
     *
     * 超级丑数是指其所有质因数都是长度为 k 的质数列表 primes 中的正整数。
     *
     * 示例:
     *
     * 输入: n = 12, primes = [2,7,13,19]
     * 输出: 32
     * 解释: 给定长度为 4 的质数列表 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
     * 说明:
     *
     * 1 是任何给定 primes 的超级丑数。
     *  给定 primes 中的数字以升序排列。
     * 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000 。
     * 第 n 个超级丑数确保在 32 位有符整数范围内。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/super-ugly-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */

    /**
     *  最小堆解法， 但是超时没有通过测试
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((n1, n2)-> {return n1 - n2;});
        Set<String> visited = new HashSet<>();
        int k = primes.length;
        int i = 1;
        int cur = 1;
        queue.offer(cur);
        while(i <= n && !queue.isEmpty()) {
            cur = queue.poll();
            System.out.println(cur);
            for(int j = 0 ; j < k ; j ++) {
                int nxt = cur * primes[j];
                if(!visited.contains(String.valueOf(nxt))) {
                    queue.offer(nxt);
                    visited.add(String.valueOf(nxt));
                }
            }
            i++;
        }
        return cur;
    }


    /**
     * 动态规划解法，类似 丑数I，只是需要处理重复的丑数
     */

    public int nthSuperUglyNumber2(int n, int[] primes) {
        int k = primes.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int[] pos = new int[k];
        for(int i = 1 ; i < n ; i ++) {
            int min = Integer.MAX_VALUE;
            int idx = -1;
            for(int j = 0 ; j < k ; j ++) {
                int tmp = dp[pos[j]] * primes[j];
                if(tmp < min) {
                    min = tmp;
                    idx = j;
                }
            }
            pos[idx] += 1;
            if(min == dp[i-1]) {
                i--;
                continue;
            }
            dp[i] = min;

        }
        return dp[n-1];
    }
}
