package dp;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DP {
	public static void main(String args[]) {
		DP dp = new DP();
		Random rand = new Random();
		System.out.println(rand.nextInt(5));
	}
	
	// Fibonacci Number
	public long fibonacci(int k) {
		long a = 0, b = 1;
		if (k <= 0) {
			return a;
		}
		while (k > 1) {
			long temp = a + b;
			a = b;
			b = temp;
			k--;
		}
		return b;
	}
		
	// Longest Ascending SubArray
	public int longest(int[] arr) {
		// Assumptions: the given array is not null
		if (arr.length == 0) {
			return 0;
		}
		int res = 1;
		int cur = 1;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > arr[i - 1]) {
				cur++;
				res = Math.max(res, cur);
			} else {
				cur = 1;
			}
		}
		return res;
	}
	
	// Max Product Of Cutting Rope
	// Solution 1: 左大段 + 右大段
	public int maxProduct(int length) {
	    int[] m = new int[length + 1];
	    // base case:
	    m[1] = 0;
	    for (int i = 2; i <= length; i++) {
	      for (int j = 1; j <= i / 2; j++) {
	        m[i] = Math.max(m[i], Math.max(j, m[j]) * Math.max(i - j, m[i - j]));
	      }
	    }
	    return m[length];
	  }
	
	// Solution 2: 左大段 + 右小段
	public int maxProductII(int length) {
		int[] m = new int[length + 1];
		// base case:
		m[1] = 0;
		for (int i = 2; i <= length; i++) {
			for (int j = 1; j < i; j++) {
				m[i] = Math.max(m[i], Math.max(j, m[j]) * (i - j));
			}
		}
		return m[length];
	}
	
	// Array Hopper I
	// 从前往后跳
	public boolean canJump(int[] array) {
	    // Assumptions: array is not null and is not empty
	    boolean[] canJump = new boolean[array.length];
	    canJump[0] = true;
	    for (int i = 1; i < array.length; i++) {
	      for (int j = 0; j < i; j++) {
	        // If index j is reachable from index 0, and from index j
	        // it is possible to jump to index i
	        if (canJump[j] && array[j] + j >= i) {
	          canJump[i] = true;
	          break;
	        }
	      }
	    }
	    return canJump[array.length - 1];
	  }
	
	// 从后往前跳
	public boolean canJumpII(int[] arr) {
		if (arr.length == 1) {
			return true;
		}
		boolean[] canJump = new boolean[arr.length];
		for (int i = arr.length - 2; i >= 0; i--) {
			if (i + arr[i] >= arr.length - 1) {
				canJump[i] = true;
			} else {
				for (int j = arr[i]; j >= 1; j--) {
					if (canJump[j + i]) {
						canJump[i] = true;
						break;
					}
				}
			}
		}
		return canJump[0];
	}
	
	// Array Hopper II: min jump to the end
	public int minJump(int[] array) {
		// minJump这个数组代表了从起点跳到当前index的最少步数
		int[] minJump = new int[array.length];
		minJump[0] = 0;
		for (int i = 1; i < array.length; i++) {
			minJump[i] = -1;
			for (int j = i - 1; j >= 0; j--) {
				if (j + array[j] >= i && minJump[j] != -1) {
					if (minJump[i] == -1 || minJump[i] > minJump[j] + 1) {
						minJump[i] = minJump[j] + 1;
					}
				}
			}
		}
		return minJump[array.length - 1];
	}
	
	// Largest SubArray Sum
	public int largestSum(int[] array) {
		int res = array[0];
		int cur = array[0];
		for (int i = 1; i < array.length; i++) {
			cur = Math.max(cur + array[i], array[i]);
			res = Math.max(res, cur);
		}
		return res;
	}
	
	// Largest SubArray Sum
	/**
	 * Given a word and a dictionary, determine if it can be composed by
	 * concatenating words from the given dictionary.
	 */
	public int[] largestSumII(int[] arr) {
		int gL = 0;
		int gR = 0;
		int cL = 0;
		int cur = arr[0];
		int res = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] + cur >= arr[i]) {
				cur = arr[i] + cur;
			} else { // arr[i] + cur < arr[i]
				cur = arr[i];
				cL = i;
			}
			if (res <= cur) {
				// update res
				res = cur;
				gL = cL;
				gR = i;
			}
		}
		return new int[] { res, gL, gR };
	}
	
	// Dictionary Word I
	/*
	 * Given a word and a dictionary, determine if it can be composed by
	 * concatenating words from the given dictionary.
	 */
	public boolean canBreak(String input, String[] dict) {
		Set<String> dictSet = toSet(dict);
		boolean[] canBreak = new boolean[input.length() + 1];
		canBreak[0] = true;
		for (int i = 1; i < canBreak.length; i++) {
			for (int j = 0; j < i; j++) {
				if (dictSet.contains(input.substring(j, i)) && canBreak[j]) {
					canBreak[i] = true;
					break;
				}
			}
		}
		return canBreak[canBreak.length - 1];
	}

	private Set<String> toSet(String[] dict) {
		Set<String> set = new HashSet<>();
		for (String s : dict) {
			set.add(s);
		}
		return set;
	}
	
	// Edit Distance
	/*
	 * Given two strings of alphanumeric characters, determine the minimum number of
	 * Replace, Delete, and Insert operations needed to transform one string into
	 * the other.
	 * 
	 * Assumptions: Both strings are not null
	 * 
	 * Examples: string one: “sigh”, string two : “asith”
	 * 
	 * the edit distance between one and two is 2 (one insert “a” at front then
	 * replace “g” with “t”).
	 */
	public int editDistance(String one, String two) {
		// Assumptions: one, two are not null
		// Again, using distance[i][j] to represent
		// substring(0, i) in one and substring(o, j) in two
		int[][] distance = new int[one.length() + 1][two.length() + 1];
		for (int i = 0; i <= one.length(); i++) {
			for (int j = 0; j <= two.length(); j++) {
				if (i == 0) {
					distance[i][j] = j;
				} else if (j == 0) {
					distance[i][j] = i;
				} else if (one.charAt(i - 1) == two.charAt(j - 1)) {
					distance[i][j] = distance[i - 1][j - 1];
				} else {
					distance[i][j] = Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1);
					distance[i][j] = Math.min(distance[i - 1][j - 1] + 1, distance[i][j]);
				}
			}
		}
		return distance[one.length()][two.length()];
	}
	public int editDistanceII(String one, String two) {
	    // Base case
	    if (one.isEmpty()) {
	      return two.length();
	    }
	    if (two.isEmpty()) {
	      return one.length();
	    }
	    // Recursion rule:
	    // Case 1: if one.charAt(0) is same as two.charAt(0), we don't need to anything
	    if (one.charAt(0) == two.charAt(0)) {
	      int nothing = editDistanceII(one.substring(1), two.substring(1));
	      return nothing;
	    }
	    // Case 2: do replace
	    int replace = 1 + editDistanceII(one.substring(1), two.substring(1));
	    // Case 3: if one is too long, we need to do delete
	    int delete = 1 + editDistanceII(one.substring(1), two);
	    // Case 4: Check what the distance is if we do a Insert first
	    int insert = 1 + editDistanceII(one, two.substring(1));
	    return min(replace, delete, insert);
	  }
	  
	private int min(int a, int b, int c) {
		if (a - Math.min(b, c) <= 0) {
			return a;
		} else if (b - Math.min(a, c) <= 0) {
			return b;
		} else {
			return c;
		}
	}
	
	// Largest Square Of 1s
	public int largest(int[][] matrix) {
	    // Assumptions: the matrix is a binary matrix
	    // (only contains 0 or 1 as the values),
	    // it is not null, and has size N*N, N >= 0.
	    int N = matrix.length;
	    if (N == 0) {
	      return 0;
	    }
	    int result = 0;
	    // dp[i][j] means the largest square of 1's with right bottom
	    // corner as matrix[i][j]
	    int[][] largest = new int[N][N];
	    for (int i = 0; i < N; i++) {
	      for (int j = 0; j < N; j++) {
	        if (i == 0 || j == 0) {
	          largest[i][j] = matrix[i][j] == 1 ? 1 : 0;
	        } else if (matrix[i][j] == 1) {
	          largest[i][j] = Math.min(largest[i][j - 1] + 1, largest[i - 1][j] + 1);
	          largest[i][j] = Math.min(largest[i - 1][j - 1] + 1, largest[i][j]);
	        }
	        result = Math.max(result, largest[i][j]);
	      }
	    }
	    return result;
	  }
		
}
