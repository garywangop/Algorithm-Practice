package solution;

import java.util.*;

public class Solution {
	
	public static void main( String[] args) {
		Solution sol = new Solution();
		String s = new String("a,b,c, d");
		System.out.println(s.toCharArray().length);
		
	}
	
	public int depthSum(String nestlists) {
	    if (nestlists == null || nestlists.length() <= 2) {
	      return 0;
	    }
	    Map<Integer, List<Integer>> map = new HashMap<>();
	    int counter = 0;
	    boolean negative = false;
	    for (int i = 0; i < nestlists.length(); i++) {
	      char cur = nestlists.charAt(i);
	      if (cur == '[') {
	        counter++;
	      } else if (cur == ']') {
	        counter--;
	      } else if (cur == ',') {
	    	  continue;
	      } else if (cur == '-') {
	    	  negative = true;
	      } else { // cur == 0 - 9
	        if (map.get(counter) == null) {
	          map.put(counter, new ArrayList<Integer>());
	        } 
	        if (negative) {
	        	map.get(counter).add('0' - cur);
	        	negative = !negative;
	        } else {
	        	map.get(counter).add(cur - '0');
	        }
	        
	      }
	    }
	    return sum(map);
	  }
	  private int sum(Map<Integer, List<Integer>> map) {
	    int res = 0;
	    for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
	      List<Integer> cur = entry.getValue();
	      for (int i = 0; i < cur.size(); i++) {
	        res += cur.get(i) * entry.getKey();
	      }
	    }
	    return res;
	  }
}
