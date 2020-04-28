package review;

public class MaxSquare {

	public static void main(String[] args) {
		MaxSquare sol = new MaxSquare();

	}

	/*
	 * Maximal Square
	 * 
	 * 
	 * Given a 2D binary matrix filled with 0's and 1's, find the largest square
	 * containing only 1's and return its area.
	 * 
	 * Example:
	 * 
	 * Input:
	 * 
	 * 1 0 1 0 0 1 0 1 1 1 1 1 1 1 1 1 0 0 1 0
	 * 
	 * Output: 4
	 */

	public int maximalSquare(char[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return 0;
		}
		// dp
		int[][] dp = new int[matrix.length][matrix[0].length];
		int res = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == '1') {
					if (i == 0 || j == 0) {
						dp[i][j] = 1;
					} else {
						dp[i][j] = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
						dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 1);
					}
					res = Math.max(res, dp[i][j] * dp[i][j]);
				}
			}
		}
		return res;
	}

}
