package Tree;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/1/17
 * @ Time: 10:24 PM
 * @ Project: Algorithm-Java-implements
 */
public class NumArray {

    // 线段树

    /**
     *
     * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
     *
     * The update(i, val) function modifies nums by updating the element at index i to val.
     *
     * Given nums = [1, 3, 5]
     *
     * sumRange(0, 2) -> 9
     * update(1, 2)
     * sumRange(0, 2) -> 8
     *
     */

    class SegmentTreeNode {

        int start, end;
        SegmentTreeNode left, right;
        int sum;

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
            this.sum = 0;
        }
    }

    SegmentTreeNode root = null;

    private SegmentTreeNode buildTree(int[] num, int start, int end) {
        if(start > end) {
            return null;
        } else {
            SegmentTreeNode root = new SegmentTreeNode(start, end);
            if(start == end) {
                root.sum = num[start];
            } else {
                int mid = start + (end - start) / 2;
                root.left = buildTree(num, start, mid);
                root.right = buildTree(num, mid+1, end);
                root.sum = root.left.sum + root.right.sum;
            }
            return root;
        }
    }

    private void update(SegmentTreeNode root, int pos, int val) {
        if(root.start == root.end) {
            root.sum = val;
        } else {
            int mid = root.start + (root.end - root.start) / 2;
            if(pos <= mid) {
                update(root.left, pos, val);
            } else {
                update(root.right, pos, val);
            }
            root.sum = root.left.sum + root.right.sum;
        }
    }

    private int sumRange(SegmentTreeNode root, int start, int end) {
        if(root.end == end && root.start == start) {
            return root.sum;
        } else {
            int mid = root.start + (root.end - root.start) / 2;
            if ( end <= mid ) {
                return sumRange(root.left, start, end);
            } else if (start >= mid + 1) {
                return sumRange(root.right, start, end);
            } else {
                return sumRange(root.right, mid+1, end) + sumRange(root.left, start, mid);
            }
        }
    }

    public NumArray(int[] nums) {
        root = buildTree(nums, 0, nums.length-1);
    }

    public void update(int i, int val) {
        update(root, i, val);
    }

    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }

}
