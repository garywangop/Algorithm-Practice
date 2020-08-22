package review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Deque;
import java.util.ArrayDeque;

import solution.ListNode;
import solution.TreeNode;

public class Test {

	public static void main(String[] args) {
		Test sol = new Test();
		int[] arr = new int[] {1,2,0,-1,-2,-3};
		System.out.println(sol.findMid(arr));
		System.out.println(11 % 13);
	}
	/* 0 1 2 3 4 5 6 7 8 9 10 11 12
	 * 1,2,3,4,5,6,7,8,9,7, 3, 2, 1
	 *               l r
	 * if (arr[mid - 1] < arr[mid + 1]) left = mid + 1
	 * if (arr[mid - 1] > arr[mid + 1]) right = mid - 1
	 * 
	 */
	
	public int findMid(int[] arr) {
		int left = 0;
		int right = arr.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (mid == left) {
				return arr[left] > arr[right] ? left : right;
			} else if (arr[mid - 1] < arr[mid] && arr[mid + 1] < arr[mid]) {
				return mid;
			} else if (arr[mid - 1] < arr[mid + 1]) {
				left = mid + 1;
			} else if (arr[mid - 1] > arr[mid + 1]) {
				right = mid - 1;
			}
		}
		return -1;
	}

}
