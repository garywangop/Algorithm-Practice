package crossTraining;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class CrossTraining {
	public static void main(String args[]) {
		CrossTraining sol = new CrossTraining();
		int[][] arr = {{3}, {1,2,3,4,5}};
		int[] arr1 = sol.merge(arr);
		for (int i : arr1) {
			System.out.print(i + " ");
		}
		System.out.print(arr);
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
		Entry (int row, int col, int value) {
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
}
