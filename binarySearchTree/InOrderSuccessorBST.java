package binarySearchTree;

import solution.TreeNode;

public class InOrderSuccessorBST {

	
	public int inOrderSuccessor(TreeNode root, int p) {
		if (root == null) {
			return -1;
		}
		int res = -1;
		while (root != null) {
			if (root.value > p) {
				res = root.value;
				root = root.left;
			} else {
				root = root.right;
			}
		}
		return res;
	}
}
