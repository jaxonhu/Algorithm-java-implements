package graph;

import java.util.Scanner;

public class FullMultipartGraph2 {
        public static void main(String args[])
        {
            Scanner sc = new Scanner(System.in);
            int N = sc.nextInt();
            while(N-- > 0)
            {
                int nodenum = sc.nextInt();
                int sidenum = sc.nextInt();
                boolean nodes[][] = new boolean[nodenum][nodenum];
                while(sidenum-- > 0)
                {
                    int i = sc.nextInt() - 1;
                    int j = sc.nextInt() - 1;
                    nodes[i][j] = true;
                    nodes[j][i] = true;
                }
                int result[] = new int[nodenum];
                for(int i = 0; i < nodenum; i++)
                {
                    for(int j = 0; j < nodenum; j++)
                    {
                        if(nodes[i][j]) result[i]++;
                    }
                }
                boolean flag = true;
                for(int i = 0; i < nodenum; i++)
                {
                    for(int j = i; j < nodenum; j++)
                    {
                        if(!nodes[i][j])
                        {
                            if(result[i] != result[j])
                            {
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(!flag) break;
                }
                if(!flag)
                {
                    System.out.println("No");
                }
                else
                {
                    System.out.println("Yes");
                }
            }
        }
}
