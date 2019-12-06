package solution;
import java.util.*;

public class BinarySearch {

	public static void main(String[] args) {
		int[][] array = {{1, 4}};
		
		BinarySearch test = new BinarySearch();
		
		System.out.println(test.search(array, 4));
		}
		
	
	
	public int[] search(int[][] matrix, int target) {
		int[] res = new int[] {-1, -1};
	    if (matrix.length == 0 || matrix[0].length == 0) {
	      return res;
	    }
	    int row = matrix.length;
	    int col = matrix[0].length;
	    int left = 0, right = row * col - 1;
	    while (left <= right) {
	      int mid = left + (right - left) / 2;
	      
	      if (matrix[mid/col][mid%col] == target) {
	        res[0] = mid/col;
	        res[1] = mid%col;
	        return res;
	      } else if (matrix[mid/row][mid%row] < target) {
	        left = mid + 1;
	      } else {
	        right = mid - 1;
	      }
	    }
	    return res;
	}

	
	
}






