package arraytype;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2019/12/25
 * @ Time: 11:53 PM
 * @ Project: Algorithm-Java-implements
 */
public class FindDuplicateNumber {

    public int findDuplicate(int[] nums) {
        int n = nums.length;
        for(int i=0;i<nums.length;i++) nums[i]--;
        int slow = n-1;
        int fast = n-1;
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while(slow != fast);
        slow = n-1;
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow+1;
    }

}
