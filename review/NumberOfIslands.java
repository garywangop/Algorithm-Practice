package review;

import java.util.Queue;
import java.util.ArrayDeque;

public class NumberOfIslands {

	public static void main(String[] args) {
		char[][] grid = new char[][] {{'1','1','1','1','1'}, {'1','0','1','0','1'}, {'1','1','1','1','1'}} ;
		NumberOfIslands test = new NumberOfIslands();
		System.out.println(test.numIslands(grid));
	}
	
	// Time: both DFS and BFS are O(row * col)
	// Space: 
	// BFS: O(min(n, m))
	// DFS: worst case is grid map filled with '1', O(row * col)
	
	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == '1') {
					count++;
					//checkIsland(grid, i, j);
					bfs(grid, new Point(i, j));
				}
			}
		}
		return count;
	}
	
	// DFS solution
	private void checkIsland(char[][] grid, int row, int col) {
		if (row < 0 || row >= grid.length || col < 0 || col >= grid[row].length || grid[row][col] == '0') {
			return;
		}
		grid[row][col] = '0';
		checkIsland(grid, row + 1, col); // down
		checkIsland(grid, row - 1, col); // up
		checkIsland(grid, row, col + 1); // right
		checkIsland(grid, row, col - 1); // left
	}
	
	// BFS solution
	class Point {
		  int row;
		  int col;
		  public Point(int row, int col) {
			  this.row = row;
			  this.col = col;
			}
		}
	
	private void bfs(char[][] grid, Point start) {
	    Queue<Point> queue = new ArrayDeque<>();
	    int[] dirX = new int[]{1, 0, -1, 0};
	    int[] dirY = new int[]{0, 1, 0, -1};
		  
	    queue.offer(start);
	    while (!queue.isEmpty()) {
	      Point curr = queue.poll();
	      grid[curr.row][curr.col] = '0';  // this is belong to island, we mark as visted
	      for (int i = 0; i < dirX.length; i++) {
	        int newRow = curr.row + dirX[i];
	        int newCol = curr.col + dirY[i];
	        if (inBound(newRow, newCol, grid) && grid[newRow][newCol] == '1') {
	          queue.offer(new Point(newRow, newCol));
	        }
	      }
	    }
	  }
	  
	  private boolean inBound(int row, int col, char[][] grid) {
	    return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
	  }

}
