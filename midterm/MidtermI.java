package midterm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MidtermI {
	public static void main (String[] args) {
		MidtermI test = new MidtermI();
		String input = "aab";
		test.permutation(input);
		
		int a = 0;
		System.out.println(++a);
		System.out.println(++a);
		
	}
	
	// Permutation
	public void permutation (String input) {
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
	
	// Two sorted array A & B
	
}
