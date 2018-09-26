package arraytype;

import java.util.Arrays;
import java.util.Scanner;

public class SuperTrans {

    /**
     * 百度笔试题，超级变变变
     * 考察逆序对
     * 应该还可以优化
     */

    public long reversePairs(int[] A) {
        long[] res=new long[1];
        mergeSort(A,0,A.length-1,res);
        return res[0];
    }

    public void mergeSort(int[] A,int l,int r,long[] res){
        if(l>=r){
            return;
        }
        int mid=(l+r)/2;
        mergeSort(A,l,mid,res);
        mergeSort(A,mid+1,r,res);
        merge(A,l,r,mid,res);
    }
    public void merge(int[] A,int l, int r,int mid,long[] res){
        int[] B=new int[mid-l+1];
        int[] C=new int[r-mid];
        for(int i=0;i<mid-l+1;i++){
            B[i]=A[l+i];
        }
        for(int i=0;i<r-mid;i++){
            C[i]=A[mid+1+i];
        }
        int i=0,j=0,k=l;
        while(i<mid-l+1 &&j<r-mid){
            if(B[i]<=C[j]){
                A[k++]=B[i++];
            }
            else{
                res[0]+=mid-l-i+1;
                A[k++]=C[j++];
            }
        }
        while(i<mid-l+1){
            A[k++]=B[i++];
        }
        while(j<r-mid){
            A[k++]=C[j++];
        }
    }

    public static void main(String[] args) {
        SuperTrans st = new SuperTrans();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        long minDemage = Integer.MAX_VALUE;
        int index = 0;
        for(int i = 0 ; i < n ; i ++) {
            nums[i] = sc.nextInt();
        }
        for(int i = 0; i < n ; i ++) {
            int[] numsCopy = Arrays.copyOf(nums, n);
            numsCopy[i] = 0;
            long reverses = st.reversePairs(numsCopy);
            if(reverses < minDemage) {
                minDemage = reverses;
                index = i;
            }
        }
        System.out.print(minDemage);
        System.out.print(" ");
        System.out.print(index+1);
    }
}
