package stringProblems;

import java.util.*;

public class Test {
	
	public static void main(String[] args) {
		Test sol = new Test();
		System.out.println(sol.longest("bcdfbd"));
		
	}
	
	public int longest(String input) {
	    if (input == null || input.length() == 0) {
	      return 0;
	    }
	    Set<Character> set = new HashSet<>();
	    int res = 0;
	    int slow = 0;
	    int fast = 0;
	    while (fast < input.length()) {
	      if (set.add(input.charAt(fast))) {
	        fast++;
	      } else {
	        while (!set.add(input.charAt(fast))) {
	          set.remove(input.charAt(slow++));
	        }
	        fast++;
	      }
	      res = Math.max(res, fast - slow);
	    } 
	    return res;
	  }

}
