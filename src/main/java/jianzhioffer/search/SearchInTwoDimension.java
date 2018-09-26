package jianzhioffer.search;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/8/1
 * @Time: 下午9:49
 * @Project: Algorithm-Java-implements
 */
public class SearchInTwoDimension {


    public boolean Find(int target, int [][] array) {
        int n = array.length;
        if(n == 0) return false;
        int m = array[0].length;
        int i = 0, j = m - 1;
        while(i < n && j >= 0) {
            if(array[i][j] == target) {
                return true;
            }else if(array[i][j] > target) {
                j -- ;
            }else {
                i ++;
            }
        }
        return false;
    }
}
