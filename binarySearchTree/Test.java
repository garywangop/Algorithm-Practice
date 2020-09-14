package binarySearchTree;

import java.util.Deque;
import java.util.PriorityQueue;
import java.util.ArrayDeque;
import java.util.Comparator;

import solution.TreeNode;

public class Test {

	public static void main(String[] args) {
		
		Test sol = new Test();
	}

	public int kthSmallest(int[][] matrix, int k) {
		int row = matrix.length;
		int col = matrix[0].length;
		PriorityQueue<Cell> minHeap = new PriorityQueue<>(k, new Comparator<Cell>() {
			@Override
			public int compare(Cell c1, Cell c2) {
				if (c1.value == c2.value) {
					return 0;
				}
				return c1.value < c2.value ? -1 : 1;
			}
		});
		minHeap.offer(new Cell(0, 0, matrix[0][0]));
		boolean[][] visited = new boolean[row][col];
		visited[0][0] = true;
		for (int i = 0; i < k - 1; i++) {
			Cell cur = minHeap.poll();
			if (cur.row + 1 < row && !visited[cur.row + 1][cur.col]) {
				minHeap.offer(new Cell(cur.row + 1, cur.col, matrix[cur.row + 1][cur.col]));
				visited[cur.row + 1][cur.col] = true;
			}
			if (cur.col + 1 < col && !visited[cur.row][cur.col + 1]) {
				minHeap.offer(new Cell(cur.row, cur.col + 1, matrix[cur.row][cur.col + 1]));
				visited[cur.row][cur.col + 1] = true;
			}
		}
		return minHeap.poll().value;
	}

	static class Cell {
		int row;
		int col;
		int value;

		public Cell(int row, int col, int value) {
			this.row = row;
			this.col = col;
			this.value = value;
		}
	}

}
