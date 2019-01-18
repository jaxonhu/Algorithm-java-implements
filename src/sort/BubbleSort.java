package sort;

import java.util.Arrays;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/12/14
 * @Time: 5:21 PM
 * @Project: Algorithm-Java-implements
 */
public class BubbleSort {

    /**
     * 冒泡排序，看自己有没有忘记，哈哈
     */

    static void bubbleSort(int[] a) {
        int n = a.length;
        if(n == 0)
            return;
        for(int i = 0 ; i < n ; i ++) {
            for(int j = 0 ; j < n - i - 1 ; j ++) {
                if(a[j] > a[j+1])
                    swap(a, j, j+1);
            }
        }
    }

    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {123,345,2,4,5,7,21};
        bubbleSort(a);
        System.out.println(Arrays.toString(a));
    }
}
