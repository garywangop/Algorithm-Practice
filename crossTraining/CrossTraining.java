package crossTraining;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class CrossTraining {
	public static void main(String args[]) {
		CrossTraining sol = new CrossTraining();
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 1 };
		List<Integer> res = sol.maxWindows2(arr, 2);
		System.out.println("Original: ");
		System.out.println("1,2,3,4,5,6,7,8,9,1,1");
		System.out.println("Actual: ");
		for (int i : res) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println("Expected: ");
		System.out.println("2,3,4,5,6,7,8,9,9,1");
	}

	// Deep Copy Undirected Graph
	// Solution 1: DFS
	public List<GraphNode> copyDFS(List<GraphNode> graph) {
		if (graph == null) {
			return null;
		}
		Map<GraphNode, GraphNode> map = new HashMap<>();
		for (GraphNode node : graph) {
			if (!map.containsKey(node)) {
				map.put(node, new GraphNode(node.key));
				DFS(node, map);
			}
		}
		return new ArrayList<GraphNode>(map.values());
	}

	private void DFS(GraphNode seed, Map<GraphNode, GraphNode> map) {
		GraphNode copy = map.get(seed);
		for (GraphNode nei : seed.neighbors) {
			if (!map.containsKey(nei)) {
				map.put(nei, new GraphNode(nei.key));
				DFS(nei, map);
			}
			copy.neighbors.add(map.get(nei));
		}
	}

	// Solution 2:
	// BFS
	public List<GraphNode> copyBFS(List<GraphNode> graph) {
		// Write your solution here.
		Map<GraphNode, GraphNode> map = new HashMap<>();
		List<GraphNode> retval = new ArrayList<>();
		for (GraphNode g : graph) {
			if (!map.containsKey(g)) {
				GraphNode newNode = new GraphNode(g.key);
				map.put(g, newNode);
				BFS(g, map);
			}
			retval.add(map.get(g));
		}
		return retval;
	}

	public void BFS(GraphNode source, Map<GraphNode, GraphNode> map) {
		Queue<GraphNode> q = new ArrayDeque<>();
		q.offer(source);
		while (!q.isEmpty()) {
			GraphNode curr = q.poll();
			// GraphNode copy = map.get(curr);
			if (!map.containsKey(curr)) {
				GraphNode newNode = new GraphNode(curr.key);
				map.put(curr, newNode);
			}
			GraphNode copy = map.get(curr);
			for (GraphNode neigh : curr.neighbors) {
				if (!map.containsKey(neigh)) {
					GraphNode newNode = new GraphNode(neigh.key);
					map.put(neigh, newNode);
					q.offer(neigh);
				}
				copy.neighbors.add(map.get(neigh));
			}
		}
	}

	// Merge K Sorted Array
	public int[] merge(int[][] arrayOfArrays) {
		PriorityQueue<Entry> minHeap = new PriorityQueue<>(new myComparator());
		int length = 0;
		for (int i = 0; i < arrayOfArrays.length; i++) {
			int[] array = arrayOfArrays[i];
			length += array.length;
			if (array.length != 0) {
				minHeap.offer(new Entry(i, 0, array[0]));
			}
		}

		int[] res = new int[length];
		int cur = 0;
		while (!minHeap.isEmpty()) {
			Entry temp = minHeap.poll();
			res[cur++] = temp.value;
			if (temp.col + 1 < arrayOfArrays[temp.row].length) {
				temp.col++;
				temp.value = arrayOfArrays[temp.row][temp.col];
				minHeap.offer(temp);
			}
		}
		return res;
	}

	static class Entry {
		int row;
		int col;
		int value;

		Entry(int row, int col, int value) {
			this.row = row;
			this.col = col;
			this.value = value;
		}
	}

	static class myComparator implements Comparator<Entry> {
		@Override
		public int compare(Entry e1, Entry e2) {
			if (e1.value == e2.value) {
				return 0;
			}
			return e1.value < e2.value ? -1 : 1;
		}
	}

	// 12/05 汤sir讲座
	// 国际象棋的马按照手机拨号键盘跳N步有多少种跳发
	public int knightDialer(int N) {
		int[] count = { 0 };
		int[][] neighbors = {};
		for (int pos = 0; pos < 10; pos++) {
			dfs(pos, N - 1, neighbors, count);
		}
		return count[0];
	}

	private void dfs(int pos, int hops, int[][] neighbors, int[] count) {
		// base case
		if (hops == 0) {
			count[0]++;
			return;
		}
		// recursive rule
		for (int nextPos : neighbors[pos]) {
			dfs(nextPos, hops - 1, neighbors, count);
		}
	}

	// 3Sum
	public List<List<Integer>> allTriples(int[] array, int target) {
		// Assumptions: array is not null, array.length >= 3.
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(array);
		for (int i = 0; i < array.length - 2; i++) {
			// Our goal is to find i < j < k, such that
			// array[i] + array[j] + array[k] == target,
			// To make sure there is no duplicate tuple,
			// we ignore all the duplicate possible i.
			// e.g, if we have 2, 2, 2, only the first 2 will be selected as i.
			if (i > 0 && array[i] == array[i - 1]) {
				continue;
			}
			int left = i + 1;
			int right = array.length - 1;
			while (left < right) {
				int temp = array[left] + array[right];
				if (temp + array[i] == target) {
					res.add(Arrays.asList(array[i], array[left], array[right]));
					left++;
					// Ignore all possible duplicate j as well.
					while (left < right && array[left] == array[left - 1]) {
						left++;
					}
				} else if (temp + array[i] < target) {
					left++;
				} else {
					right--;
				}
			}
		}
		return res;
	}

	/*
	 * 198. Largest Rectangle In Histogram Given a non-negative integer array
	 * representing the heights of a list of adjacent bars. Suppose each bar has a
	 * width of 1. Find the largest rectangular area that can be formed in the
	 * histogram.
	 * 
	 * Assumptions
	 * 
	 * The given array is not null or empty Examples
	 * 
	 * { 2, 1, 3, 3, 4 }, the largest rectangle area is 3 * 3 = 9(starting from
	 * index 2 and ending at index 4)
	 */
	public int largest(int[] array) {
		// Assumptions: array is not null, array.length >= 1
		// all the values in the array are non-negative.
		int res = 0;
		// Note that the stack contains the "index",
		// not the "value" of the array.
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i <= array.length; i++) {
			// We need a way of popping out all the elements in the stack
			// at last, so that we explicitly add a bar of height 0.
			int cur = i == array.length ? 0 : array[i];
			while (!stack.isEmpty() && array[stack.peekFirst()] >= cur) {
				int height = array[stack.pollFirst()];
				// Determine the left boundary of the largest rectangle with height array[i]
				int left = stack.isEmpty() ? 0 : stack.peekFirst() + 1;
				// Determine the right boundary of the largest rectangle
				// with height of the poped element.
				res = Math.max(res, height * (i - left));
			}
			stack.offerFirst(i);
		}
		return res;
	}

	/*
	 * 199. Max Water Trapped I Given a non-negative integer array representing the
	 * heights of a list of adjacent bars. Suppose each bar has a width of 1. Find
	 * the largest amount of water that can be trapped in the histogram.
	 * 
	 * Assumptions
	 * 
	 * The given array is not null Examples
	 * 
	 * { 2, 1, 3, 2, 4 }, the amount of water can be trapped is 1 + 1 = 2 (at index
	 * 1, 1 unit of water can be trapped and index 3, 1 unit of water can be
	 * trapped)
	 */
	public int maxTrapped1(int[] array) {
		// Assumptions: array is not null.
		if (array.length == 0) {
			return 0;
		}
		int left = 0;
		int right = array.length - 1;
		int res = 0;
		int lmax = array[left];
		int rmax = array[right];
		while (left < right) {
			if (array[left] <= array[right]) {
				res += Math.max(0, lmax - array[left]);
				lmax = Math.max(lmax, array[left]);
				left++;
			} else {
				res += Math.max(0, rmax - array[right]);
				rmax = Math.max(rmax, array[right]);
				right--;
			}
		}
		return res;
	}

	public int maxTrapped2(int[] array) {
		// Assumptions: array is not null.
		if (array.length == 0) {
			return 0;
		}
		int[] left = new int[array.length];
		left[0] = array[0];
		int[] right = new int[array.length];
		right[array.length - 1] = array[array.length - 1];
		for (int i = 1; i < array.length; i++) {
			left[i] = Math.max(left[i - 1], array[i]);
		}
		for (int i = array.length - 2; i >= 0; i--) {
			right[i] = Math.max(right[i + 1], array[i]);
		}
		int res = 0;
		for (int i = 1; i < array.length - 1; i++) {
			if (array[i] >= Math.min(left[i], right[i])) {
				continue;
			} else {
				res += Math.min(left[i], right[i]) - array[i];
			}
		}
		return res;
	}

	/*
	 * 204. Maximum Values Of Size K Sliding Windows Given an integer array A and a
	 * sliding window of size K, find the maximum value of each window as it slides
	 * from left to right.
	 * 
	 * Assumptions
	 * 
	 * The given array is not null and is not empty
	 * 
	 * K >= 1, K <= A.length
	 * 
	 * Examples
	 * 
	 * A = {1, 2, 3, 2, 4, 2, 1}, K = 3, the windows are {{1,2,3}, {2,3,2}, {3,2,4},
	 * {2,4,2}, {4,2,1}},
	 * 
	 * and the maximum values of each K-sized sliding window are [3, 3, 4, 4, 4]
	 */
	public List<Integer> maxWindows1(int[] array, int k) {
		// Assumptions: array is not null or not empty,
		// k >= 1 and k <= a.length
		List<Integer> max = new ArrayList<Integer>();
		// Use a descending deque to solve this problem,
		// we store the index instead of the actual value in the deque,
		// and we make sure:
		// 1. the deque only contains index in the current sliding window.
		// 2. for any index, the previous index with smaller value is
		// discarded from the deque.
		Deque<Integer> deque = new ArrayDeque<Integer>();
		for (int i = 0; i < array.length; i++) {
			// discard any index with smaller value than index i.
			while (!deque.isEmpty() && array[deque.peekLast()] <= array[i]) {
				deque.pollLast();
			}
			// It is possible the head element is out of the current
			// sliding window so we might need to discard it as well.
			if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
				deque.pollFirst();
			}
			deque.offerLast(i);
			if (i >= k - 1) {
				max.add(array[deque.peekFirst()]);
			}
		}
		return max;
	}

	public List<Integer> maxWindows2(int[] array, int k) {
		PriorityQueue<Pair> maxHeap = new PriorityQueue<>(k, new newComparator());
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			maxHeap.offer(new Pair(i, array[i]));
		}
		res.add(maxHeap.peek().value);
		for (int i = k; i < array.length; i++) {
			maxHeap.offer(new Pair(i, array[i]));
			while (maxHeap.peek().index < i - k + 1) {
				maxHeap.poll();
			}
			res.add(maxHeap.peek().value);
		}
		return res;
	}

	static class Pair {
		int index;
		int value;

		Pair(int index, int value) {
			this.index = index;
			this.value = value;
		}
	}

	static class newComparator implements Comparator<Pair> {
		@Override
		public int compare(Pair p1, Pair p2) {
			if (p1.value == p2.value) {
				return 0;
			}
			return p1.value > p2.value ? -1 : 1;
		}
	}
}
