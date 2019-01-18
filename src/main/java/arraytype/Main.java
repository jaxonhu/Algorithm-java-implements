package arraytype;

// 本题为考试多行输入输出规范示例，无需提交，不计分。
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    class Node implements Comparable<Node>{
        int value;
        int index;

        @Override
        public int compareTo(Node o) {
            return this.value > o.value ? 1 : -1;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        Node[] nodes = new Node[n];
        for(int i = 0 ; i < n ; ++ i) {
            nodes[i].value = sc.nextInt();
            nodes[i].index = i+1;
        }
        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.value == o2.value)
                    return 0;
                return o1.value > o2.value ? 1 : -1;
            }
        });


    }
}
