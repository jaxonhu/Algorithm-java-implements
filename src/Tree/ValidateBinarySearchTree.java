package Tree;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2018/7/6
 * @Time: 上午10:04
 * @Project: Algorithm-Java-implements
 */
public class ValidateBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean valid(TreeNode root, long min, long max) {
        if(root == null)
            return true;

        if(root.val <= min || root.val >= max) {
            return false;
        }

        return valid(root.left, min, root.val) && valid(root.right, root.val, max);
    }
}
