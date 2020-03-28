package review;

import java.util.Map;
import java.util.HashMap;

public class MinWindowSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(minWindow("AAAAAAA", "AAA"));
	}
	
	public static String minWindow(String source, String target) {
		// Assume both source and target are not null
	    if (source.length() < target.length()) {
	      return "";
	    }
	    Map<Character, Integer> map = new HashMap<>();
	    for (int i = 0; i < target.length(); i++) {
	      Integer freq = map.get(target.charAt(i));
	      if (freq == null) {
	        map.put(target.charAt(i), 1);
	      } else {
	        map.put(target.charAt(i), freq+1);
	      }
	    }
	    int minLen = Integer.MAX_VALUE, matchCount = 0, index = 0;
	    int slow = 0;
	    for (int fast = 0; fast < source.length(); fast++) {
	      char ch = source.charAt(fast);
	      Integer count = map.get(ch);
	      if (count == null) {
	        continue;
	      }
	      map.put(ch, count - 1);
	      // Match another character
	      if (count == 1) {
	        matchCount++;
	      }
	      while (matchCount == map.size()) {
	        // find a valid substring
	        if (fast - slow + 1 < minLen) {
	          minLen = fast - slow + 1;
	          index = slow;
	        }
	        char leftmost = source.charAt(slow++);
	        Integer leftmostCount = map.get(leftmost);
	        if (leftmostCount == null) {
	          continue;
	        }
	        map.put(leftmost, leftmostCount + 1);
	        if (leftmostCount == 0) {
	          // 0 -> 1
	          matchCount--;
	        }
	      }
	    }
	    return minLen == Integer.MAX_VALUE ? "" : source.substring(index, index + minLen);
		
	}

}
