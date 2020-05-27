package review;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class PacificAtlanticFlow {

	public static void main(String[] args) {
		PacificAtlanticFlow sol = new PacificAtlanticFlow();
		int[][] matrix = new int[][] { { 1, 2, 2, 3 }, { 3, 2, 3, 4 }, { 2, 4, 5, 3 }, { 6, 7, 1, 4 } };
		System.out.println(sol.pacificAtlantic(matrix));

	}

	public List<List<Integer>> pacificAtlantic(int[][] matrix) {
		List<List<Integer>> res = new ArrayList<>();
		Queue<int[]> qPacific = new ArrayDeque<>();
		Queue<int[]> qAtlantic = new ArrayDeque<>();
		boolean[][] pacific = new boolean[matrix.length][matrix[0].length];
		boolean[][] atlantic = new boolean[matrix.length][matrix[0].length];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (i == 0 || j == 0) {
					pacific[i][j] = true;
					qPacific.offer(new int[] { i, j });
				}
				if (i == matrix.length - 1 || j == matrix[0].length - 1) {
					atlantic[i][j] = true;
					qAtlantic.offer(new int[] { i, j });
				}
			}
		}

		find(matrix, qAtlantic, atlantic);
		find(matrix, qPacific, pacific);

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (pacific[i][j] && atlantic[i][j]) {
					res.add(Arrays.asList(i, j));
				}
			}
		}
		return res;
	}

	static final int[][] DIRECTIONS = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	private void find(int[][] matrix, Queue<int[]> q, boolean[][] path) {

		while (!q.isEmpty()) {

			int[] cur = q.poll();
			for (int[] dirt : DIRECTIONS) {
				int row = cur[0] + dirt[0];
				int col = cur[1] + dirt[1];
				if (row > matrix.length - 1 || row < 0 || col > matrix[0].length - 1 || col < 0 || path[row][col]
						|| matrix[row][col] < matrix[cur[0]][cur[1]]) {
					continue;
				}
				q.offer(new int[] { row, col });
				path[row][col] = true;

			}

		}

	}

}
