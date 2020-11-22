package leetcode;

import java.util.*;

public class CarPooling {

	public static void main(String[] args) {
		CarPooling sol = new CarPooling();
		
		int[][] trips = new int[][] {
				{3,2,8},{4,4,6},{10,8,9}
		};
		System.out.println(sol.carPooling(trips, 11));
	}

	// 1094
	
	// trip[i] = [num_passengers, start_location, end_location]
	public boolean carPooling(int[][] trips, int capacity) {
		//Arrays.sort(trips, new MyComparator1());
		Arrays.sort(trips, (e1, e2) -> e1[1] - e2[1]);
		//PriorityQueue<int[]> minHeap = new PriorityQueue<>(new MyComparator2());
		PriorityQueue<int[]> minHeap = new PriorityQueue<>((e1, e2) -> e1[2] - e2[2]);
		minHeap.offer(trips[0]);
		int cur = trips[0][0];
		if (cur > capacity) {
			return false;
		}
		
		for (int i = 1; i < trips.length; i++) {
			int[] prev = minHeap.peek();
			// Compare the trips[i] start time to prev[2] end time, and trips[i] has to be added to the minHeap
			// cur += trips[i][0]
			// If trips[i][1] >= prev[2],then prev has to be popped out from minHeap
			// cur -= prev[0]
			// Otherwise, check if cur > capacity. If not, continue. If it is, return false
			cur += trips[i][0];
			while (!minHeap.isEmpty() && minHeap.peek()[2] <= trips[i][1]) {
				cur -= minHeap.peek()[0];
				minHeap.poll();
			}
			minHeap.offer(trips[i]);
			if (cur > capacity) {
				return false;
			}
		}
		return true;
	}
	
	// Sort by start time
	public class MyComparator1 implements Comparator<int[]> {
		@Override
		public int compare(int[] e1, int[] e2) {
			if (e1[1] == e2[1]) {
				return e1[2] < e2[2] ? -1 : 1;
			}
			return e1[1] < e2[1] ? -1 : 1;
		}
	}

	// Sort by end time
	public class MyComparator2 implements Comparator<int[]> {
		@Override
		public int compare(int[] e1, int[] e2) {
			if (e1[2] == e2[2]) {
				return 0;
			}
			return e1[2] < e2[2] ? -1 : 1;
		}
	}
}
