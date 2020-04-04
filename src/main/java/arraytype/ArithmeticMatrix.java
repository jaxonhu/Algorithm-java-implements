package arraytype;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/4/4
 * @ Time: 11:23 上午
 * @ Project: Algorithm-Java-implements
 */
public class ArithmeticMatrix {


    /**
     *
     * 等差数列
     *
     * 题目：给定一个N行M列的矩阵，矩阵每行、每列都是等差数列，所有缺失值被标记为0，
     * 现在访问矩阵的N个位置(i,j)，如果该位置值能给出，输出该值，否则输出“Unknown"。
     *
     * 输入示例：
     * 2 3 6
     * 1 0 3
     * 0 0 0
     * 1 1
     * 1 2
     * 1 3
     * 2 1
     * 2 2
     * 2 3
     *
     */

    static int[][] A;
    static int[] cols;
    static boolean[] colsOK;
    static int[] rows;
    static boolean[] rowsOK;
    static int m, n;
    static void fill(int i, int j, int value) {
        if(colsOK[j] && rowsOK[i]) {
            return;
        }
        A[i][j] = value;
        if(!colsOK[j]) {
            if(cols[j] == -1 || cols[j] == i) {
                cols[j] = i;
            } else {
                colsOK[j] = true;
                int delta = (value - A[cols[j]][j]) / (i - cols[j]);
                for(int k = i-1 ; k >= 0 ; k --) {
                    fill(k, j, value - (i-k) * delta);
                }
                for(int k = i+1 ; k < m ; k ++) {
                    fill(k, j, value + (k-i) * delta);
                }
            }
        }
        if(!rowsOK[i]) {
            if(rows[i] == -1 || rows[i] == j) {
                rows[i] = j;
            } else {
                rowsOK[i] = true;
                int delta = (value - A[i][rows[i]]) / (j - rows[i]);
                for(int k = j-1 ; k >= 0 ; k --) {
                    fill(i, k, value - (j-k) * delta);
                }
                for(int k = j+1 ; k < n ; k ++) {
                    fill(i, k,value + (k-j) * delta);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int q;
        m = scanner.nextInt();
        n = scanner.nextInt();
        q = scanner.nextInt();
        A = new int[m][n];
        cols = new int[n];
        Arrays.fill(cols, -1);
        rows = new int[m];
        Arrays.fill(rows, -1);
        colsOK = new boolean[n];
        rowsOK = new boolean[m];

        for(int i = 0 ;  i < m ; i ++) {
            for(int j =  0 ; j < n ; j ++) {
                int value = scanner.nextInt();
                if(value != 0) {
                    fill(i, j, value);
                }
            }
        }

        for(int k = 0 ; k < q ; k ++) {
            int i = scanner.nextInt()-1;
            int j = scanner.nextInt()-1;
            if(A[i][j] != 0) {
                System.out.println(A[i][j]);
            } else {
                System.out.println("Unknown");
            }
        }
    }

}
