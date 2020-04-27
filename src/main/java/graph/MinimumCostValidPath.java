package graph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/4/25
 * @ Time: 4:11 下午
 * @ Project: Algorithm-Java-implements
 */
public class MinimumCostValidPath {


    /**
     *
     * 给你一个 m x n 的网格图 grid 。 grid 中每个格子都有一个数字，对应着从该格子出发下一步走的方向。 grid[i][j] 中的数字可能为以下几种情况：
     *
     * 1 ，下一步往右走，也就是你会从 grid[i][j] 走到 grid[i][j + 1]
     * 2 ，下一步往左走，也就是你会从 grid[i][j] 走到 grid[i][j - 1]
     * 3 ，下一步往下走，也就是你会从 grid[i][j] 走到 grid[i + 1][j]
     * 4 ，下一步往上走，也就是你会从 grid[i][j] 走到 grid[i - 1][j]
     * 注意网格图中可能会有 无效数字 ，因为它们可能指向 grid 以外的区域。
     *
     * 一开始，你会从最左上角的格子 (0,0) 出发。我们定义一条 有效路径 为从格子 (0,0) 出发，每一步都顺着数字对应方向走，最终在最右下角的格子 (m - 1, n - 1) 结束的路径。有效路径 不需要是最短路径 。
     *
     * 你可以花费 cost = 1 的代价修改一个格子中的数字，但每个格子中的数字 只能修改一次 。
     *
     * 请你返回让网格图至少有一条有效路径的最小代价。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */


    /**
     *
     * 题解： BFS解法
     *  题目相当于求解从(0,0)到(n-1,m-1)的一条最短路径，为什么这么转化呢，因为如果把题目中箭头当成连接线的话，这个图可能是非完全连通的
     *  但是如果把 箭头当做权重 的话，这就是一个完全连通图，就可以用最短路径算法。
     *  如果A ——> B，则AB之间边的权重为0， 否则为1
     *
     */

    class XY {
        public int x;
        public int y;
        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    int[] dx = new int[] {0, 1, -1, 0, 0};
    int[] dy = new int[] {0, 0, 0, 1, -1};
    public int minCost(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] cost = new int[n][m];
        Queue<XY> queue = new LinkedList<>();
        queue.offer(new XY(0, 0));
        for(int i = 0 ; i < n ; i ++) {
            for(int j = 0 ; j < m ; j++)
                cost[i][j] = Integer.MAX_VALUE;
        }
        cost[0][0] = 0;
        while(queue.size() > 0) {
            XY xy = queue.poll();
            int x = xy.x; int y = xy.y;
            for(int i = 1 ; i <= 4 ; i ++) {
                int nx = x + dx[i]; int ny = y + dy[i];
                if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                int ncost = cost[y][x] + (grid[y][x] == i ? 0:1);
                if(ncost < cost[ny][nx]) {
                    cost[ny][nx] = ncost;
                    queue.offer(new XY(nx, ny));
                }
            }
        }
        return cost[n-1][m-1];
    }


    /**
     * Dijkstra 算法
     *
     *Dijkstra 算法是求取单源最短路径的常用算法，其基本思想是每次用当前未拓展且具有最小权值的点来更新源点到其余顶点的距离。
     * Dijkstra 算法的时间复杂度包含两个部分：找到最小节点并将其移除的用时 以及 更新某一节点权值的用时
     *
     * 由于 Fibonacci 堆实现较为复杂，各语言标准库未提供实现（C++ 的 Boost 库实现了这一数据结构），
     * 并且其实际运行效率与优先队列相比的优势并不明显，所以基于优先队列的实现是 Dijkstra 算法最常见的实现方式。
     *
     * 需要注意的是，Dijkstra 算法在图中存在负环的情况下不适用！对于无向图来说，只要有一条负边，就构成了一个两节点的负环，
     * 所以在无向图中只要有负边就不能使用 Dijkstra 算法。
     *
     * dijkstra算法应该算一种贪心算法，和BFS的区别在于，dijkstra算法用优先队列保证每次更新代价最小的节点，所以访问过的节点
     * 不需要再次入队。
     *
     *
     */

    class Node {
        public int weight;
        public int xy;
        public Node(int weight, int xy) {
            this.weight = weight;
            this.xy = xy;
        }
    }

    public int minCost2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] cost = new int[n][m];
        boolean[] visited = new boolean[n*m];
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> n1.weight - n2.weight);
        queue.offer(new Node(0, 0));
        for(int i = 0 ; i < n ; i ++) {
            for(int j = 0 ; j < m ; j++)
                cost[i][j] = Integer.MAX_VALUE;
        }
        cost[0][0] = 0;
        while(queue.size() > 0) {
            Node node = queue.poll();
            if(visited[node.xy]) continue;
            visited[node.xy] = true;
            int x = node.xy % m; int y = node.xy / m;
            for(int i = 1 ; i <= 4 ; i ++) {
                int nx = x + dx[i]; int ny = y + dy[i];
                if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                int ncost = cost[y][x] + (grid[y][x] == i ? 0:1);
                if(ncost < cost[ny][nx]) {
                    cost[ny][nx] = ncost;
                    queue.offer(new Node(cost[ny][nx], nx + ny * m));
                }
            }
        }
        return cost[n-1][m-1];
    }




    /**
     *
     * SFPA 算法
     *
     * 如果一个节点已经在队列中，其实就没有必要将其再次入队了。这是 SPFA 算法的基本思想。可以看到，与上面的BFS 方法相比，就是增加了一个 in 数组来判断当前节点是否已经在队列中。
     *
     * SPFA 算法是一个十分依赖于数据的算法。在特定的数据下，SPFA 会退化为 Bellman-Ford，时间复杂度为 O(V\cdot E)O(V⋅E)。一般的编程竞赛中，涉及到最短路径的题目，都会有专门卡SPFA的数据，所以一般情况下还是使用 Dijkstra 算法。本题的测试数据相对较弱，BFS 和 SPFA 都可以顺利通过，甚至 SPFA 的运行时间还要长于 BFS（修改 in 数组状态带来了额外的开销）。
     *
     * SPFA 的好处是可以判断负环。我们可以用一个数组记录每个顶点的入队次数，如果有顶点的入队次数超过了 VV 次，则代表图中存在负环。
     *
     *
     */

    public int minCost3(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] cost = new int[n][m];
        boolean[][] visited = new boolean[n][m]; // SFPA 增加一个visited数组判断是否已经存在于队列
        Queue<XY> queue = new LinkedList<>();
        queue.offer(new XY(0, 0));
        visited[0][0] = true;
        for(int i = 0 ; i < n ; i ++) {
            for(int j = 0 ; j < m ; j++)
                cost[i][j] = Integer.MAX_VALUE;
        }
        cost[0][0] = 0;
        while(queue.size() > 0) {
            XY xy = queue.poll();
            int x = xy.x; int y = xy.y;
            visited[y][x] = false;
            for(int i = 1 ; i <= 4 ; i ++) {
                int nx = x + dx[i]; int ny = y + dy[i];
                if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                int ncost = cost[y][x] + (grid[y][x] == i ? 0:1);
                if(ncost < cost[ny][nx]) {
                    cost[ny][nx] = ncost;
                    if(!visited[ny][nx]) {
                        queue.offer(new XY(nx, ny));
                        visited[ny][nx] = true;
                    }
                }
            }
        }
        return cost[n-1][m-1];
    }

}
