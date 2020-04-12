package dynamicprogramming;

import org.junit.Test;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/4/7
 * @ Time: 11:32 下午
 * @ Project: Algorithm-Java-implements
 */
public class StoneGameIII {

    /**
     *
     * Alice 和 Bob 用几堆石子在做游戏。几堆石子排成一行，每堆石子都对应一个得分，由数组 stoneValue 给出。
     *
     * Alice 和 Bob 轮流取石子，Alice 总是先开始。在每个玩家的回合中，该玩家可以拿走剩下石子中的的前 1、2 或 3 堆石子 。比赛一直持续到所有石头都被拿走。
     *
     * 每个玩家的最终得分为他所拿到的每堆石子的对应得分之和。每个玩家的初始分数都是 0 。比赛的目标是决出最高分，得分最高的选手将会赢得比赛，比赛也可能会出现平局。
     *
     * 假设 Alice 和 Bob 都采取 最优策略 。如果 Alice 赢了就返回 "Alice" ，Bob 赢了就返回 "Bob"，平局（分数相同）返回 "Tie" 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：values = [1,2,3,7]
     * 输出："Bob"
     * 解释：Alice 总是会输，她的最佳选择是拿走前三堆，得分变成 6 。但是 Bob 的得分为 7，Bob 获胜。
     * 示例 2：
     *
     * 输入：values = [1,2,3,-9]
     * 输出："Alice"
     * 解释：Alice 要想获胜就必须在第一个回合拿走前三堆石子，给 Bob 留下负分。
     * 如果 Alice 只拿走第一堆，那么她的得分为 1，接下来 Bob 拿走第二、三堆，得分为 5 。之后 Alice 只能拿到分数 -9 的石子堆，输掉比赛。
     * 如果 Alice 拿走前两堆，那么她的得分为 3，接下来 Bob 拿走第三堆，得分为 3 。之后 Alice 只能拿到分数 -9 的石子堆，同样会输掉比赛。
     * 注意，他们都应该采取 最优策略 ，所以在这里 Alice 将选择能够使她获胜的方案。
     * 示例 3：
     *
     * 输入：values = [1,2,3,6]
     * 输出："Tie"
     * 解释：Alice 无法赢得比赛。如果她决定选择前三堆，她可以以平局结束比赛，否则她就会输。
     * 示例 4：
     *
     * 输入：values = [1,2,3,-1,-2,-3,7]
     * 输出："Alice"
     * 示例 5：
     *
     * 输入：values = [-1,-2,-3]
     * 输出："Tie"
     *
     * ========================================================
     *
     * 题解： 动态规划之零和博弈
     *
     *      dp[n][2] : Alice-bob
     */

    int N = 50000 + 50;
    int[][] dp = new int[N][2];
    boolean[][] visit = new boolean[N][2];
    int[] nums;
    int dfs(int idx, int turn, int n) {
        if(idx >= n) return 0;
        if(visit[idx][turn]) return dp[idx][turn];
        visit[idx][turn] = true;
        if(turn == 0) {
            int max = Integer.MIN_VALUE;
            int cur = 0;
            for(int i = idx ; i < n && i < idx+3 ; i ++) {
                cur += nums[i];
                max = Math.max(max, cur + dfs(i+1, 1, n));
            }
            dp[idx][0] = max;
        } else {
            int min = Integer.MAX_VALUE;
            int cur = 0;
            for(int i = idx ; i < n && i < idx+3 ; i ++) {
                cur += nums[i];
                min = Math.min(min, dfs(i+1, 0, n) - cur);
            }
            dp[idx][1] = min;
        }
        return dp[idx][turn];
    }


    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        nums = new int[n];
        System.arraycopy(stoneValue, 0, nums, 0, n);
        int ans = dfs(0, 0, n);
        if(ans > 0) return "Alice";
        if(ans < 0) return "Bob";
        return "Tie";
    }

    @Test
    public void stoneTest() {
        int[] stons = new int[] {1,2,3,7};
        System.out.println(stoneGameIII(stons));
    }


    /**
     *
     *  另一种dp解法
     *  目标结果：从0开始拿，所能拿到的最高分，如果最高分大于 sum/2 则alice赢，如果等于，则平局，如果小于，则bob赢
     *  中间状态：第 i 个，alice开始拿
     *  状态转移：如果从 i 个开始拿的最高分是已知解，那么从第 i 个bob开始拿的得分也是dp[i], 假设第 i 手是alice拿，那么
     *           i+1  i+2 i+3可以是bob拿，因此 第i个状态是从 i+1 i+2 i+3 转移而来
     *  转移方程：dp[i] = Math.max(sum - dp[i+1], sum - dp[i+2], sum - dp[i+3]);
     *
     */
    public String stoneGameIII2(int[] stoneValue) {
        int n = stoneValue.length;
        if(n == 0) return "Tie";
        int[] dp = new int[n+3];
        int sum = 0;
        for(int i = n-1 ; i >= 0 ; i --) {
            sum += stoneValue[i];
            dp[i] = Math.max(sum - dp[i+1], Math.max(sum - dp[i+2], sum - dp[i+3]));
        }
        if(2 * dp[0] > sum) return "Alice";
        if(2 * dp[0] < sum) return "Bob";
        return "Tie";
    }

}
