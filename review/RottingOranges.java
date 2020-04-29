package review;

import java.util.Queue;
import java.util.ArrayDeque;

public class RottingOranges {

	public static void main(String[] args) {
		RottingOranges sol = new RottingOranges();
		int[][] test = new int[][] {{2,1,1},{0,1,1},{1,0,1}};
		System.out.println(sol.orangesRotting(test));

	}

	public int orangesRotting(int[][] grid) {
        // Use count to track how many 1s are in the matrix
        int count = 0;
        // Use queue to perform BFS
        Queue<Pair> q = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    count++;
                } else if (grid[i][j] == 2) {
                    q.offer(new Pair(i, j));
                }
            }
        }
        int time = 0;
        int[][] directions = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        while (!q.isEmpty()) {
            time++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair cur = q.poll();
                for (int[] dirc : directions) {
                    Pair temp = new Pair(cur.row, cur.col);
                    temp.row = cur.row + dirc[0];
                    temp.col = cur.col + dirc[1];
                    if (temp.row < 0 || temp.row > grid.length - 1 
                        || temp.col < 0 || temp.col > grid[0].length - 1
                        || grid[temp.row][temp.col] == 0 
                        || grid[temp.row][temp.col] == 2) {
                        continue;
                    } else {
                        grid[temp.row][temp.col] = 2;
                        q.offer(temp);
                        count--;
                    }
                }
            }
        }
        return count == 0 ? time - 1 : -1;
    }
    
    static class Pair{
        int row;
        int col;
        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
