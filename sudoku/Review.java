package sudoku;

public class Review {
	public static void main(String[] args) {
		Review sol = new Review();
		int[][] arr = new int[][] {
			{9,0,0,8,3,0,1,5,7},
			{5,0,3,1,0,6,2,8,0},
			{1,0,0,7,4,0,0,9,0},
			{0,0,0,0,5,0,8,3,0},
			{3,0,1,0,0,4,6,7,2},
			{2,0,0,0,1,3,0,0,9},
			{0,0,2,0,7,0,0,1,0},
			{0,0,0,0,0,0,0,6,0},
			{0,3,4,0,6,0,9,2,0}
		};
		sol.playGame(arr);
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (j < arr[i].length - 1) {
					System.out.print(arr[i][j] + ",");
				} else {
					System.out.println(arr[i][j]);
				}
			}
		}
	}
	
	public void playGame(int[][] board) {
		solvePuzzle(board, 0, 0);
	}
	
	private boolean solvePuzzle(int[][] board, int row, int col) {
		
		for (int i = row; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 0) {
					for (int k = 1; k <= 9; k++) {
						if (checkValid(board, i, j, k)) {
							board[i][j] = k;
							if (solvePuzzle(board, i, j + 1)) {
								return true;
							}
							board[i][j] = 0;
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean checkValid(int[][] board, int row, int col, int target) {
		int blockRow = (row / 3) * 3;
		int blockCol = (col / 3) * 3;
		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == target || board[row][i] == target || board[blockRow + i / 3][blockCol + i % 3] == target) {
				return false;
			}
		}
		return true;
	}
}
