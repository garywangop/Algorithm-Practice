package leetcode;

public class GetMaximumGold {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetMaximumGold sol = new GetMaximumGold();
		int[][] grid = new int[][] {{0,6,0},{5,8,7},{0,9,0}};
		System.out.println(sol.getMaximumGold(grid));
		

	}
	
	public int getMaximumGold(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    int[] cur = new int[]{0};
                    boolean[][] visited = new boolean[grid.length][grid[i].length];
                    dfs(grid, i, j, 0, visited, cur);
                    res = Math.max(cur[0], res);
                }
            }
        }
        return res;
    }
    
    private final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    
    private void dfs(int[][] grid, int row, int col, int cur, boolean[][] visited, int[] max) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[row].length || grid[row][col] == 0 || visited[row][col]) {
            max[0] = Math.max(max[0], cur);
            return;
        }
        
        for (int[] direction : DIRECTIONS) {
            visited[row][col] = true;
            dfs(grid, row + direction[0], col + direction[1], cur + grid[row][col], visited, max);
            visited[row][col] = false;
        }
    }

}
