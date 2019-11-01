package line;

import java.util.*;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2019/10/31
 * @ Time: 11:09 PM
 * @ Project: Algorithm-Java-implements
 */
public class Skyline {


    /**
     *
     * 如果按照矩形来处理将会非常麻烦，可以把这些矩形拆成两个点，一个左上顶点，一个右上顶点，这个题相当于处理2*n个边的问题，
     * 每一个边有一个x-axis和一个height，把所给的triplet转换成[x-axis, height]，对这些顶点按x-axis排序。然后开始遍历
     * 这些点，用一个最大堆来记录高度，对于左顶点，将height加入堆中。对于右顶点，从堆中移出height，同时也意味这这个矩形的
     * 结束。堆顶是所有顶点中最高的点，是当前图形的最高位置。只要这个点没被移出堆，说明这个最高的矩形还没结束。如果堆顶高度
     * 值出现了变化，说明出现了拐点，记录相应位置到结果中。
     */

    class Node{

        int x;
        int height;
        String type;

        public Node(int x, int height, String type) {
            this.x = x;
            this.height = height;
            this.type = type;
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int[] building : buildings) {
            nodes.add(new Node(building[0], building[2], "left"));
            nodes.add(new Node(building[1], building[2], "right"));
        }
        nodes.sort((o1, o2) -> {
            if(o1.x != o2.x) {
                return o1.x - o2.x;
            }
            if(o1.type.equals("left") && o2.type.equals("left"))
                return o2.height - o1.height;
            else if(o1.type.equals("right") && o2.type.equals("right"))
                return o1.height - o2.height;
            else
                return o1.type.equals("left")?-1:1;
        });
        int size = nodes.size();
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> {
            if(o1 > o2)
                return -1;
            return 1;
        });
        heap.add(0);
        List<List<Integer>> results = new ArrayList<>();
        for(int i = 0 ; i < size ; i ++) {
            Node n = nodes.get(i);
            int prev = heap.peek();
            int cur;
            int height;
            if(n.type.equals("left")) {
                heap.add(n.height);
                height = cur = heap.peek();
            } else {
                heap.remove(n.height);
                height = cur = heap.peek();
            }
            if(cur != prev) {
                List<Integer> point = new ArrayList<>();
                point.add(n.x);point.add(height);
                results.add(point);
            }}
        return results;
    }

    public static void main(String[] args) {

//        int[][] buildings = {
//                {2, 9 ,10},
//                {3, 7, 15},
//                {5, 12, 12},
//                {15, 20, 10},
//                {19, 24, 8}
//        };

        int[][] buildings = {
                {0, 2 ,3},
                {2, 5, 3}
        };

//        int[][] buildings = {
//                {1,2,1},
//                {1,2,2},
//                {1,2,3}
//        };

        Skyline skyline = new Skyline();
        List<List<Integer>> result = skyline.getSkyline(buildings);
        System.out.println(Arrays.toString(result.toArray()));
    }

}
