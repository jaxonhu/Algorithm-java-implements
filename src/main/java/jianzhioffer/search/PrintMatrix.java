package jianzhioffer.search;

import java.util.ArrayList;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/8/1
 * @Time: 下午9:59
 * @Project: Algorithm-Java-implements
 */
public class PrintMatrix {

    /**
     *输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，\
     * 例如，如果输入如下4 X 4矩阵： 1 2 3 4
     *                            5 6 7 8
     *                            9 10 11 12
     *                            13 14 15 16
     * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
     */

    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> l = new ArrayList<>();
        int n = matrix.length;
        if(n == 0) return l;
        int m = matrix[0].length;
        int left = 0, right = m - 1;
        int low = 0, high = n - 1;
        while(left <= right && low <= high) {

            for(int i = left ; i <= right ; ++ i) {
                l.add(matrix[low][i]);
            }
            low ++;
            if(high < low) break;
            for(int i = low ; i <= high ; ++ i) {
                l.add(matrix[i][right]);
            }
            right --;
            if(right < left) break;
            for(int i = right ; i >= left ;  -- i) {
                l.add(matrix[high][i]);
            }
            high --;
            if(high < low) break;
            for(int i = high ; i>= low ; -- i) {
                l.add(matrix[i][left]);
            }
            left ++;

        }
        return l;
    }
}
