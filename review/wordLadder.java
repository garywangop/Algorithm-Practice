package review;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.ArrayDeque;

public class wordLadder {

	public static void main(String[] args) {
		wordLadder sol = new wordLadder();
		List<String> words = new ArrayList<>();
		words.add("hot");
		words.add("dot");
		words.add("dog");
		words.add("lot");
		words.add("log");
		words.add("cog");
//		words.add("ab");
//		words.add("ac");
//		words.add("bc");
//		words.add("cc");
//		words.add("cd");
		System.out.println(sol.ladderLength("hit", "cog", words));

	}
	
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Set<String> words = new HashSet<>();
		for (String s : wordList) {
			words.add(s);
		}
		Set<String> visited = new HashSet<>();
		int res = 1;
		Queue<String> q = new ArrayDeque<>();
		q.offer(beginWord);
		while (!q.isEmpty()) {
			res++;
			int size = q.size();
			while (size > 0) {
				size--;
				String s = q.poll();
				visited.add(s);
				for (int i = 0; i < s.length(); i++) {
					char[] arr = s.toCharArray();
					for (char j = 'a'; j <= 'z'; j++) {
						arr[i] = j;
						String str = new String(arr);
						if (str.equals(endWord) && words.contains(endWord)) {
							return res;
						}
						if (words.contains(str) && !visited.contains(str)) {
							q.offer(str);
						}
					}
				}
			}
			
		}
		return 0;
	}

}
