package midterm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MidtermI {
	public static void main(String[] args) {
		MidtermI test = new MidtermI();
		String input = "aab";
		test.permutation(input);

		int a = 0;
		System.out.println(++a);
		System.out.println(++a);

	}

	// Permutation
	public void permutation(String input) {
		if (input == null || input.length() == 0) {
			return;
		}
		char[] arr = input.toCharArray();
		helper(arr, 0);
	}

	private void helper(char[] arr, int index) {
		// base case
		if (index == arr.length) {
			System.out.println(new String(arr));
			return;
		}
		HashSet<Character> check = new HashSet<>();
		for (int i = index; i < arr.length; i++) {
			if (check.add(arr[i])) {
				swap(arr, i, index);
				helper(arr, index + 1);
				swap(arr, i, index);
			}
		}
	}

	private void swap(char[] arr, int a, int b) {
		char temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	/*
	 * 143. Minimum Cuts For Palindromes Given a string, a partitioning of the
	 * string is a palindrome partitioning if every substring of the partition is a
	 * palindrome. Determine the fewest cuts needed for a palindrome partitioning of
	 * a given string.
	 * 
	 * Assumptions
	 * 
	 * The given string is not null Examples
	 * 
	 * “a | babbbab | bab | aba” is a palindrome partitioning of “ababbbabbababa”.
	 * 
	 * The minimum number of cuts needed is 3.
	 */
	public int minCuts(String input) {
		if (input.length() <= 1) {
			return 0;
		}
		// dp[i] means the minimum cuts at index i for palindromes
		// For example, dp[1] means a cut after index 1(ab|cd)
		// dp[2] means a cut after index 2(abc|d).
		// 该题运用的是左大段+右小段的思想，把右小段看做一个整体
		int[] dp = new int[input.length()];
		dp[0] = 0;
		for (int i = 1; i < input.length(); i++) {
			dp[i] = i;
			if (checkPalindromes(input, 0, i)) {
				dp[i] = 0;
				continue;
			}
			for (int j = 0; j < i; j++) {
				if (checkPalindromes(input, j + 1, i)) {
					dp[i] = Math.min(dp[i], dp[j] + 1);
				}
			}
		}
		return dp[input.length() - 1];
	}

	private boolean checkPalindromes(String s, int i, int j) {
		while (i < j) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
}
