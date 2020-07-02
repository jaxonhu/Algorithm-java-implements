package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/7/2
 * @ Time: 10:52 下午
 * @ Project: Algorithm-Java-implements
 */
public class CourseScheduleIV {

    /**
     *
     * 你总共需要上 n 门课，课程编号依次为 0 到 n-1 。
     *
     * 有的课会有直接的先修课程，比如如果想上课程 0 ，你必须先上课程 1 ，那么会以 [1,0] 数对的形式给出先修课程数对。
     *
     * 给你课程总数 n 和一个直接先修课程数对列表 prerequisite 和一个查询对列表 queries 。
     *
     * 对于每个查询对 queries[i] ，请判断 queries[i][0] 是否是 queries[i][1] 的先修课程。
     *
     * 请返回一个布尔值列表，列表中每个元素依次分别对应 queries 每个查询对的判断结果。
     *
     * 注意：如果课程 a 是课程 b 的先修课程且课程 b 是课程 c 的先修课程，那么课程 a 也是课程 c 的先修课程。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/course-schedule-iv
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */

    // 思路就是先构造邻接矩阵，然后再BFS搜索
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        int[][] adji = new int[n][n];
        List<Boolean> res = new ArrayList<>();
        for(int i = 0 ; i < prerequisites.length ; i ++) {
            int u = prerequisites[i][0];
            int v = prerequisites[i][1];
            adji[u][v] = 1;
        }
        for(int i = 0 ; i < queries.length ; i ++) {
            res.add(findPath(adji, queries[i][0], queries[i][1], n));
        }
        return res;
    }

    boolean findPath(int[][] adji, int from, int target, int n) {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(from);
        visited[from] = true;
        while(!queue.isEmpty()) {
            int len = queue.size();
            for(int i = 0 ; i < len ; i ++) {
                int cur = queue.poll();
                if(cur == target) return true;
                for(int j = 0 ; j < n ; j ++) {
                    if(adji[cur][j] == 1 && !visited[j]) {
                        queue.offer(j);
                        visited[j] = true;
                    }
                }
            }
        }
        return false;
    }
}
