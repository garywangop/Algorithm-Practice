package solution;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		Test sol = new Test();

		int[] arr = new int[] { 2,1 };
		int[] arr1 = sol.sort(arr);
		for (int i : arr1) {
			System.out.print(i + ",");
		}
	}

	private int[] sort(int[] arr) {
		if (arr == null || arr.length <= 1) {
			return arr;
		}
		return helper(arr, 0, arr.length - 1);
		
	}

	private int[] helper(int[] arr, int start, int end) {
		if (start == end) {
			return new int[] {arr[start]};
		}
		int mid = start + (end - start) / 2;
		int[] left = helper(arr, start, mid);
		int[] right = helper(arr, mid + 1, end);
		return merge(left, right);
	}
	
	private int[] merge(int[] left, int[] right) {
		int[] res = new int[left.length + right.length];
		int l = 0;
		int r = 0;
		int k = 0;
		
		while (l < left.length && r < right.length) {
			if (left[l] < right[r]) {
				res[k] = left[l];
				l++;
			} else {
				res[k] = right[r];
				r++;
			}
			k++;
		}
		
		while (l < left.length) {
			res[k] = left[l];
			k++;
			l++;
		} 
		
		while (r < right.length) {
			res[k] = right[r];
			k++;
			r++;
		}
		return res;
	}

	
}
