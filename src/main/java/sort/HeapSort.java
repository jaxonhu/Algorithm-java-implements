package sort;

import java.util.Arrays;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/6/19
 * @Time: 上午10:33
 * @Project: Algorithm-Java-implements
 */
public class HeapSort {

    /**
     *  堆排序
     *  堆数据结构的特性：每个节点的值大于等于左右孩子节点的值
     *  复杂度：最好最坏情况都是 O(nlogn) 不稳定排序
     *  特性 大顶堆： arr[i] >= a[2i + 1]  arr[i] >= arr[2i + 2]
     *      小顶堆： arr[i] <= a[2i+1]  arr[i] <= arr[2i + 2]
     *
     *  基本思想： 将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点，将其于末尾元素进行交换，
     *           此时末尾就是最大值，然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。迭代执行。
     *
     *  最后一个非叶子节点： arr.length/2 - 1   2i+1 = n - 1
     *
     *  三个步骤：
     *  1. 将序列构建成一个堆，根据升序降序需求选择大顶堆或者小顶堆
     *  2. 将堆顶元素与末尾元素交换，将最大元素下沉到末尾
     *  3. 重新调整结构，重复这个过程
     */

    public static void heapSort(int[] nums) {
        //从第一个非叶子节点开始，先自下而上构造一个大顶堆
        for(int i = nums.length/2 - 1 ; i >= 0 ; --i) {
            adjustHeap(nums, i, nums.length);
        }
        //调整堆结构
        for(int j = nums.length - 1 ; j > 0 ; --j) {
            //堆顶元素与末尾元素交换
            swap(nums, 0, j);
            //重新从堆顶开始调整
            adjustHeap(nums, 0, j);
        }
    }

    public static void adjustHeap(int[] nums, int i, int length) {
        //保存堆顶元素
        int temp = nums[i];
        //从根节点的左子节点开始遍历
        for(int k = 2 * i + 1 ; k < length ; k = k * 2 + 1) {
            //如果有右子节点且右子节点的值比左子节点值大
            if(k + 1 < length && nums[k] < nums[k + 1]) {
                k ++;
            }
            //如果大于根节点，调整结构，这是自上而下的调整过程
            if(nums[k] > temp) {
                nums[i] = nums[k];
                i = k;
            }else {
                //从节点i开始满足大顶堆的特性了，不需要遍历子节点的子节点
                break;
            }
        }
        nums[i] = temp;
    }


    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }


    public static void main(String[] args){
        int[] nums = {8, 2, 6, 1, 6};
        heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }

}
