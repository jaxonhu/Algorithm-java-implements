package graph;

import java.util.*;

public class FullMultipartGraph {

    /**
     * 完全多分图
     */


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        for(int i = 0 ; i < n ; ++ i) {

            int nodes = in.nextInt();
            int lines = in.nextInt();

            HashMap<Integer, List<Integer>> graph = new HashMap<>();

            for(int j = 0; j < lines ; ++j) {
                int from = in.nextInt();
                int to = in.nextInt();
                if(graph.get(from) != null) {
                    List<Integer> l = graph.get(from);
                    l.add(to);
                    graph.put(from, l);
                }else {
                    graph.put(from, new ArrayList<>(to));
                }

                // coloring
                // color : node
                HashMap<Integer, List<Integer>> color2node = new HashMap<>();
                int[] node2color = new int[nodes+1];
                for(int colors = 2 ; colors < nodes ; colors++) {
                    List<Integer> remains = new ArrayList<>();
                    for(int k = 1 ; k <= colors ; ++ k) {
                        remains.add(k);
                    }

                    Iterator<Map.Entry<Integer, List<Integer>>> iter = graph.entrySet().iterator();
                    while(iter.hasNext()) {
                        Map.Entry<Integer, List<Integer>> entry = iter.next();
                        int f = entry.getKey();
                        // uncolor
                        if(node2color[f] == 0) {
                            int color = remains.remove(remains.size()-1);
                            node2color[f] = color;
                            List<Integer> toNode = null;
                            if(color2node.get(f) != null) {

                            }
                        }
                        List<Integer> tos = entry.getValue();
                        for(Integer t : tos) {
                            // uncolor
                            if(node2color[t] == 0) {
                                int color = remains.remove(remains.size()-1);
                                node2color[t] = color;
                            }
                        }
                    }
                }

                // verify



            }




        }


    }
}
