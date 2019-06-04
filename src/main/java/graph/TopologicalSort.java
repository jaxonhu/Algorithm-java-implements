package graph;

import backtracking.CourseSchedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/18
 * @Time: 下午2:00
 * @Project: Algorithm-Java-implements
 */
public class TopologicalSort {


    /**
     * leetcode207 拓扑排序  210
     *
     * 题意：There are a total of n courses you have to take, labeled from 0 to n-1.
     *
     * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
     *
     * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
     *
     * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
     *
     * 第一题的变形
     *
     * 其核心思想与非递归DFS比较类似
     *
     */


    public int[] findOrder(int numCourses, int[][] courses) {
        //这道题的关键是在于作图，把先决课程作为parent node，把上完先决课程之后的课作为child node加在parent node，同时我们需要保留parent node index的信息，因此我们可以想到用 array of list这个结构
        List<Integer>[] graph = new List[numCourses];
        for(int i = 0; i < numCourses; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] course : courses){
            graph[course[1]].add(course[0]);
        }
        //接下来使用dfs对整个图进行遍历，遍历的条件是只要找到任何一个先决课程能够完成所有的课程，如果存在环或者graph断链的情况，则不能完成
        //1 我们首先写用有环的dfs来进行判断
        int[] visited = new int[numCourses];
        Stack<Integer> stack = new Stack<>();
        //0 还未做任何处理  1 正在遍历 2 已经遍历过了
        for(int i = 0; i < numCourses; i++){
            if(dfs(graph, visited, i, stack)){//如果有环 那么从该点出发的路径没办法遍历所有的course
                return new int[0];
            }
        }
        int i = 0;
        int[] result = new int[numCourses];
        while (!stack.isEmpty()) {
            result[i++] = stack.pop();
        }
        return result;
    }

    public boolean dfs(List<Integer>[] graph, int[] visited, int course, Stack<Integer> stack){
        if(visited[course] == 1) return true;//如果我们访问到了一个正在遍历的点，说明我们再次访问到这个节点，有环，返回true
        if(visited[course] == 2) return false;//如果我们访问到了一个已经完成遍历的点，我们直接返回false，表明在该次行动下还不存在环，因为我们的最终目标是遍历所有course
        visited[course] = 1;
        for(int nei : graph[course]){
            if(dfs(graph, visited, nei, stack)){
                return true;
            }
        }
        visited[course] = 2;
        stack.push(course);
        return false;
    }


    public static void main(String[] args) {
        int[][] test = {{0,1},{2,3},{1,2}};
        int[][] test2 = {{1,0}, {2,0}};
        int[][] test3 = {{0,1}};
        TopologicalSort ts = new TopologicalSort();
        int[] res = ts.findOrder(2, test3);
        for( int i : res) {
            System.out.println(i);
        }
    }
}
