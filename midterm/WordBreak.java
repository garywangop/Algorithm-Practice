package midterm;

import java.util.*;

public class WordBreak {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[] dictionary = new String[] {"apple", "pen", "applepen", "pine", "pineapple"};
		System.out.println(WordBreak.canBreak("pineapplepenapple", dictionary));
	}
	
	// input = catsend
	// dictionary = ["cat", "cats", "send", "end"]
	// output = 2(cats | end, cat | send)
	// catsendcatsend
	/*
	 m.length = 15
	 		012345678901234
	        catsendcatsend
	 m[i] = 000110020022004
	 
	 cat|send|cat|send
	 cat|send|cats|end
	 cats|end|cat|send
	 cats|end|cats|end
	 
	  catsend
	  00011002
	 */
	
	public static int canBreak(String input, String[] dictionary) {
		Set<String> set = getDictionary(dictionary);
		// m[i] represents the total ways of cuts of first i letters
		int[] m = new int[input.length() + 1];
		for (int i = 1; i <= input.length(); i++) {
			
			if (set.contains(input.substring(0, i))) {
				m[i]++;
			}
			
			for (int j = 1; j < i; j++) {
				if (m[j] > 0 && set.contains(input.substring(j, i))) {
					m[i] += m[j];
				}
			}
		}
		return m[input.length()];
	}
	
	private static Set<String> getDictionary(String[] s) {
		Set<String> set = new HashSet<>();
		for (String str : s) {
			set.add(str);
		}
		return set;
	}

}
