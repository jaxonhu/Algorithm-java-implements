package bfs;

import org.junit.Test;

import java.util.*;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/5/24
 * @ Time: 12:07 上午
 * @ Project: Algorithm-Java-implements
 */
public class OpenTheLock {


    public int openLock(String[] deadends, String target) {
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add("0000");
        int step = 0;
        while(!queue.isEmpty()) {
            int len = queue.size();
            for(int i = 0 ; i < len ; i ++) {
                String cur = queue.poll();
                if(visited.contains(cur)) continue;
                if(deads.contains(cur)) continue;
                if(cur.equals(target)) return step;
                for(int j = 0 ;  j < cur.length() ; j ++) {
                    char ch = cur.charAt(j);
                    char next = ch == '9' ? '0' : (char)(ch + 1);
                    char prev = ch == '0' ? '9' : (char)(ch - 1);
                    String nextStr = cur.substring(0,j) + next + cur.substring(j+1);
                    String prevStr = cur.substring(0,j) + prev + cur.substring(j+1);
                    if(!visited.contains(nextStr))
                        queue.offer(nextStr);
                    if(!visited.contains(prevStr))
                        queue.offer(prevStr);
                }
                visited.add(cur);
            }
            step ++;
        }
        return -1;
    }


    public int openLock2(String[] deadends, String target) {
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        Queue<String> queue = new LinkedList<>();
        Queue<String> queue2 = new LinkedList();
        Set<String> visited = new HashSet<>();
        queue.add("0000");
        queue2.add(target);
        int step = 0;
        while(!queue.isEmpty() && !queue2.isEmpty()) {
            Queue<String> temp = new LinkedList<>();
             if(queue.size() > queue2.size()) {
                 temp = queue2;
                 queue2 = queue;
                 queue = temp;
                 temp = new LinkedList<>();
             }
            while(!queue.isEmpty()) {
                String cur = queue.poll();
                if(visited.contains(cur)) continue;
                if(deads.contains(cur)) continue;
                if(queue2.contains(cur)) return step;
                visited.add(cur);
                for(int j = 0 ;  j < cur.length() ; j ++) {
                    char ch = cur.charAt(j);
                    char next = ch == '9' ? '0' : (char)(ch + 1);
                    char prev = ch == '0' ? '9' : (char)(ch - 1);
                    String nextStr = cur.substring(0,j) + next + cur.substring(j+1);
                    String prevStr = cur.substring(0,j) + prev + cur.substring(j+1);
                    if(!visited.contains(nextStr))
                        temp.offer(nextStr);
                    if(!visited.contains(prevStr))
                        temp.offer(prevStr);
                }
            }
            step ++;
            queue = queue2;
            queue2 = temp;
        }
        return -1;
    }

    @Test
    public void test() {
        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        System.out.println(openLock(deadends, target));
    }

    @Test
    public void test2() {
        String[] deadends = {"8888"};
        String target = "0009";
        System.out.println(openLock(deadends, target));
    }

    @Test
    public void test3() {
        String[] deadends = {"2110","0202","1222","2221","1010"};
        String target = "2010";
        System.out.println(openLock2(deadends, target));
    }

    @Test
    public void test4() {
        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        System.out.println(openLock2(deadends, target));
    }
}
