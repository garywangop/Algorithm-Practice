package binaryTree;

import java.util.HashSet;
import java.util.Set;

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
		int[] max = new int[] { Integer.MIN_VALUE };
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

	/*
	 * 440. Binary Tree Path Sum To Target I
	 * 
	 * Given a binary tree and a target sum, determine if the tree has a
	 * root-to-leaf path such that adding up all the values along the path equals
	 * the given target.
	 * 
	 * Example: Given the below binary tree and target = 16,
	 * 
	 * 5 / \ 4 8 / / \ 1 3 4 / \ \ 7 2 1 return true, as there exist a root-to-leaf
	 * path 5-8-3 which sum is 16.
	 */

	public boolean exist1(TreeNode root, int target) {
		if (root == null) {
			return false;
		}
		if (root.left == null && root.right == null) {
			return root.value == target;
		}
		return exist1(root.left, target - root.value) || exist1(root.right, target - root.value);
	}

	/*
	 * 141. Binary Tree Path Sum To Target III
	 * 
	 * Given a binary tree in which each node contains an integer number. Determine
	 * if there exists a path (the path can only be from one node to itself or to
	 * any of its descendants), the sum of the numbers on the path is the given
	 * target number.
	 * 
	 * Examples
	 * 
	 * 5
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
	 * 3
	 * 
	 * If target = 17, There exists a path 11 + 6, the sum of the path is target.
	 * 
	 * If target = 20, There exists a path 11 + 6 + 3, the sum of the path is
	 * target.
	 * 
	 * If target = 10, There does not exist any paths sum of which is target.
	 * 
	 * If target = 11, There exists a path only containing the node 11.
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
	public boolean exist2(TreeNode root, int target) {
		if (root == null) {
			return false;
		}
		Set<Integer> prefixSums = new HashSet<>();
		prefixSums.add(0);
		return helperII(root, prefixSums, 0, target);
	}

	private boolean helperII(TreeNode root, Set<Integer> prefixSums, int prevSum, int sum) {
		prevSum += root.value;
		if (prefixSums.contains(prevSum - sum)) {
			return true;
		}
		boolean needRemove = prefixSums.add(prevSum);
		if (root.left != null && helperII(root.left, prefixSums, prevSum, sum)) {
			return true;
		}
		if (root.right != null && helperII(root.right, prefixSums, prevSum, sum)) {
			return true;
		}
		if (needRemove) {
			prefixSums.remove(prevSum);
		}
		return false;
	}

	/*
	 * 388. Longest Ascending Path Binary Tree
	 * 
	 * Determine the length of the longest ascending path in the binary tree.
	 * 
	 * A valid path is a part of the path from root to any of the leaf nodes.
	 * 
	 * Examples:
	 * 
	 * 5
	 * 
	 * / \
	 * 
	 * 3 2
	 * 
	 * / \ \
	 * 
	 * 1 0 11
	 * 
	 * the longest ascending path is 2 -> 11, length is 2.
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
	public int longest(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int[] max = new int[] { 0 };
		maxPath(root, root.value, 0, max);
		return max[0];
	}

	private void maxPath(TreeNode root, int preValue, int curLen, int[] max) {
		if (root == null) {
			return;
		}
		if (root.value > preValue) {
			curLen++;
		} else {
			curLen = 1;
		}
		max[0] = Math.max(max[0], curLen);
		maxPath(root.left, root.value, curLen, max);
		maxPath(root.right, root.value, curLen, max);
	}
}
