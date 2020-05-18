package binary_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/5/13
 * @ Time: 2:40 下午
 * @ Project: Algorithm-Java-implements
 */
public class CountSmallerNumbersAfterSelf {

    /**
     * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
     *
     * 示例:
     *
     * 输入: [5,2,6,1]
     * 输出: [2,1,1,0]
     * 解释:
     * 5 的右侧有 2 个更小的元素 (2 和 1).
     * 2 的右侧仅有 1 个更小的元素 (1).
     * 6 的右侧有 1 个更小的元素 (1).
     * 1 的右侧有 0 个更小的元素.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     */


    /**
     *
     *  这道题卡了很都没过，很多corner case，而且二分搜索时要注意是否允许重复，要找的边界和target的关系，是否允许相等
     *
     *  结果还是超时了
     */
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> heads = new ArrayList<>();
        int n = nums.length;
        int[] res = new int[n];
        int head = 0;
        heads.add(0);
        for(int i = 0 ; i < n - 1 ; i ++) {
            if(nums[i] <= nums[i+1]) {
                head = i+1;
                heads.add(head);
            }
        }
        for(int i = 0 ; i < n ; i ++) {
            int cur = 0;
            int idx = -1;
            int k = n;
            for(int j = 0 ; j < heads.size() ; j ++) {
                if(heads.get(j) > i) {
                    idx = heads.get(j);
                    k = j;
                    break;
                }
            }
            cur += idx == -1? n - i - 1: idx - i - 1;
            int prevHead = idx;
            for(int j = k ; j < heads.size() ; j ++) {
                int curHead = heads.get(j);
                int nextHead = j >= heads.size() - 1 ? n : heads.get(j+1);
                int pos = getCell(nums, prevHead, nextHead - 1, nums[i]);
                prevHead = nextHead;
                if(nums[pos] < nums[i]) {
                    cur += nextHead - pos;
                }

            }
            res[i] = cur;
        }
        List<Integer> result = Arrays.stream(res).boxed().collect(Collectors.toList());;
        return result;
    }

    int getCell(int[] nums, int start, int end, int target) {
        int left = start, right = end;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                left = mid + 1;
            } else if(nums[mid] > target) {
                left = mid + 1;
            } else
                right = mid;
        }
        return left;
    }

    /**
     *
     * 解法2 ：
     *  我们只要把输入数组反过来插入一个有序数组（降序）中，插入的位置就是在原数组中位于它右侧的元素的个数。
     * 不好理解的话，我们看例子。
     *
     * 初始状态：
     * 原数组为：[5,2,6,1]
     * 排序数组：[]
     * 结果数组：[]
     *
     * 第一轮：
     * 原数组为：[5,2,6]
     * 排序数组：[1]
     * 插入的下标为 0，记入结果数组：[0]
     *
     * 第二轮：
     * 原数组为：[5,2]
     * 排序数组：[1,6]
     * 插入的下标为 1，记入结果数组：[0,1]
     *
     * 第三轮：
     * 原数组为：[5]
     * 排序数组：[1,2,6]
     * 插入的下标为 1，记入结果数组：[0,1,1]
     *
     * 第四轮：
     * 原数组为：[]
     * 排序数组：[1,2,5,6]
     * 插入的下标为 2，记入结果数组：[0,1,1,2]
     *
     * 最后我们把结果数组逆序一下，就得到了最终的结果。
     * 算法的复杂度的上界为：n \log_2 nnlog
     * 2
     *
     *  这题刷得真心自闭了
     */

    public List<Integer> countSmaller2(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] arr = new int[n];
        int len = 0;
        for(int i = n - 1 ; i >= 0 ; i --) {
            if(len == 0) {
                arr[0] = nums[i];
                res[0] = 0;
                len = 1;
            } else {
                int left = 0, right = len - 1;
                int target = nums[i];
                while(left < right) {
                    int mid = left + (right - left) / 2;
                    if(arr[mid] == target) {
                        right = mid;
                    } else if(arr[mid] > target) {
                        right = mid;
                    } else
                        left = mid + 1;
                }
                if(arr[left] < target) left += 1;
                res[n-i-1] = left;
                // System.out.println(left);
                for(int j = len - 1 ; j >= left ; j --) {
                    arr[j+1] = arr[j];
                }
                arr[left] = target;
                len ++;
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int i = 0 ; i < n ; i ++) {
            result.add(0, res[i]);
        }
        return result;
    }

    /**
     *  归并排序解法
     */

    private int[] index; // 记录中间排序结果对应在nums数组中的index
    private int[] helper;// 作用等同于归并排序中的temp数组
    private int[] count; // 最终结果

    public List<Integer> countSmaller3(int[] nums) {
        List<Integer> res = new ArrayList<>(nums.length);

        index = new int[nums.length];
        helper = new int[nums.length];
        count = new int[nums.length];
        for (int i = 0; i < index.length; i++) {
            index[i] = i;
        }

        merge(nums, 0, nums.length - 1);

        for (int i = 0; i < count.length; i++) {
            res.add(i, count[i]);
        }
        return res;
    }

    private void merge(int[] nums, int s, int e) {
        if (s == e || s > e) return;
        int mid = (s + e) >> 1;

        if (s < mid) {
            merge(nums, s, mid);
        }

        if (mid + 1 < e) {
            merge(nums, mid + 1, e);
        }

        int i = s, j = mid + 1;
        int hi = s;
        while (i <= mid && j <= e) {
            if (nums[index[i]] <= nums[index[j]]) {
                // 右侧出
                helper[hi++] = index[j++];
            } else {
                // 左侧出 计数
                count[index[i]] += e - j + 1;
                helper[hi++] = index[i++];
            }
        }

        while (i <= mid) {
            //左侧出
            helper[hi++] = index[i++];
        }

        while (j <= e) {
            // 右侧出
            helper[hi++] = index[j++];
        }

        for (int k = s; k <= e; k++) {
            index[k] = helper[k];
        }
    }
}
