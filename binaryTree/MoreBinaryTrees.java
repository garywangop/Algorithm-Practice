package binaryTree;

import solution.TreeNode;

public class MoreBinaryTrees {

	public static void main(String args[]) {

	}

	/*
	 * 139. Maximum Path Sum Binary Tree II
	 * 
	 * Given a binary tree in which each node contains an integer number. Find the
	 * maximum possible sum from any node to any node (the start node and the end
	 * node can be the same).
	 * 
	 * Assumptions
	 * 
	 * ​The root of the given binary tree is not null Examples
	 * 
	 * -1
	 * 
	 * / \
	 * 
	 * 2 11
	 * 
	 * / \
	 * 
	 * 6 -14
	 * 
	 * one example of paths could be -14 -> 11 -> -1 -> 2
	 * 
	 * another example could be the node 11 itself
	 * 
	 * The maximum path sum in the above binary tree is 6 + 11 + (-1) + 2 = 18
	 * 
	 * How is the binary tree represented?
	 * 
	 * We use the level order traversal sequence with a special symbol "#" denoting
	 * the null node.
	 * 
	 * For Example:
	 * 
	 * The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
	 * 
	 * 1
	 * 
	 * / \
	 * 
	 * 2 3
	 * 
	 * /
	 * 
	 * 4
	 */

	public int maxPathSum2(TreeNode root) {
		int[] max = new int[] { Integer.MIN_VALUE };
		helper139(root, max);
		return max[0];
	}

	private int helper139(TreeNode root, int[] max) {
		if (root == null) {
			return 0;
		}
		int left = helper139(root.left, max);
		int right = helper139(root.right, max);
		left = left < 0 ? 0 : left;
		right = right < 0 ? 0 : right;
		max[0] = Math.max(left + right + root.value, max[0]);
		return Math.max(left, right) + root.value;
	}

	/*
	 * 140. Maximum Path Sum Binary Tree III
	 * 
	 * Given a binary tree in which each node contains an integer number. Find the
	 * maximum possible subpath sum(both the starting and ending node of the subpath
	 * should be on the same path from root to one of the leaf nodes, and the
	 * subpath is allowed to contain only one node).
	 * 
	 * Assumptions
	 * 
	 * The root of given binary tree is not null Examples
	 * 
	 * -5
	 * 
	 * / \
	 * 
	 * 2 11
	 * 
	 * / \
	 * 
	 * 6 14
	 * 
	 * /
	 * 
	 * -3
	 * 
	 * The maximum path sum is 11 + 14 = 25
	 * 
	 * How is the binary tree represented?
	 * 
	 * We use the level order traversal sequence with a special symbol "#" denoting
	 * the null node.
	 * 
	 * For Example:
	 * 
	 * The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
	 * 
	 * 1
	 * 
	 * / \
	 * 
	 * 2 3
	 * 
	 * /
	 * 
	 * 4
	 */
	public int maxPathSum3(TreeNode root) {
	    int[] max = new int[]{Integer.MIN_VALUE};
	    helper140(root, max);
	    return max[0];
	  }

	  private int helper140(TreeNode root, int[] max) {
	    if (root == null) {
	      return 0;
	    }
	    int left = helper140(root.left, max);
	    int right = helper140(root.right, max);
	    left = left < 0 ? 0 : left;
	    right = right < 0 ? 0 : right;
	    max[0] = Math.max(max[0], root.value + Math.max(left, right));
	    return Math.max(left, right) + root.value;
	  }

}
