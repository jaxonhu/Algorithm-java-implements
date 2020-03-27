package arraytype;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2019/12/25
 * @ Time: 11:53 PM
 * @ Project: Algorithm-Java-implements
 */
public class FindDuplicateNumber {

    static int findDuplicate(int[] nums) {
        int n = nums.length;
        if(n <= 1) return -1;
        int slow = nums[0];
        int fast = nums[nums[0]];
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = 0;
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        System.out.println(findDuplicate(new int[] {1,3,4,2,2}));
    }

}
