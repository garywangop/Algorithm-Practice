package binarySearchTree;

import solution.TreeNode;

public class CloestToTarget {

	public int cloest(TreeNode root, int target) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		int res = Integer.MIN_VALUE;
		while (root != null) {
			if (root.value == target) {
				return root.value;
			} else {
				res = Math.abs(target - res) <= Math.abs(target- root.value) ? res : root.value;
				if (root.value > target) {
					root = root.left;
				} else {
					root = root.right;
				}
			}
		}
		return res;
	}

}
