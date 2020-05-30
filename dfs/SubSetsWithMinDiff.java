package dfs;

public class SubSetsWithMinDiff {

	public static void main(String[] args) {
		SubSetsWithMinDiff sol = new SubSetsWithMinDiff();
		int[] arr = new int[] {2,9,3,1,1};
		System.out.println(sol.minDifference(arr));

	}
	
	public int minDifference(int[] arr) {
		// Assume the arr is not null
		int total = 0;
		for (int i : arr) {
			total += i;
		}
		int[] res = new int[] {Integer.MAX_VALUE};
		helper(arr, total, 0, arr.length / 2, 0, 0, res);
		return res[0];
	}
	
	private void helper(int[] arr, int total, int index, int leftSize, int left, int sum, int[] res) {
		if (left == leftSize) {
			res[0] = Math.min(res[0], Math.abs(2 * sum - total));
			return;
		}
		
		if (index == arr.length) {
			return;
		}
		
		// Add a number to the left
		helper(arr, total, index + 1, leftSize, left + 1, sum + arr[index], res);
		// Don't add the number
		helper(arr, total, index + 1, leftSize, left, sum, res);
	}
}
