package bfs;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/5/11
 * @ Time: 7:37 下午
 * @ Project: Algorithm-Java-implements
 */
public class FindKthSmallestSumMatrixWithSortedRows {

    /**
     *
     * 给你一个 m * n 的矩阵 mat，以及一个整数 k ，矩阵中的每一行都以非递减的顺序排列。
     *
     * 你可以从每一行中选出 1 个元素形成一个数组。返回所有可能数组中的第 k 个 最小 数组和。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：mat = [[1,3,11],[2,4,6]], k = 5
     * 输出：7
     * 解释：从每一行中选出一个元素，前 k 个和最小的数组分别是：
     * [1,2], [1,4], [3,2], [3,4], [1,6]。其中第 5 个的和是 7 。
     * 示例 2：
     *
     * 输入：mat = [[1,3,11],[2,4,6]], k = 9
     * 输出：17
     * 示例 3：
     *
     * 输入：mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
     * 输出：9
     * 解释：从每一行中选出一个元素，前 k 个和最小的数组分别是：
     * [1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]。其中第 7 个的和是 9 。
     * 示例 4：
     *
     * 输入：mat = [[1,1,10],[2,2,9]], k = 7
     * 输出：12
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */

    /**
     * BFS 的模板题了
     */

    static class Node {
        public int sum;
        public int[] pos;

        public Node(int[] prevs, int[][] mat, int n, int m) {
            this.pos = new int[n];
            for(int i = 0 ; i < n ; i ++) {
                this.pos[i] = prevs[i];
                this.sum += mat[i][prevs[i]];
            }
        }
        public boolean select(int p, int[][] mat, int m) {
            if(this.pos[p] == m - 1) return false;
            this.sum -= mat[p][this.pos[p]];
            this.pos[p] += 1;
            this.sum += mat[p][this.pos[p]];
            return true;
        }
        public String toString() {
            String res = "";
            for(int i = 0 ; i < this.pos.length ; i ++) {
                res += String.valueOf(this.pos[i]);
            }
            return res;
        }
    }
    public int kthSmallest(int[][] mat, int k) {
        int n = mat.length;
        if(n == 0) return -1;
        int m = mat[0].length;
        int[] pos = new int[n];
        Set<String> visited = new HashSet<>();
        //优先队列本身类似一个最大堆，实现自定义排序器时，n2.sum - n1.sum, n2值大，权重小
        //所以我们要 n1.sum - n2.sum，n1值大，权重大， 递增排序
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> {return n1.sum - n2.sum;});
        Node root = new Node(pos, mat, n, m);
        queue.offer(root);
        visited.add(root.toString());
        int cnt = 1;
        int sum = root.sum;
        while(!queue.isEmpty() && cnt <= k) {
            Node curNode = queue.poll();
            for(int i = 0 ; i < n ; i ++) {
                Node next = new Node(curNode.pos, mat, n, m);
                if(!next.select(i, mat, m)) continue;
                String str = next.toString();
                if(visited.contains(str)) continue;
                queue.offer(next);
                visited.add(next.toString());
            }
            cnt += 1;
            sum = curNode.sum;
        }
        return sum;
    }
}
