package bfs;

import java.util.Collections;
import java.util.PriorityQueue;

public class BFS {

	public static void main(String[] args) {
		System.out.println("aab");

	}
	
	/*
	 * 25. K Smallest In Unsorted Array
	 * 
	 * Find the K smallest numbers in an unsorted integer array A. The returned numbers should be in ascending order.

Assumptions

A is not null
K is >= 0 and smaller than or equal to size of A
Return

an array with size K containing the K smallest numbers in ascending order
Examples

A = {3, 4, 1, 2, 5}, K = 3, the 3 smallest numbers are {1, 2, 3}
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

}
