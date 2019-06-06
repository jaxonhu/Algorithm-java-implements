package stringtype;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2019/6/6
 * @Time: 7:23 PM
 * @Project: Algorithm-Java-implements
 */
public class Trie2 {

    class Node {

        public boolean isLeaf = false;
        public Node[] vocals;
        Node() {
            vocals = new Node[27];
        }
    }

    private Node root;

    /** Initialize your data structure here. */
    public Trie2() {
        root = new Node();
    }

    public void addWord(String word) {
        this.addWord(root, word);
    }
    /** Adds a word into the data structure. */
    public void addWord(Node root, String word) {
        word = word.toLowerCase();
        char[] words = word.toCharArray();
        for(int i = 0 ; i < words.length; i ++) {
            int index = words[i] == '.' ? 26 : words[i] - 'a';
            if(root.vocals[index] == null)
                root.vocals[index] = new Node();
            root = root.vocals[index];
        }
        root.isLeaf = true;
    }

    public boolean search(String word) {
        return this.search(root, word);
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(Node root, String word) {
        word = word.toLowerCase();
        char[] words = word.toCharArray();
        for(int i = 0 ; i < words.length ; i ++) {
            int index = words[i] == '.' ? 26: words[i] - 'a';
            if(index == 26) {
                for(int j = 0 ; j < 26 ; j ++) {
                    if(root.vocals[j] != null)
                        if(search(root.vocals[j], word.substring(i+1)))
                            return true;
                }
                return false;
            }
            if(root.vocals[26] != null) {
                root = root.vocals[26];
                continue;
            }
            if(root.vocals[index] == null)
                return false;
            root = root.vocals[index];
        }
        return root.isLeaf;
    }


    public static void main(String[] args) {
        Trie2 t2 = new Trie2();
        t2.addWord(".ab");
        System.out.println(t2.search("cab"));
    }
}
