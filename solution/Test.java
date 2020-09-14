package solution;

import java.util.*;

public class Test {

	public static void main(String[] args) {
		Test sol = new Test();
		
		
		System.out.println(sol.shift("abcdefg", 39));
		
	}
	
	public String shift(String s, int n) {
		int size = s.length();
		int shift = n % size;
		char[] arr = s.toCharArray();
		reverse(arr, 0, size - 1);
		reverse(arr, 0, shift - 1);
		reverse(arr, shift, size - 1);
		return new String(arr, 0, size);
	}
	
	private void reverse(char[] arr, int start, int end) {
		while (start < end) {
			char temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
	}
}
