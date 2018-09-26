package backtracking;

import java.util.*;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/8/11
 * @Time: 下午12:12
 * @Project: Algorithm-Java-implements
 */
public class WordLadder {

    /**
     * WordLadder I : 返回次数即可
     * 思路：宽度优先遍历，保存一个字典集合，一个起始集合，一个结束集合，一个访问集合
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> dict = new HashSet<>();
        dict.addAll(wordList);
        if(!dict.contains(endWord))
            return -1;
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> visited = new HashSet<>();
        int res = 1;
        beginSet.add(beginWord);
        visited.add(beginWord);
        endSet.add(endWord);

        while(!beginSet.isEmpty()) {
            Set<String> temp = new HashSet<>();
            res += 1;
            for(String strBegin : beginSet) {
                char[] strBeginChars = strBegin.toCharArray();
                for(int i = 0 ; i < strBeginChars.length ; ++ i) {
                    char old = strBeginChars[i];
                    for(char c = 'a' ; c <= 'z' ; ++ c) {
                        strBeginChars[i] = c;
                        String str = String.valueOf(strBeginChars);
                        if(!dict.contains(str)) continue;
                        if(endSet.contains(str)) return res;
                        visited.add(str);
                        temp.add(str);
                    }
                    strBeginChars[i] = old;
                }
            }
            beginSet = temp;
            if(endSet.size() < beginSet.size()) {
                Set<String> tmp = beginSet;
                beginSet = endSet;
                endSet = tmp;
            }
        }
        return 0;
    }


    /**
     * WordLadder II : 返回序列，用回溯的方法
     */

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        HashSet<String> visited = new HashSet<>();
        HashMap<String, List<String>> neighbors = new HashMap<>(); // curString的neighbors
        HashMap<String, Integer> distances = new HashMap<>(); // curString 到 startString的距离
        ArrayList<String> solution = new ArrayList<>();

        dict.add(beginWord);
        bfs(beginWord, endWord, visited, neighbors, distances, dict);
        dfs(beginWord, endWord, result, solution, neighbors, distances);
        return result;
    }

    public void bfs(String beginWord, String endWord, HashSet<String> visited, HashMap<String, List<String>> neighbors
                    , HashMap<String, Integer> distances, Set<String> dict) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        distances.put(beginWord, 0);
        while(!queue.isEmpty()) {
            boolean flag = false;
            int n = queue.size();
            for (int i = 0; i < n; ++i) {
                String curStr = queue.poll();
                List<String> neighbor = new ArrayList<>();
                for (String nextStr : getNeighbors(curStr, dict)) {
                    if (visited.contains(nextStr))
                        continue;
                    neighbor.add(nextStr);
                    queue.offer(nextStr);
                    distances.put(nextStr, distances.get(curStr) + 1);
                    if (nextStr == endWord)
                        flag = true;
                }
                neighbors.put(curStr, neighbor);
            }
            if (flag)
                break;
        }
    }

    public List<String> getNeighbors(String curWord, Set<String> dict) {
        List<String> res = new ArrayList<>();
        char[] curWordChars = curWord.toCharArray();
        for(int i = 0 ; i < curWordChars.length ; ++ i) {
            char old = curWordChars[i];
            for(char c = 'a' ; c <= 'z' ; ++ c) {
                curWordChars[i] = c;
                String curStr = String.valueOf(curWordChars);
                if(dict.contains(curStr)){
                    res.add(curStr);
                }
            }
        }
        return res;
    }

    public void dfs(String curWord, String endWord, List<List<String>> result, List<String> solution
                    , HashMap<String, List<String>> neighbors, HashMap<String, Integer> distance) {
        solution.add(curWord);
        if(curWord.equals(endWord)) {
            result.add(new ArrayList<>(solution));
            return;
        }
        for(String nextWord : neighbors.get(curWord)) {
            if(distance.get(nextWord) == distance.get(curWord) + 1)
                dfs(nextWord, endWord, result, solution, neighbors, distance);
        }
        solution.remove(curWord);
    }
}
