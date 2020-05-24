package review;

import review.Trie.TrieNode;

public class BuildTrie {
	public static void main(String[] args) {
		BuildTrie sol = new BuildTrie();
		String[] arr = new String[] {"ab","abc","aa"};
		TrieNode root = sol.build(arr);
		System.out.println();
	}
	
	static class TrieNode {
		TrieNode[] children = new TrieNode[26];
		boolean isWord;
	}
	
	public TrieNode build(String[] words) {
		TrieNode root = new TrieNode();
		for (String s : words) {
			TrieNode cur = root;
			for (int i = 0; i < s.length(); i++) {
				TrieNode next = cur.children[s.charAt(i) - 'a'];
				if (next == null) {
					next = new TrieNode();
					cur.children[s.charAt(i) - 'a'] = next;
				}
				cur = next;
			}
			cur.isWord = true;
		}
		return root;
	}
}
