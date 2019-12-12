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
		String[] str = sol.combinations272(123);
		for (String s : str) {
			System.out.println(s);
		}

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
	private static final char[] ps = new char[] { '(', ')', '<', '>', '{', '}' };

	public List<String> validParentheses(int l, int m, int n) {
		// Assumptions: l, m, n >= 0
		int[] remain = new int[] { l, l, m, m, n, n };
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

	/*
	 * 642. All Valid Permutations Of Parentheses III Get all valid permutations of
	 * l pairs of (), m pairs of <> and n pairs of {}, subject to the priority
	 * restriction: {} higher than <> higher than ().
	 * 
	 * 
	 * 
	 * Assumptions
	 * 
	 * l, m, n >= 0
	 * 
	 * l + m + n > 0
	 * 
	 * 
	 * 
	 * Examples
	 * 
	 * l = 1, m = 1, n = 0, all the valid permutations are ["()<>", "<()>", "<>()"].
	 * 
	 * l = 2, m = 0, n = 1, all the valid permutations are [“()(){}”, “(){()}”,
	 * “(){}()”, “{()()}”, “{()}()”, “{}()()”].
	 */

	/*
	 * 641. All Subsets II of Size K Given a set of characters represented by a
	 * String, return a list containing all subsets of the characters whose size is
	 * K. Notice that each subset returned will be sorted for deduplication.
	 * 
	 * 
	 * 
	 * Assumptions
	 * 
	 * There could be duplicate characters in the original set.
	 * 
	 * ​
	 * 
	 * Examples
	 * 
	 * Set = "abc", K = 2, all the subsets are [“ab”, “ac”, “bc”].
	 * 
	 * Set = "abb", K = 2, all the subsets are [“ab”, “bb”].
	 * 
	 * Set = "abab", K = 2, all the subsets are [“aa”, “ab”, “bb”].
	 * 
	 * Set = "", K = 0, all the subsets are [""].
	 * 
	 * Set = "", K = 1, all the subsets are [].
	 * 
	 * Set = null, K = 0, all the subsets are [].
	 */
	public List<String> subSetsIIOfSizeK(String set, int k) {
		List<String> res = new ArrayList<>();
		if (set == null) {
			return res;
		}
		char[] arrSet = set.toCharArray();
		Arrays.sort(arrSet);
		StringBuilder sb = new StringBuilder();
		dfs641(arrSet, k, sb, 0, res);
		return res;
	}

	private void dfs641(char[] arrSet, int k, StringBuilder sb, int index, List<String> res) {
		if (sb.length() == k) {
			res.add(sb.toString());
			return;
		}
		if (index == arrSet.length) {
			return;
		}
		// Add arrSet[index] at this level
		dfs641(arrSet, k, sb.append(arrSet[index]), index + 1, res);
		sb.deleteCharAt(sb.length() - 1);
		while (index < arrSet.length - 1 && arrSet[index] == arrSet[index + 1]) {
			index++;
		}
		// Don't add arrSet[index] at this level
		dfs641(arrSet, k, sb, index + 1, res);
	}

	/*
	 * 264. Keep Distance For Identical Elements Given an integer k, arrange the
	 * sequence of integers [1, 1, 2, 2, 3, 3, ...., k - 1, k - 1, k, k], such that
	 * the output integer array satisfy this condition:
	 * 
	 * Between each two i's, they are exactly i integers (for example: between the
	 * two 1s, there is one number, between the two 2's there are two numbers).
	 * 
	 * If there does not exist such sequence, return null.
	 * 
	 * Assumptions:
	 * 
	 * k is guaranteed to be > 0 Examples:
	 * 
	 * k = 3, The output = { 2, 3, 1, 2, 1, 3 }.
	 */
	public int[] keepDistance(int k) {
		// AssumptionS; k > 0
		int[] arr = new int[2 * k];
		for (int i = 0; i < k; i++) {
			arr[i * 2] = i + 1;
			arr[i * 2 + 1] = i + 1;
		}
		// used[i] == true if and only if i is used once
		boolean[] used = new boolean[k + 1];
		return helper264Method1(arr, 0, used) ? arr : null;
	}

	private boolean helper264Method1(int[] arr, int index, boolean[] used) {
		if (index == arr.length) {
			return true;
		}
		for (int i = index; i < arr.length; i++) {
			int cur = arr[i];
			if (!used[cur] || (index > cur && arr[index - cur - 1] == cur)) {
				swap(arr, index, i);
				used[cur] = !used[cur];
				if (helper264Method1(arr, index + 1, used)) {
					return true;
				}
				swap(arr, index, i);
				used[cur] = !used[cur];
			}
		}
		return false;
	}

	private void swap(int[] arr, int left, int right) {
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}

	/*
	 * 272. Combinations For Telephone Pad I Given a telephone keypad, and an int
	 * number, print all words which are possible by pressing these numbers, the
	 * output strings should be sorted.
	 * 
	 * {0 : "", 1 : "", 2 : "abc", 3 : "def", 4 : "ghi", 5 : "jkl", 6 : "mno", 7 :
	 * "pqrs", 8 : "tuv", 9 : "wxyz"}
	 * 
	 * Assumptions:
	 * 
	 * The given number >= 0 Examples:
	 * 
	 * if input number is 231, possible words which can be formed are:
	 * 
	 * [ad, ae, af, bd, be, bf, cd, ce, cf]
	 */
	public String[] combinations272(int number) {
		List<String> res = new ArrayList<>();
		String[] numToChar = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		StringBuilder sb = new StringBuilder();
		dfs272(Integer.toString(number).toCharArray(), numToChar, 0, sb, res);
		return res.toArray(new String[0]);
	}

	private void dfs272(char[] number, String[] numToChar, int level, StringBuilder sb, List<String> res) {
		if (level == number.length) {
			res.add(sb.toString());
			return;
		}
		char[] chars = numToChar[number[level] - '0'].toCharArray();
		if (chars.length == 0) {
			dfs272(number, numToChar, level + 1, sb, res);
		} else {
			for (int i = 0; i < chars.length; i++) {
				dfs272(number, numToChar, level + 1, sb.append(chars[i]), res);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}

	/*
	 * 147. Restore IP Addresses Given a string containing only digits, restore it
	 * by retiring all possible valid IP address combinations.
	 * 
	 * Input: ”25525511135”
	 * 
	 * Output: [“255.255.11.135”, “255.255.111.35”]
	 */
	public List<String> Restore(String ip) {
		List<String> res = new ArrayList<>();
		if (ip == null || ip.length() == 0) {
			return res;
		}
		StringBuilder sb = new StringBuilder();
		dfs147(ip.toCharArray(), 0, 0, sb, res);
		return res;
	}

	private void dfs147(char[] ip, int level, int offset, StringBuilder sb, List<String> res) {
		if (level == 4) {
			if (sb.length() == ip.length + 4) {
				res.add(sb.substring(0, sb.length() - 1));
			}
			return;
		}
		if (offset < ip.length) {
			dfs147(ip, level + 1, offset + 1, sb.append(ip[offset]).append('.'), res);
			sb.delete(sb.length() - 2, sb.length());
		}
		if (offset + 1 < ip.length) {
			char a = ip[offset];
			char b = ip[offset + 1];
			if (a != '0') {
				dfs147(ip, level + 1, offset + 2, sb.append(a).append(b).append('.'), res);
				sb.delete(sb.length() - 3, sb.length());
			}
		}
		if (offset + 2 < ip.length) {
			char a = ip[offset];
			char b = ip[offset + 1];
			char c = ip[offset + 2];
			if (a == '1' || a == '2' && b >= '0' && b <= '4' || a == '2' && b == '5' && c >= '0' && c <= '5') {
				dfs147(ip, level + 1, offset + 3, sb.append(a).append(b).append(c).append('.'), res);
				sb.delete(sb.length() - 4, sb.length());
			}
		}
	}
}
