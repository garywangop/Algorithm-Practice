package stringProblems;

import java.util.*;

public class Test {
	
	public static void main(String[] args) {
		Test sol = new Test();
		int[] arr = new int[] {1,0,0,1,1,1,0,1,0,1,1,0,1,0,1,1,1,0,1,0,1,1,1,0,0,0,1,1,1,1,1,1,0,0,1,1,1,0,0,1,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,1,0,1,0,0,0,1,1,0,1,0,1,0,0,1,1,0,0,1,0,1,0,0,0,1,1,0,0,1,1,1,1,1,1,1,0,1,1,1,0,1,0,0,0,1,0,1,1,1,1,0,0,1,0,1,0,1,1,0,1,0,1,1,0,0,1,1,0,1,1,0,1,1,0,0,0,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,1,0,1,0,1,0,1,1,0,0,1,0,0,0,0,0,0,1,0,0,0,1,0,0,1,0,0,0,0,0,1,1,0,0,0,0,0,1,0,1,1,0,0,0,1,1,1,0,0,1,1,1,0,1,1,1,0,0,0,0,0,0,1,0,1,0,1,0,1,1,0,1,0,0,0,1,0,1,1,1,0,1,1,1,0,1,0,1,1,0,1,1,1,1,0,0,0,1,1,1,1,0,1,1,1,0,1,0,0,0,0,0,1,1,1,0,1,0,0,0,1,0,0,1,1,0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,0,0,0,0};
		System.out.println(sol.longestConsecutiveOnes(arr, 76));
		
		String s1 = new String("laioffercom");
		String s2 = new String("l10");
		System.out.println(sol.match(s1, s2));
		
	}
	
	/*
	k = 2
	
			0 1 2 3 4 5 6 7 8 9
			1,1,0,0,1,1,1,0,0,0
			      s
			              f
cur			1 2 3 4 5 6 7
globle_max	1 2 3 4 5 6 7
countZero	2 2 1 0 0 0 0 
	countZero <= k
	*/
	
	
	/*
	  	1. Length of given array is between [1, 20000].

		2. The given array only contains 1s and 0s.

		3. 0 <= k <= length of given array.
	 */
	public int longestConsecutiveOnes(int[] nums, int k) {
	    int slow = 0, fast = 0, res = 0, count = k;
	    while (fast < nums.length) {
	    	if (nums[fast] == 0) {
	    		if (count > 0) {
	    			count--;
	    		} else {
	    			while (nums[slow] != 0 && slow < fast) {
	    				slow++;
	    			}
	    			slow++;
	    		}
	    	}
	    	res = Math.max(res, ++fast - slow);
	    }
	    return res;
	}
	
	public boolean match(String input, String pattern) {
	    return helper(input, pattern, 0, 0);
	  }

	private boolean helper(String input, String pattern, int i, int j) {
	    if (i == input.length() && j == pattern.length()) {
      return true;
    }

    if (i >= input.length() || j >= pattern.length()) {
      return false;
    }

    char source = input.charAt(i);
    char target = pattern.charAt(j);
    if (target >= 'a' && target <= 'z') {
      if (source != target) {
        return false;
      } else {
        return helper(input, pattern, i + 1, j + 1);
      }
    }

    int count = 0;
    if (target >= '0' && target <= '9') {
      while (j < pattern.length() && pattern.charAt(j) >= '0' && pattern.charAt(j) <= '9') {
        count = count * 10 + pattern.charAt(j) - '0';
        j++;
      }
      return helper(input, pattern, i + count, j);
    }

    return false;
}
}
