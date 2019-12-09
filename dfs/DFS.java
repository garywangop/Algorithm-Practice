package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFS {
	public static void main(String args[]) {
		DFS sol = new DFS();
		List<String> res = sol.subSets("aa");
		System.out.print(res);
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
}
