package arraytype;

import java.util.*;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/4/30
 * @ Time: 10:50 上午
 * @ Project: Algorithm-Java-implements
 */
public class DiagonalTraverse {


    /**
     *
     * 给你一个列表 nums ，里面每一个元素都是一个整数列表。请你依照下面各图的规则，按顺序返回 nums 中对角线上的整数。
     *
     *  
     *
     * 示例 1：
     *
     *
     *
     * 输入：nums = [[1,2,3],[4,5,6],[7,8,9]]
     * 输出：[1,4,2,7,5,3,8,6,9]
     * 示例 2：
     *
     *
     *
     * 输入：nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
     * 输出：[1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
     * 示例 3：
     *
     * 输入：nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
     * 输出：[1,4,2,5,3,8,6,9,7,10,11]
     * 示例 4：
     *
     * 输入：nums = [[1,2,3,4,5,6]]
     * 输出：[1,2,3,4,5,6]
     *  
     *
     * 提示：
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i].length <= 10^5
     * 1 <= nums[i][j] <= 10^9
     * nums 中最多有 10^5 个数字。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/diagonal-traverse-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */

    /**
     * 题目限定的范围比较大，所以n*n的空间复杂度会内存溢出
     */
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int n = nums.size();
        int cnt = 0;
        for(int i = 0 ; i < nums.size() ; i ++) {
            n = Math.max(n, nums.get(i).size());
        }
        int[] arr = new int[n*n];
        //上三角
        for(int i = 0 ; i < n ; i ++) {
            int p = i;int q = 0;
            for(int k = 0 ; k < i+1 ; k ++) {
                if(nums.size() > p && nums.get(p).size() > q) {
                    arr[cnt++] = nums.get(p).get(q);
                }
                p--;q++;
            }
        }
        //下三角
        for(int i = 1 ; i < n ; i ++) {
            int p = n-1; int q = i;
            for(int k = 0 ; k < n-i ; k ++) {
                if(nums.size() > p && nums.get(p).size() > q) {
                    arr[cnt++] = nums.get(p).get(q);
                }
                p--;q++;
            }
        }
        return Arrays.copyOf(arr, cnt);
    }

    /**
     *  正确解法： 关于对角线对称的一组数，xy坐标之和为固定值，分别为 0 ~ 2(n-1)
     *  所以只需要用TreeMap来保存相应对角线上的数，然后顺序遍历nums，计算i+j得到key，将nums[i][j]追加到对应的list中，
     *  由于遍历是自上而下，所以最后需要把list翻转一下就能得到自下而上的顺序
     */
    public int[] findDiagonalOrder2(List<List<Integer>> nums) {
        int n = nums.size();
        int cnt = 0;
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        for(int i = 0 ; i < nums.size() ; i ++) {
            for(int j = 0 ;  j < nums.get(i).size() ; j ++) {
                int key = i+j;
                if(!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                map.get(key).add(nums.get(i).get(j));
                cnt ++;
            }
        }
        int[] res = new int[cnt];
        int idx = 0;
        Iterator iter = map.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();
            ArrayList<Integer> list=  (ArrayList<Integer>)entry.getValue();
            Collections.reverse(list);
            for(Integer i:list) {
                res[idx++] = i;
            }
        }
        return res;
    }

}
