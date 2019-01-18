package sort;

import java.util.Arrays;
import java.util.Scanner;

public class DividTeam {

    /**
     *  笔试题：
     */


    public  void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    public int quickSort(int[] nums) {
        int n = nums.length;
        int i = 0, j = n-1;
        int key = nums[0];

        while(i < j) {
            while(nums[j] > key && j > i)
                j--;
            nums[i] = nums[j];
            while(nums[i] < key && j > i)
                i++;
            nums[j] = nums[i];
        }
        nums[j] = key;
        return j;
    }
    public int qulify(int[] nums, int index) {
        int res = 0;
        int left = 0;
        int right = 0;
        for(int i = 0 ; i < index  ; ++i) {
            left += nums[i];
        }
        for(int i = nums.length-1 ; i > index ; --i) {
            right += nums[i];
        }
        if(left == 0 || right == 0)
            return 0;
        if(left < right + nums[index] && left + nums[index] > right)
            res += 1;
        if(left + nums[index] > right && left < right + nums[index])
            res += 1;
        return res;
    }
    public int getDivided(int n, int[] nums) {
        int sum = 0;
        for(int i = 0 ; i < n ; ++ i) {
            int[] copy = Arrays.copyOf(nums, nums.length);
            swap(copy, 0, i);
            int index = quickSort(copy);
            sum += qulify(copy, index);
        }
        return sum;
    }

    public static void main(String[] args) {
        DividTeam dt = new DividTeam();
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            for(int i = 0 ; i < n ; ++ i) {
                nums[i] = sc.nextInt();
            }
            System.out.println(dt.getDivided(n, nums));
        }
    }
}
