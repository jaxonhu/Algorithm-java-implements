package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/3/16
 * @ Time: 9:41 AM
 * @ Project: Algorithm-Java-implements
 */
public class MinimumHeightTrees {


    /**
     *
     * For an undirected graph with tree characteristics, we can choose any node as the root.
     * The result graph is then a rooted tree. Among all possible rooted trees,
     * those with minimum height are called minimum height trees (MHTs). Given such a graph,
     * write a function to find all the MHTs and return a list of their root labels.
     *
     * Format
     * The graph contains n nodes which are labeled from 0 to n - 1.
     * You will be given the number n and a list of undirected edges (each edge is a pair of labels).
     *
     * You can assume that no duplicate edges will appear in edges.
     * Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
     *
     * Example 1 :
     *
     * Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]
     *
     *         0
     *         |
     *         1
     *        / \
     *       2   3
     *
     * Output: [1]
     *
     *
     * @param n
     * @param edges
     * @return
     */

    // breadth-first-search
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        int[] degree = new int[n];
        List<Integer> res = new ArrayList<Integer>();
        List<List<Integer>> graph = new ArrayList<List<Integer>>();
        Queue<Integer> queue = new LinkedList<Integer>();
        if(n == 1) {
            res.add(0);
            return res;
        }
        for(int i = 0 ; i < n ; i ++) {
            graph.add(new ArrayList<Integer>());
        }
        int len = edges.length;
        for(int i = 0 ; i < len ; i ++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
            degree[edges[i][0]] ++;
            degree[edges[i][1]] ++;
        }
        for(int i = 0 ; i < n ; i ++) {
            if(degree[i] == 1)
                queue.offer(i);
        }
        while(!queue.isEmpty()) {
            res = new ArrayList<Integer>();
            int size = queue.size();
            for(int i = 0 ; i <size ; i ++) {
                int node = queue.poll();
                res.add(node);
                degree[node]--;
                for(int j = 0 ; j < graph.get(node).size() ; j ++) {
                    int to = graph.get(node).get(j);
                    degree[to] --;
                    if(degree[to] == 1) queue.offer(to);
                }
            }
        }
        return res;
    }

}
