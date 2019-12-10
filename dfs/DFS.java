package dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFS {
	public static void main(String args[]) {
		DFS sol = new DFS();
		char[] arr = { 'a', 'b', 'c' };
		System.out.print(new String(arr, 0, 2));

	}

	/*
	 * All Subsets II - 63 Given a set of characters represented by a String, return
	 * a list containing all subsets of the characters. Notice that each subset
	 * returned will be sorted to remove the sequence.
	 * 
	 * Assumptions
	 * 
	 * There could be duplicate characters in the original set. ​Examples
	 * 
	 * Set = "abc", all the subsets are ["", "a", "ab", "abc", "ac", "b", "bc", "c"]
	 * Set = "abb", all the subsets are ["", "a", "ab", "abb", "b", "bb"] Set =
	 * "abab", all the subsets are ["", "a", "aa","aab", "aabb", "ab","abb","b",
	 * "bb"] Set = "", all the subsets are [""] Set = null, all the subsets are []
	 */
	public List<String> subSets(String set) {
		List<String> res = new ArrayList<>();
		if (set == null) {
			return res;
		}
		StringBuilder sb = new StringBuilder();
		char[] arr = set.toCharArray();
		Arrays.sort(arr);
		dfs63(sb, 0, res, arr);
		return res;
	}

	private void dfs63(StringBuilder sb, int index, List<String> res, char[] arr) {
		if (index == arr.length) {
			res.add(new String(sb));
			return;
		}

		// Add character in this level
		dfs63(sb.append(arr[index]), index + 1, res, arr);
		sb.deleteCharAt(sb.length() - 1);
		while (index < arr.length - 1 && arr[index] == arr[index + 1]) {
			index++;
		}
		// Don't add character in this level
		dfs63(sb, index + 1, res, arr);

	}

	// All Subsets of Size K - 640
	/*
	 * Given a set of characters represented by a String, return a list containing
	 * all subsets of the characters whose size is K.
	 * 
	 * Assumptions
	 * 
	 * There are no duplicate characters in the original set.
	 * 
	 * ​Examples
	 * 
	 * Set = "abc", K = 2, all the subsets are [“ab”, “ac”, “bc”].
	 * 
	 * Set = "", K = 0, all the subsets are [""].
	 * 
	 * Set = "", K = 1, all the subsets are [].
	 */
	public List<String> subSetsOfSizeK(String set, int k) {
		List<String> res = new ArrayList<>();
		if (set == null) {
			return res;
		}
		char[] arr = set.toCharArray();
		StringBuilder sb = new StringBuilder();
		dfs640(arr, sb, 0, k, res);
		return res;
	}

	private void dfs640(char[] arr, StringBuilder sb, int index, int k, List<String> res) {
		if (sb.length() == k) {
			res.add(new String(sb));
			return;
		}
		if (index == arr.length) {
			return;
		}
		// Do not add arr[index] at this level
		dfs640(arr, sb, index + 1, k, res);
		// Add arr[index] at this level
		dfs640(arr, sb.append(arr[index]), index + 1, k, res);
		sb.deleteCharAt(sb.length() - 1);
	}

	/*
	 * 404. Factor Combinations Given an integer number, return all possible
	 * combinations of the factors that can multiply to the target number.
	 * 
	 * Example
	 * 
	 * Give A = 24
	 * 
	 * since 24 = 2 x 2 x 2 x 3
	 * 
	 * = 2 x 2 x 6
	 * 
	 * = 2 x 3 x 4
	 * 
	 * = 2 x 12
	 * 
	 * = 3 x 8
	 * 
	 * = 4 x 6
	 * 
	 * your solution should return
	 * 
	 * { { 2, 2, 2, 3 }, { 2, 2, 6 }, { 2, 3, 4 }, { 2, 12 }, { 3, 8 }, { 4, 6 } }
	 * 
	 * note: duplicate combination is not allowed.
	 */
	public List<List<Integer>> combinations(int target) {
		List<List<Integer>> res = new ArrayList<>();
		if (target <= 1) {
			return res;
		}
		List<Integer> factors = getFactors(target);
		List<Integer> cur = new ArrayList<>();
		dfs404(target, 0, factors, cur, res);
		return res;
	}

	private void dfs404(int target, int index, List<Integer> factors, List<Integer> cur, List<List<Integer>> res) {
		if (index == factors.size()) {
			if (target == 1) {
				res.add(new ArrayList<>(cur));
			}
			return;
		}
		// Don't add factors[index] at this level
		dfs404(target, index + 1, factors, cur, res);
		// Add factors[index] at this level
		int factor = factors.get(index);
		int count = 0;
		while (target % factor == 0) {
			count++;
			cur.add(factor);
			target /= factor;
			dfs404(target, index + 1, factors, cur, res);
		}
		for (int i = 0; i < count; i++) {
			cur.remove(cur.size() - 1);
		}
	}

	/*
	 * 643. All Permutations of Subsets Given a string with no duplicate characters,
	 * return a list with all permutations of the string and all its subsets.
	 * 
	 * 
	 * 
	 * Examples
	 * 
	 * Set = “abc”, all permutations are [“”, “a”, “ab”, “abc”, “ac”, “acb”, “b”,
	 * “ba”, “bac”, “bc”, “bca”, “c”, “cb”, “cba”, “ca”, “cab”].
	 * 
	 * Set = “”, all permutations are [“”].
	 * 
	 * Set = null, all permutations are [].
	 */
	private List<Integer> getFactors(int target) {
		List<Integer> res = new ArrayList<Integer>();
		/*
		 * for (int i = 2; i <= target / 2; i++) { if (target % i == 0) { res.add(i); }
		 * }
		 */
		for (int i = target / 2; i > 1; i--) {
			if (target % i == 0) {
				res.add(i);
			}
		}
		return res;
	}

	public List<String> allPermutationsOfSubsets(String set) {
		List<String> res = new ArrayList<>();
		char[] arr = set.toCharArray();
		dfs643(arr, 0, res);
		return res;
	}

	private void dfs643(char[] arr, int index, List<String> res) {
		res.add(new String(arr, 0, index));

		for (int i = index; i < arr.length; i++) {
			swap(arr, i, index);
			dfs643(arr, index + 1, res);
			swap(arr, i, index);
		}
	}

	private void swap(char[] arr, int a, int b) {
		char temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	/*
	 * 179. All Valid Permutations Of Parentheses II Get all valid permutations of l
	 * pairs of (), m pairs of <> and n pairs of {}.
	 * 
	 * Assumptions
	 * 
	 * l, m, n >= 0 l + m + n > 0 Examples
	 * 
	 * l = 1, m = 1, n = 0, all the valid permutations are ["()<>", "(<>)", "<()>",
	 * "<>()"]
	 */
	private static final char[] ps = new char[] {'(', ')', '<', '>', '{', '}'};

	  public List<String> validParentheses(int l, int m, int n) {
	    // Assumptions: l, m, n >= 0
	    int[] remain = new int[] {l, l, m, m, n, n};
	    int targetLen = 2 * l + 2 * m + 2 * n;
	    StringBuilder cur = new StringBuilder();
	    Deque<Character> stack = new ArrayDeque<>();
	    List<String> res = new ArrayList<>();
	    dfs179(cur, stack, remain, targetLen, res);
	    return res;
	  }
	  private void dfs179(StringBuilder cur, Deque<Character> stack, int[] remain, int targetLen, List<String> res) {
	    if (cur.length() == targetLen) {
	      res.add(cur.toString());
	      return;
	    }
	    for (int i = 0; i < remain.length; i++) {
	      if (i % 2 == 0) {
	        if (remain[i] > 0) {
	          cur.append(ps[i]);
	          stack.offerFirst(ps[i]);
	          remain[i]--;
	          dfs179(cur, stack, remain, targetLen, res);
	          cur.deleteCharAt(cur.length() - 1);
	          stack.pollFirst();
	          remain[i]++;
	        }
	      } else {
	        if (!stack.isEmpty() && stack.peekFirst() == ps[i - 1]) {
	          cur.append(ps[i]);
	          stack.pollFirst();
	          remain[i]--;
	          dfs179(cur, stack, remain, targetLen, res);
	          cur.deleteCharAt(cur.length() - 1);
	          stack.offerFirst(ps[i - 1]);
	          remain[i]++;
	        }
	      }
	    }
	  }
}
