package sort;

import java.util.Arrays;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/6/19
 * @Time: 上午9:49
 * @Project: Algorithm-Java-implements
 */
public class MergeSort {

    /**
    *   归并排序的递归形式
    */
    public static void merge(int[] a, int first, int mid, int last, int[] temp) {
        int i = first, j = mid + 1;
        int m = mid, n = last;
        int k = 0;
        while(i <= m && j <= n){
            if(a[i] < a[j]) {
                temp[k++] = a[i++];
            }else{
                temp[k++] = a[j++];
            }
        }

        while(i <= m)
            temp[k++] = a[i++];
        while(j <= n)
            temp[k++] = a[j++];

        for(int p = 0 ; p < k ; ++p) {
            a[first + p] = temp[p];
        }
    }

    public static void mergeSort(int[] nums, int first, int last, int[] temp) {
        if(first < last) {
            int mid = (first + last) / 2;
            mergeSort(nums, first, mid, temp);
            mergeSort(nums, mid + 1, last, temp);
            merge(nums, first, mid, last, temp);
        }
    }



    /**
     *   归并排序的非递归形式
     *   思路： 两两合并，从小到大合并
     *
     */

    public static void mergeSortUndfs(int[] nums, int n, int[] temp) {

        int size = 1;
        int low;
        int mid;
        int high;
        while(size <= n - 1 ) {
            low = 0;
            while(low + size <= n - 1) {
                mid = low + size - 1;
                high = mid + size;
                if(high > n - 1)
                    high = n - 1;
                merge(nums, low, mid, high, temp);
                System.out.println("size " + size + " low " + low + " mid " + mid +   " high " + high );
                low = high + 1;
            }
            size *= 2;
        }
    }




    public static void main(String[] args){
        int[] nums = {8, 2, 6, 1, 6};
//        QuickSort.quickSort(nums, 0, 4);
        int[] temp = new int[nums.length];
//        mergeSort(nums, 0, nums.length-1, temp);
        System.out.println("hello world");
        mergeSortUndfs(nums, nums.length, temp);

        System.out.println(Arrays.toString(nums));
    }
}
