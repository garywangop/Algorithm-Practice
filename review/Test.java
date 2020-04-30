package review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import solution.ListNode;

public class Test {

	public static void main(String[] args) {
		Test sol = new Test();
		/*
		 * [[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]]
		 */
		//String[][] test = new String[][] {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
		
		
		System.out.println(sol.haveSomeFun(new int[] {1,2,3}));
		String s1 = "abc";
		String s2 = "abd";
		System.out.println(s1.compareTo(s2));
		
	}
	
	private class MaxComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer i1, Integer i2) {
			if (i1.equals(i2)) {
				return 0;
			}
			return i1 > i2 ? -1 : 1;
		}
	}
	
	private class MinComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer e1, Integer e2) {
			if (e1.equals(e2)) {
				return 0;
			}
			return e1 < e1 ? -1 : 1;
		}
	}
	
	public int haveSomeFun(int[] arr) {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MinComparator());
		for (int i : arr) {
			maxHeap.offer(i);
		}
		return maxHeap.poll();
	}

}
