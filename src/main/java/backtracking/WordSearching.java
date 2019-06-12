package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jaxon
 * @Description:
 * @Date: 2019/6/10
 * @Time: 4:15 PM
 * @Project: Algorithm-Java-implements
 */
public class WordSearching {

    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0) return new ArrayList<>();
        root = new TrieNode();
        for (String word :words) {
            insert(word);
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                search(res, board, i, j, root);
            }
        }
        return res;
    }

    private void search(List<String> res, char[][] board, int x, int y, TrieNode cur) {
        char c = board[x][y];
        if (c == 0 || cur.kids[c - 'a'] == null) {
            return;
        }
        cur = cur.kids[c - 'a'];
        if (cur.word != null) {
            res.add(cur.word);
            cur.word = null;
        }
        board[x][y] = 0;
        for (int i = 0; i < 4; ++i) {
            int m = x + dx[i];
            int n = y + dy[i];
            if (0 <= m && m < board.length && 0 <= n && n < board[x].length) {
                search(res, board, m, n, cur);
            }
        }
        board[x][y] = c;
    }

    int[] dx = new int[]{0, -1, 0, 1};
    int[] dy = new int[]{-1, 0, 1, 0};

    private void insert(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (cur.kids[i] == null) {
                cur.kids[i] = new TrieNode();
            }
            cur = cur.kids[i];
        }
        cur.word = word;
    }

    private TrieNode root;

    private class TrieNode {
        String word;
        TrieNode[] kids;

        TrieNode() {
            kids = new TrieNode[26];
        }
    }
}
