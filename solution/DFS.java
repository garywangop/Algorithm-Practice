package solution;

import java.util.ArrayList;
import java.util.List;

public class DFS {
	public static void main(String[] args) {
		DFS test = new DFS();
		String q4 = "aab";
		List<String> res = new ArrayList<String>();
		res = test.permutations(q4);
		System.out.println(res);
		
	}

	// Q1: all subsets I
	// Given a set of characters represented by a String, return a list containing
	// all subsets of the characters
	public List<String> subSets(String set) {
		List<String> res = new ArrayList<String>();
		if (set == null) {
			return res;
		}
		char[] arraySet = set.toCharArray();
		StringBuilder sb = new StringBuilder();
		helperQ1(arraySet, sb, 0, res);
		return res;
	}

	private void helperQ1(char[] set, StringBuilder sb, int index, List<String> res) {
		if (index == set.length) {
			res.add(sb.toString());
			return;
		}
		// case 1: 每层index不加set里的element
		helperQ1(set, sb, index + 1, res);
		// case 2: add element from set in each index level
		helperQ1(set, sb.append(set[index]), index + 1, res);
		sb.deleteCharAt(sb.length() - 1);
	}

	// Q2: all valid permutations of parentheses
	// Given N pairs of parentheses "()", return a list with all the valid
	// permutations
	// M1:
	public List<String> validParenthesesM1(int k) {
		List<String> res = new ArrayList<String>();
		char[] cur = new char[k * 2];
		helperQ2M1(cur, k, k, 0, res);
		return res;
	}

	private void helperQ2M1(char[] cur, int left, int right, int index, List<String> res) {
		if (left == 0 && right == 0) {
			res.add(new String(cur));
			return;
		}
		// case 1: add '('
		if (left > 0) {
			cur[index] = '(';
			helperQ2M1(cur, left - 1, right, index + 1, res);
		}
		if (right > left) {
			cur[index] = ')';
			helperQ2M1(cur, left, right - 1, index + 1, res);
		}
	}

	// M2:
	public List<String> validParenthesesM2(int k) {
		List<String> res = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		helperQ2M2(k, 0, 0, sb, res);
		return res;
	}

	private void helperQ2M2(int n, int left, int right, StringBuilder sb, List<String> res) {
		if (left == n && right == n) {
			res.add(sb.toString());
			return;
		}
		// case 1: add '('
		if (left < n) {
			helperQ2M2(n, left + 1, right, sb.append('('), res);
			sb.deleteCharAt(sb.length() - 1);
		}
		// case 2: add ')'
		if (right < left) {
			helperQ2M2(n, left, right + 1, sb.append(')'), res);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	// Q3: combinations of coins
	// Given a number of different denominations of coins, get all the possible ways
	// to pay a target number of cents
	public List<List<Integer>> combinations(int target, int[] coins) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> cur = new ArrayList<Integer>();
		helperQ3(target, coins, 0, cur, res);
		return res;
	}

	private void helperQ3(int target, int[] coins, int index, List<Integer> cur, List<List<Integer>> res) {
		// base case: stop at index == coins.length - 1 level
		if (index == coins.length - 1) {
			if (target % coins[coins.length - 1] == 0) {
				cur.add(target / coins[coins.length - 1]);
				res.add(new ArrayList<Integer>(cur));
				cur.remove(cur.size() - 1);
			}
			return;
		}
		// Recursion rule:
		int max = target / coins[index];
		for (int i = 0; i <= max; i++) {
			cur.add(i);
			helperQ3(target - i * coins[index], coins, index + 1, cur, res);
			cur.remove(cur.size() - 1);
		}
	}

	// Q4: all permutations I
	// Given a string with no duplicate characters, return a list with all
	// permutations of the characters
	public List<String> permutations(String input) {
		List<String> res = new ArrayList<String>();
		if (input == null) {
			return res;
		}
		char[] array = input.toCharArray();
		helperQ4(array, 0, res);
		return res;
	}
	private void helperQ4(char[] arr, int index, List<String> res) {
		// Base case and terminate condition:
		if (index == arr.length) {
			res.add(new String(arr));
			return;
		}
		// Recursion:
		for (int i = index; i < arr.length; i++) {
			swap(arr, index, i);
			helperQ4(arr, index + 1, res);
			swap(arr, index, i);
		}
	}
	private void swap(char[] arr, int a, int b) {
		char temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
