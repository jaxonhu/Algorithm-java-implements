package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2019/6/2
 * @Time: 3:55 PM
 * @Project: Algorithm-Java-implements
 */
public class CourseSchedule {

    /**
     * 超时了
     * @param numCourses
     * @param prerequisites
     * @return
     */

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        int len = prerequisites.length;
        if(len == 1 || len == 0)
            return true;
        for(int i = 0 ; i < len ; i ++) {
            Set<Integer> path = new HashSet<Integer>();
            Set<Integer> visited = new HashSet<Integer>();
            visited.add(i);
            path.add(prerequisites[i][0]);
            path.add(prerequisites[i][1]);
            if(!recursive(prerequisites, visited, path, len, prerequisites[i][1], 1))
                return false;
        }
        return true;
    }

    public boolean recursive(int[][] prerequisites, Set<Integer> visited, Set<Integer> path, int len, int prev, int index) {
        if ( visited.size() == len)
            return true;
        for(int i = index ; i < len ; ++i) {
            if (visited.contains(i))
                continue;
            Set<Integer> copy_visited = new HashSet<>(visited);
            int[] current = prerequisites[i];
            int start = current[0];
            int end = current[1];
            if (prev == start) {
                Set<Integer> copy = new HashSet<>(path);
                copy.add(start);
                if (copy.contains(end))
                    return false;
                copy.add(end);
                copy_visited.add(i);
                System.out.println("visited:" + visited);
                System.out.println("path" + copy);
                return recursive(prerequisites, copy_visited, copy, len, end, 0);
            } else {
                return recursive(prerequisites, visited, path, len, prev, index + 1);
            }
        }
        return true;
    }


    /**
     * 第二种做法，用邻接表表示Graph。 其实就是拓扑排序
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish2 (int numCourses, int[][] prerequisites) {
        final List<Integer>[] graph = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < prerequisites.length; i++) {
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        return !hasCycle(graph, numCourses);
    }

    private boolean hasCycle(final List<Integer>[] graph, int numCourses) {
        final boolean[] visited = new boolean[numCourses];
        final boolean[] onStack = new boolean[numCourses];
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                if (hasCycle(i, graph, visited, onStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCycle(int v, final List<Integer>[] graph, final boolean[] visited, final boolean[] onStack) {
        visited[v] = true;
        onStack[v] = true;
        boolean hasCycle = false;
        for (int w : graph[v]) {
            if (!visited[w]) {
                hasCycle = hasCycle(w, graph, visited, onStack);
                if (hasCycle) {
                    break;
                }
            } else if (onStack[w]) {
                hasCycle = true;
                break;
            }
        }
        onStack[v] = false;
        return hasCycle;
    }

    public static void main(String[] args) {
        int[][] test = {{0,1},{2,3},{1,2},{3,0}};
        int[][] test2 = {{1,0}, {2,0}};
        CourseSchedule cs = new CourseSchedule();
        System.out.println(cs.canFinish(3, test2));
    }
}
