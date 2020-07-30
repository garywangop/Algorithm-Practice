package sudoku;

public class Solver {
	public static void main(String[] args) {
		Solver sol = new Solver();
		/*
		int[][] arr = new int[][] {
			{0,0,0,6,8,7,0,4,0},
			{0,2,0,0,0,3,0,6,0},
			{6,0,0,2,5,0,1,0,7},
			{1,3,0,0,0,0,0,0,5},
			{5,0,0,0,0,2,7,3,0},
			{7,6,4,0,3,8,0,0,9},
			{0,0,0,3,0,0,0,2,6},
			{0,5,6,0,0,1,4,7,3},
			{0,4,0,7,2,0,0,0,1}
		};
		*/
		
		int[][] arr = new int[9][9];
		arr[3][0] = 3;
		arr[3][1] = 8;
		arr[3][2] = 4;
		arr[8][8] = 2;

 		sol.solveSudoku(arr);
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (j < arr.length - 1) {
					System.out.print(arr[i][j] + ",");
				} else {
					System.out.println(arr[i][j]);
				}
			}
		}
	}
	
	public void solveSudoku(int[][] board) {
		helper(board, 0, 0);
	}
	
	private boolean helper(int[][] board, int row, int col) {
		for (int i = row; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 0) {
					for (int k = 1; k <= 9; k++) {
						if (isValid(board, i, j, k)) {
							board[i][j] = k;
							if (helper(board, i, j + 1)) {
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
	
	private boolean isValid(int[][] board, int row, int col, int target) {
		int blockRow = (row / 3) * 3;
		int blockCol = (col / 3) * 3;
		for (int i = 0; i < board.length; i++) {
			if (board[row][i] == target || board[i][col] == target || board[blockRow + i / 3][blockCol + i % 3] == target) {
				return false;
			}
		}
		return true;
	}

}
