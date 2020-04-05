package review;

public class WordSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] board = new char[][] {{'a','b','c','e'}, {'s','f','c','s'}, {'a','d','e','e'}};
		System.out.println(exist(board, "abcced"));

	}
	
	public static boolean exist(char[][] board, String word) {
		if (board == null || word == null) {
			return false;
		}
		
		boolean[][] visited = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (findWord(board, word, 0, i, j, visited)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static boolean findWord(char[][] board, String word, int index, int row, int col, boolean[][] visited) {
		if (index == word.length()) {
			return true;
		}
		if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col] || word.charAt(index) != board[row][col]) {
			return false;
		}
		
		visited[row][col] = true;
		if (findWord(board, word, index + 1, row + 1, col, visited) ||
				findWord(board, word, index + 1, row - 1, col, visited) ||
				findWord(board, word, index + 1, row, col + 1, visited) ||
				findWord(board, word, index + 1, row, col - 1, visited)
				) {
			
			return true;
		}
		visited[row][col] = false;
		return false;
	}

}
