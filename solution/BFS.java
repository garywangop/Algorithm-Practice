package solution;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class BFS {
	public static void main (String[] args) {
		BFS test = new BFS();
		int[][] arr = {{1,2,3,4}, {11,12,13,14}, {15,16,17,18}, {19,20,21,22}};
		int j = test.kthSmallest(arr, 8);
		System.out.println(j);

		
	}
	
	// K-th smallest in unsorted array
	// Assumptions: 1. array is not null; 2. k >= 0 and k <= array.length
	public int[] kSmallest(int[] array, int k) {
		if (array.length == 0 || k == 0) {
			return new int[0];
		}
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer> (k, Collections.reverseOrder());
		for (int i = 0; i < array.length; i++) {
			if (i < k) {
				maxHeap.offer(array[i]);
			} else if (array[i] < maxHeap.peek()) {
				maxHeap.poll();
				maxHeap.offer(array[i]);
			}
		}
		int[] res = new int[k];
		for (int i = k - 1; i >= 0; i--) {
			res[i] = maxHeap.poll();
		}
		return res;
	}
	
	// Get Keys In Binary Tree Layer By Layer
	public List<List<Integer>> layerByLayer(TreeNode root) {
	   List<List<Integer>> res = new ArrayList<List<Integer>>();
	   if (root == null) {
		   return res;
	   }
	   Queue<TreeNode> q = new ArrayDeque<TreeNode>();
	   q.offer(root);
	   while (!q.isEmpty()) {
		  int size = q.size();
		  List<Integer> curLayer = new ArrayList<Integer>();
		  for (int i = 0; i < size; i++) {
			  TreeNode cur = q.poll();
			  curLayer.add(cur.value);
			  if (cur.left != null) {
				  q.offer(cur.left);
			  }
			  if (cur.right != null) {
				  q.offer(cur.right);
			  }
		  }
		  res.add(curLayer);
	   }
	   return res;
	  }
	
	// Check if binary tree is completed
	public boolean isCompleted(TreeNode root) {
		if (root == null) {
			return true;
		}
		Queue<TreeNode> q = new ArrayDeque<TreeNode>();
		q.offer(root);
		boolean flag = false;
		while (!q.isEmpty()) {
			TreeNode cur = q.poll();
			if (cur.left == null) {
				flag = true;
			} else if (flag) {
				return false;
			} else { // cur.left != null && flag == false
				q.offer(cur.left);
			}
			if (cur.right == null) {
				flag = true;
			} else if (flag) {
				return false;
			} else {
				q.offer(cur.right);
			}
		}
		return true;
	}
	
	// K-th smallest in sorted Matrix
	public int kthSmallest (int[][] matrix, int k) {
		int row = matrix.length;
		int col = matrix[0].length;
		PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>() {
			@ Override
			public int compare(Cell c1, Cell c2) {
				if (c1.value == c2.value) {
					return 0;
				}
				return c1.value < c2.value ? -1 : 1;
			}
		});
		boolean[][] flag = new boolean[row][col];
		minHeap.offer(new Cell(0, 0, matrix[0][0]));
		flag[0][0] = true;
		for (int i = 0; i < k - 1; i++) {
			Cell cur = minHeap.poll();
		      if (cur.row + 1 < row && !flag[cur.row + 1][cur.col]) {
		        minHeap.offer(new Cell(cur.row + 1, cur.col, matrix[cur.row + 1][cur.col]));
		        flag[cur.row + 1][cur.col] = true;
		      }
		      if (cur.col + 1 < col && !flag[cur.row][cur.col + 1]) {
		        minHeap.offer(new Cell(cur.row, cur.col + 1, matrix[cur.row][cur.col + 1]));
		        flag[cur.row][cur.col + 1] = true;
		      }
			
		}
		return minHeap.peek().value;
	}
	
	static class Cell {
		int row;
		int col;
		int value;
		Cell (int row, int col, int value) {
			this.row = row;
			this.col = col;
			this.value = value;
		}
	}

}
