package bfs;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Comparator;

public class BFS {

	public static void main(String[] args) {
		int[] A = new int[] {1,3,5};
		int[] B = new int[] {4,8};
		System.out.println(kthSum(A, B, 6));

	}

	/*
	 * 25. K Smallest In Unsorted Array
	 * 
	 * Find the K smallest numbers in an unsorted integer array A. The returned
	 * numbers should be in ascending order.
	 * 
	 * Assumptions
	 * 
	 * A is not null K is >= 0 and smaller than or equal to size of A Return
	 * 
	 * an array with size K containing the K smallest numbers in ascending order
	 * Examples
	 * 
	 * A = {3, 4, 1, 2, 5}, K = 3, the 3 smallest numbers are {1, 2, 3}
	 */
	public int[] kSmallest(int[] arr, int k) {
		if (arr.length == 0 || k == 0) {
			return new int[0];
		}
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
		for (int i = 0; i < arr.length; i++) {
			if (i < k) {
				maxHeap.offer(arr[i]);
			} else if (arr[i] < maxHeap.peek()) {
				maxHeap.poll();
				maxHeap.offer(arr[i]);
			}
		}
		int[] res = new int[k];
		for (int i = k - 1; i >= 0; i--) {
			res[i] = maxHeap.poll();
		}
		return res;
	}

	/*
	 * 27. Kth Smallest Sum In Two Sorted Arrays
	 * 
	 * Given two sorted arrays A and B, of sizes m and n respectively. Define s = a
	 * + b, where a is one element from A and b is one element from B. Find the Kth
	 * smallest s out of all possible s'.
	 * 
	 * Assumptions
	 * 
	 * A is not null and A is not of zero length, so as B K > 0 and K <= m * n
	 * Examples
	 * 
	 * A = {1, 3, 5}, B = {4, 8}
	 * 
	 * 1st smallest s is 1 + 4 = 5 2nd smallest s is 3 + 4 = 7 3rd, 4th smallest s
	 * are 9 (1 + 8, 4 + 5) 5th smallest s is 3 + 8 = 11
	 */
	
	public static int kthSum(int[] A, int[] B, int k) {
		boolean[][] visited = new boolean[A.length][B.length];
		PriorityQueue<Pair> minHeap = new PriorityQueue<>(k, new Comparator<Pair>() {
			@Override
			public int compare(Pair e1, Pair e2) {
				if (e1.value == e2.value) {
					return 0;
				}
				return e1.value < e2.value ? -1: 1;
			}
		});
		minHeap.offer(new Pair(0, 0, A[0] + B[0]));
		visited[0][0] = true;
		for (int i = 1; i < k; i++) {
			Pair cur = minHeap.poll();
			int row = cur.row;
			int col = cur.col;
			if (row + 1 < A.length && !visited[row + 1][col]) {
				minHeap.offer(new Pair(row + 1, col, A[row + 1] + B[col]));
				visited[row + 1][col] = true;
			}
			if (col + 1 < B.length && !visited[row][col + 1]) {
				minHeap.offer(new Pair(row, col + 1, A[row] + B[col + 1]));
				visited[row][col + 1] = true;
			}
		}
		return minHeap.peek().value;
	}
		
	
	static class Pair {
		int row;
		int col;
		int value;
		public Pair(int row, int col, int value) {
			this.row = row;
			this.col = col;
			this.value = value;
		}
	}

}
