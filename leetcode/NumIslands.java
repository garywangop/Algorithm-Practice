package leetcode;

import java.util.*;

public class NumIslands {
	
	// 200

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NumIslands sol = new NumIslands();
		char[][]grid = {
		        {'1','1','0','0','0'},
		        {'1','1','0','0','0'},
		        {'0','0','1','0','0'},
		        {'0','0','0','1','1'}
		};
		System.out.println(sol.numIslands(grid));
	}
	
	// DFS
	public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    helper(grid, i, j);
                }
            }
        }
        return res;
    }
	
	private void helper(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[row].length || grid[row][col] == '0') {
            return;
        }
        
        grid[row][col] = '0';
        for (int[] dirc : DIRECTIONS) {
            helper(grid, row + dirc[0], col + dirc[1]);
        }
    }
	
	// BFS
	public int numIslands2(char[][] grid) {
        int res = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == '1') {
					res++;
					helper2(grid, new Pair(i, j));
				}
			}
		}
		return res;
    }
	
	private final int[][] DIRECTIONS = new int[][] {{0,1}, {1,0}, {0,-1},{-1,0}};
	
	private void helper2(char[][] grid, Pair pair) {
		Queue<Pair> q = new ArrayDeque<>();
		q.offer(pair);
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Pair cur = q.poll();
				int row = cur.row;
				int col = cur.col;
				grid[row][col] = '0';
				for (int[] direction : DIRECTIONS) {
					int r = row + direction[0];
					int c = col + direction[1];
					if (r >= 0 && r < grid.length && c >= 0 && c < grid[r].length && grid[r][c] == '1') {
						q.offer(new Pair(r, c));
					}
				}
			}
		}
	}
	
	class Pair {
		int row; 
		int col;
		Pair(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

}
