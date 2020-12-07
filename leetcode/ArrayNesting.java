package leetcode;

public class ArrayNesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// 565
	
	// 很有意思的题。如果visited过的话就标记成-1，后面扫描到-1的话证明之前已经visited过了，那以-1开始检查的话肯定小于之前的结果
	// 语文不好，只可意会，不可言传
	public int arrayNesting(int[] nums) {
		int res = 0;
		int[] visited = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			int size = 0;
			for (int j = i; visited[j] != -1; size++) {
				visited[j] = -1;
				j = nums[j];
			}
			res = Math.max(res,  size);
		}
		return res;
	}

}
