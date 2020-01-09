package Tree;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.Function;

/**
 * @ Author: jaxon
 * @ Description:
 * @ Date:  2020/1/9
 * @ Time: 8:36 PM
 * @ Project: Algorithm-Java-implements
 */
public class SerializeBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private static final String spliter = ",";
    private static final String NN = "X";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NN).append(spliter);
        } else {
            sb.append(node.val).append(spliter);
            buildString(node.left, sb);
            buildString(node.right,sb);
        }
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        if (val.equals(NN)) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }


    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-5);
        root.left.left = null;
        root.left.right = null;
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.left.left = null;
        root.right.left.right = null;
        root.right.right = new TreeNode(5);
        root.right.right.left = null;
        root.right.right.right = null;

        SerializeBinaryTree codec = new SerializeBinaryTree();
        String s = codec.serialize(root);
        TreeNode r2 = codec.deserialize(s);
        System.out.println("a");
    }

}
