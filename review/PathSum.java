package review;

import java.util.HashMap;
import java.util.Map;

public class PathSum {

	public static void main(String[] args) {
		PathSum sol = new PathSum();
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(-2);
		TreeNode node3 = new TreeNode(-3);
		node1.left = node2;
		node1.right = node3;
		System.out.println(sol.pathSum(node1, -1));
	}
	
	/*
	 * Leetcode:
	 * 437. Path Sum III
	 */
	
	public int pathSum(TreeNode root, int sum) {
		if (root == null) {
			return 0;
		}
		int[] res = new int[1];
		// Key: sum, Value: freq
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0,1);
		helper(root, sum, 0, map, res);
		return res[0];
	}
	
	private void helper(TreeNode root, int target, int sum, Map<Integer, Integer> map, int[] res) {
		if (root == null) {
			return;
		}
		
		sum += root.val;
		Integer cur = map.get(sum - target);
		Integer curFreq = map.get(sum);
		if (cur != null) {
			res[0] += cur;
		}
		
		if (curFreq == null) {
			map.put(sum, 1);
		} else {
			map.put(sum, curFreq + 1);
		}
		
		helper(root.left, target, sum, map, res);
		helper(root.right, target, sum, map, res);
		map.put(sum, map.get(sum) - 1);
	}
	
	static class TreeNode {
		TreeNode left;
		TreeNode right;
		int val;
		public TreeNode(int val) {
			this.val = val;
		}
	}

}
