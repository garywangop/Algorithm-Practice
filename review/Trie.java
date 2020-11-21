package review;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Trie {

	public static void main(String[] args) {
		char[][] arr = new char[][]{
			  {'o','a','a','n'},
			  {'e','t','a','e'},
			  {'i','h','k','r'},
			  {'i','f','l','v'}
			};
		String[] words = new String[] {"oaa","oath"};
		Trie sol = new Trie();
		System.out.println(sol.findWords(arr, words));
		System.out.println(sol.findWords(new char[][] {{'a', 'a'}}, new String[] {"a"}));
	}

	/*
	 * 431. Word Search II
	 * 
	 * Given a 2D board and a list of words from the dictionary, find all words in
	 * the board.
	 * 
	 * Each word must be constructed from letters of sequentially adjacent cell,
	 * where "adjacent" cells are those horizontally or vertically neighboring. The
	 * same letter cell may not be used more than once in a word.
	 * 
	 * For example, Given words = ["oath","pea","eat","rain"] and board =
	 * 
	 * [ ['o','a','a','n'], ['e','t','a','e'], ['i','h','k','r'], ['i','f','l','v']
	 * ] Return ["eat","oath"].
	 * 
	 * Note: You may assume that all inputs are consist of lowercase letters a-z.
	 */

	static class TrieNode {
		TrieNode[] children = new TrieNode[26];
		boolean isWord;
	}

	static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public List<String> findWords(char[][] board, String[] words) {
		if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) {
			return new ArrayList<>();
		}
		Set<String> res = new HashSet<>();
		TrieNode root = buildDict(words);
		final int rows = board.length;
		final int cols = board[0].length;
		boolean[][] visited = new boolean[rows][cols];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				helper(board, i, j, root, sb, res, visited);
			}
		}
		return new ArrayList<>(res);
	}

	private TrieNode buildDict(String[] words) {
		TrieNode root = new TrieNode();
		for (String word : words) {
			TrieNode cur = root;
			for (int i = 0; i < word.length(); i++) {
				TrieNode next = cur.children[word.charAt(i) - 'a'];
				if (next == null) {
					next = new TrieNode();
					cur.children[word.charAt(i) - 'a'] = next;
				}
				cur = next;
			}
			cur.isWord = true;
		}
		return root;
	}

	private void helper(char[][] board, int x, int y, TrieNode root, StringBuilder sb, Set<String> res,
			boolean[][] visited) {
		if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y]) {
			return;
		}
		char ch = board[x][y];
		if (root.children[ch - 'a'] == null) {
			return;
		}
		sb.append(ch);
		root = root.children[ch - 'a'];
		if (root.isWord) {
			res.add(sb.toString());
		}
		visited[x][y] = true;
		for (int[] dir : DIRS) {
			int neiX = dir[0] + x;
			int neiY = dir[1] + y;
			helper(board, neiX, neiY, root, sb, res, visited);
		}
		visited[x][y] = false;
		sb.deleteCharAt(sb.length() - 1);
	}
}
