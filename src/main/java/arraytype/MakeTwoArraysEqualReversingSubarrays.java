package arraytype;

import java.util.HashMap;
import java.util.Map;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/6/22
 * @ Time: 11:47 下午
 * @ Project: Algorithm-Java-implements
 */
public class MakeTwoArraysEqualReversingSubarrays {


    /**
     *
     * 给你两个长度相同的整数数组 target 和 arr 。
     *
     * 每一步中，你可以选择 arr 的任意 非空子数组 并将它翻转。你可以执行此过程任意次。
     *
     * 如果你能让 arr 变得与 target 相同，返回 True；否则，返回 False 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/make-two-arrays-equal-by-reversing-sub-arrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     */

    public boolean canBeEqual(int[] target, int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        if(target.length != arr.length) return false;
        for(int i = 0 ; i < target.length ; i ++) {
            map.put(target[i], map.get(target[i]) == null ? 1: map.get(target[i]) + 1);
            map.put(arr[i], map.get(arr[i]) == null ? -1: map.get(arr[i]) - 1);
        }
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if(entry.getValue() != 0) return false;
        }
        return true;
    }
}
