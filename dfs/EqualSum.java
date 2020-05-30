package dfs;

public class EqualSum {

	public static void main(String[] args) {
		
		EqualSum sol = new EqualSum();
		//1,2,4,4,5,6
		int[] arr = null;
		System.out.println(sol.isPossible(arr));
	}
	
	public boolean isPossible(int[] arr) {
		if (arr == null) {
			return false;
		}
		
		int sum = 0;
		for (int i : arr) {
			sum += i;
		}
		if (sum % 2 != 0) {
			return false;
		} else {
			return dfs(arr, sum / 2, 0, 0, new boolean[1]);
		}
	}

	private boolean dfs(int[] arr, int target, int index, int sum, boolean[] find) {
		
		if (!find[0]) {
			if (sum == target) {
				find[0] = true;
				return true;
			}
			
			if (index == arr.length) {
				return false;
			}
			
			if (!find[0]) {
				// Add arr[index]
				dfs(arr, target, index + 1, sum + arr[index], find);
				// Don't add arr[index]
				dfs(arr, target, index + 1, sum, find);
			}
			
		}
		
		return find[0];
	}
}
