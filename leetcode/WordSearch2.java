package leetcode;

import java.util.*;

public class WordSearch2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordSearch2 sol = new WordSearch2();
		String[] words = new String[] {"eat","oath"};
		char[][] board = new char[][] {
			{'o','a','a','n'},
			{'e','t','a','e'},
			{'i','h','k','r'},
			{'i','f','l','e'}
		};
		List<String> list = sol.findWords(board, words);
		for (String s : list) {
			System.out.println(s);
		}
		
		System.out.println(sol.findWords(new char[][] {{'a', 'a'}}, new String[] {"a"}));
		StringBuilder sb = new StringBuilder();
		sb.append("abc");
		System.out.println(new String(sb));
	}
	
	public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        boolean[][] visited = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
        	for (int j = 0; j < board[i].length; j++) {
        		dfs(board, i, j, root, new StringBuilder(), visited, res);
        	}
        }
		return res;
    }
	
	private final int[][] DIRECTIONS = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
	
	private void dfs(char[][] board, int row, int col, TrieNode root, StringBuilder sb, boolean[][] visited, List<String> res) {
		if (row < 0 || row >= board.length || col < 0 || col >= board[row].length || visited[row][col]) {
			return;
		}
		
		char cur = board[row][col];
		root = root.letter[cur - 'a'];
		if (root == null) {
			return;
		}
		sb.append(cur);
		if (root.isWord) {
			res.add(sb.toString());
			root.isWord = false;
		}
		visited[row][col] = true;
		
		for (int[] direction : DIRECTIONS) {
			dfs(board, row + direction[0], col + direction[1], root, sb, visited, res);
		}
		
		sb.deleteCharAt(sb.length() - 1);
		visited[row][col] = false;
	}
	
	private TrieNode buildTrie(String[] words) {
		TrieNode root = new TrieNode();
		for (String s : words) {
			TrieNode cur = root;
			for (int i = 0; i < s.length(); i++) {
				int index = s.charAt(i) - 'a';
				if (cur.letter[index] == null) {
					cur.letter[index] = new TrieNode();
				}
				cur = cur.letter[index];
			}
			cur.isWord = true;
		}
		return root;
	}
	
	class TrieNode {
		TrieNode[] letter = new TrieNode[26];
		boolean isWord;
	}

}
