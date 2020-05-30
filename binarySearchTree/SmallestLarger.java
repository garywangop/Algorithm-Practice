package binarySearchTree;

import solution.TreeNode;

public class SmallestLarger {

	public int smallestLarger(TreeNode root, int target) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		int res = Integer.MIN_VALUE;
		while (root != null) {
			if (root.value > target) {
				res = root.value;
				root = root.left;
			} else {
				root = root.right;
			}
		}
		return res;
	}
}
