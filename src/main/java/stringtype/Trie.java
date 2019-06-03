package stringtype;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2019/6/3
 * @Time: 6:39 PM
 * @Project: Algorithm-Java-implements
 */
public class Trie {


    /**
     *  Trie 树
     */
    private class Node {
        private int dumpli_num;
        private int prefix_num;
        private Node childs[];
        private boolean isLeaf;
        public Node() {
            dumpli_num = 0;
            prefix_num = 0;
            childs = new Node[26];
        }
    }

    private Node root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        insert(this.root, word);
    }

    public void insert(Node root, String word) {
        word = word.toLowerCase();
        char[] chrs = word.toCharArray();
        for(int i = 0, length = chrs.length; i < length; i ++ ) {
            int index = chrs[i] - 'a';
            if( root.childs[index] != null) {
                root.childs[index].prefix_num ++;
            }else {
                root.childs[index] = new Node();
                root.childs[index].prefix_num ++;
            }

            if(i == length - 1) {
                root.childs[index].isLeaf = true;
                root.childs[index].dumpli_num ++;
            }
            root = root.childs[index];
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return search(this.root, word);
    }

    private boolean search(Node root, String word) {
        word = word.toLowerCase();
        char[] chrs = word.toCharArray();
        for(int i = 0, length = chrs.length; i < length ; i++) {
            int index = chrs[i] - 'a';
            if(root.childs[index] == null)
                return false;
            root = root.childs[index];
        }
        if(!root.isLeaf)
            return false;
        return true;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return startsWith(this.root, prefix);
    }

    public boolean startsWith(Node root, String prefix) {
        prefix = prefix.toLowerCase();
        char[] chrs = prefix.toCharArray();
        for(int i = 0, length = chrs.length; i < length ; i++) {
            int index = chrs[i] - 'a';
            if(root.childs[index] == null)
                return false;
            root = root.childs[index];
        }
        return true;
    }
}
