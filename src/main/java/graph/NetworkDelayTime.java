package graph;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/4/27
 * @ Time: 3:17 下午
 * @ Project: Algorithm-Java-implements
 */
public class NetworkDelayTime {


    /**
     *
     * 有 N 个网络节点，标记为 1 到 N。
     *
     * 给定一个列表 times，表示信号经过有向边的传递时间。 times[i] = (u, v, w)，其中 u 是源节点，v 是目标节点， w 是一个信号从源节点传递到目标节点的时间。
     *
     * 现在，我们从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1。
     *
     *  
     *
     * 示例：
     *
     *
     *
     * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
     * 输出：2
     *  
     *
     * 注意:
     *
     * N 的范围在 [1, 100] 之间。
     * K 的范围在 [1, N] 之间。
     * times 的长度在 [1, 6000] 之间。
     * 所有的边 times[i] = (u, v, w) 都有 1 <= u, v <= N 且 0 <= w <= 100。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/network-delay-time
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */

    /**
     *
     * dijkstra 算法模板
     *
     */

    int[] len;      // 最短路径
    int[][] adji;   // 邻接矩阵
    boolean[] visited;
    public int networkDelayTime(int[][] times, int N, int K) {
        int n = times.length;
        len = new int[N+1];
        adji = new int[N+1][N+1];
        visited = new boolean[N+1];
        int v0 = 0;
        for(int i = 1 ; i <= N ; i ++) {
            for(int j = 1 ; j <= N ; j ++) {
                adji[i][j] = Integer.MAX_VALUE;
            }
            len[i] = Integer.MAX_VALUE;
        }
        for(int i =0 ; i < n ; i++) {
            int u = times[i][0];
            int v = times[i][1];
            int w = times[i][2];
            adji[u][v] = w;
            if(u == K) len[v] = w;
        }
        len[K] = 0; visited[K] = true;
        for(int i = 1 ; i < N ; i ++) {
            int min = Integer.MAX_VALUE;
            for(int j = 1 ; j <= N ; j ++) {
                if(!visited[j] && len[j] < min) {
                    min = len[j];
                    v0 = j;
                }
            }
            visited[v0] = true;
            for(int j = 1 ; j <= N ; j ++) {
                if(!visited[j]) {
                    if(len[j] - min > adji[v0][j]) {
                        len[j] = min+adji[v0][j];
                        adji[K][j] = len[j];
                    }
                }
            }
        }
        int max = -1;
        for(int i = 1 ; i <= N ; i ++) {
            if(len[i] > max) {
                max = len[i];
                if(max == Integer.MAX_VALUE) return -1;
            }
        }
        return max;
    }

    /**
     * 单源最短路径算法的其他变种：
     *  优先队列/堆 优化的dijkstra
     *  Bellman-ford（解决负权边）
     *  SFPA
     *  floyd
     *
     */


    /**
     * bellman-ford 算法原理：https://blog.csdn.net/yuewenyao/article/details/81026278
     *
     * bellman-ford 算法实现：
     *
     *  核心是两层循环
     *
     */

    public int networkDelayTime2(int[][] times, int N, int K) {
        int n = times.length;
        int[] len = new int[N+1];      // 最短路径
        for(int i = 1 ; i <= N ; i ++) {
            len[i] = Integer.MAX_VALUE;
        }
        len[K] = 0;
        for(int i = 1 ;  i < N ; i ++) {
            for(int j = 0 ; j < n ; j ++) {
                int u = times[j][0];
                int v = times[j][1];
                int w = times[j][2];
                if(len[v] - w > len[u]) {
                    len[v] = w + len[u];
                }
            }
        }
        int max = -1;
        for(int i = 1 ; i <= N ; i ++) {
            if(len[i] > max) {
                max = len[i];
            }
        }
        if(max == Integer.MAX_VALUE) return -1;
        return max;
    }


}
