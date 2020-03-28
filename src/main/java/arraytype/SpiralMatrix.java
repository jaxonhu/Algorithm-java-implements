package arraytype;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/14
 * @Time: 下午12:44
 * @Project: Algorithm-Java-implements
 */
public class SpiralMatrix {


    /**
     *
     * 用四个游标 分别表示上下、左右边界
     *
     * 这道题需要注意corner case，low ++ right -- 后可能不满足left <= right  low <= high 的约束
     */

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int m = matrix.length;
        if(m == 0) return result;
        int n = matrix[0].length;
        int left = 0, right = n-1;
        int low = 0, high = m-1;
        while(left<=right && low <= high) {
            for(int i = left ; i <= right ; i ++) {
                result.add(matrix[low][i]);
            }
            low++;
            for(int i = low ; i <= high ; i ++) {
                result.add(matrix[i][right]);
            }
            right--;
            for(int i = right ; low <= high && i >= left; i --) {
                result.add(matrix[high][i]);
            }
            high--;
            for(int i = high ; left <= right && i >= low ; i --) {
                result.add(matrix[i][left]);
            }
            left++;
        }
        return result;
    }

}
