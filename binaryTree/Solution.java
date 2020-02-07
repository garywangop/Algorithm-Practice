package binaryTree;

import solution.TreeNode;

public class Solution {

	public static void main(String args[]) {
		TreeNode t1 = new TreeNode(5);
		TreeNode t2 = new TreeNode(9);
		TreeNode t3 = new TreeNode(12);
		TreeNode t4 = new TreeNode(2);
		TreeNode t5 = new TreeNode(3);
		TreeNode t6 = new TreeNode(14);
		TreeNode t7 = new TreeNode(7);
		t1.right = t3;
		t1.left = t2;
		t2.right = t5;
		t2.left = t4;
		t3.right = t6;

		TreeNode tt1 = new TreeNode(1);
		TreeNode tt2 = new TreeNode(2);
		TreeNode tt3 = new TreeNode(3);
		tt1.left = tt2;
		tt1.right = tt3;
		Solution test = new Solution();

		System.out.print(test.lowestCommonAncestorI(t1, t4, t7).value);
	}

	/*
	 * 142. Binary Tree Diameter
	 * 
	 * Given a binary tree in which each node contains an integer number. The
	 * diameter is defined as the longest distance from one leaf node to another
	 * leaf node. The distance is the number of nodes on the path.
	 * 
	 * If there does not exist any such paths, return 0.
	 */
	int max = 0; // the diameter of the tree

	public int diameter(TreeNode root) {
		if (root == null) {
			return 0;
		}
		helper(root);
		return max;
	}

	public int helper(TreeNode root) {
		if (root.left == null && root.right == null) {
			return 1;
		}
		if (root.left == null) {
			return helper(root.right) + 1;
		}
		if (root.right == null) {
			return helper(root.left) + 1;
		}
		int left = helper(root.left);
		int right = helper(root.right);
		max = Math.max(left + right + 1, max);
		return Math.max(left, right) + 1;
	}

	/*
	 * diameter1 return的是从leaf到root的最大diameter，但是本题需要的是leaf到lead的最大diameter
	 */
	static int res = 0;

	public int diameter1(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return 1;
		}
		if (root.left == null) {
			return diameter1(root.right) + 1;
		}
		if (root.right == null) {
			return diameter1(root.left) + 1;
		}
		int left = diameter1(root.left);
		int right = diameter1(root.right);
		res = Math.max(left + right + 1, res);
		return Math.max(left, right) + 1;
	}

	/*
	 * 481. Count Univalue Subtrees
	 * 
	 * Given a binary tree, count the number of uni-value subtrees.
	 * 
	 * A Uni-value subtree means all nodes of the subtree have the same value.
	 * 
	 * For example: Given binary tree,
	 * 
	 * 5 / \ 1 5 / \ \ 5 5 5 return 4.
	 */

	public int countUnivalSubtrees(TreeNode root) {
		int[] count = new int[] { 0 };
		helper481(root, count);
		return count[0];
	}

	private boolean helper481(TreeNode root, int[] count) {
		if (root == null) {
			return true;
		}
		boolean left = helper481(root.left, count);
		boolean right = helper481(root.right, count);
		if (left == true && right == true) {
			if (root.left != null && root.value != root.left.value) {
				return false;
			}
			if (root.right != null && root.value != root.right.value) {
				return false;
			}
			count[0]++;
			return true;
		}
		return false;
	}

	/*
	 * 126. Lowest Common Ancestor I
	 * 
	 * Given two nodes in a binary tree, find their lowest common ancestor.
	 * 
	 * Assumptions
	 * 
	 * There is no parent pointer for the nodes in the binary tree
	 * 
	 * The given two nodes are guaranteed to be in the binary tree
	 * 
	 * Examples
	 * 
	 * 5
	 * 
	 * / \
	 * 
	 * 9 12
	 * 
	 * / \ \
	 * 
	 * 2 3 14
	 * 
	 * The lowest common ancestor of 2 and 14 is 5
	 * 
	 * The lowest common ancestor of 2 and 9 is 9
	 */
	public TreeNode lowestCommonAncestorI(TreeNode root, TreeNode a, TreeNode b) {
		if (root == null || root == a || root == b) {
			return root;
		}
		TreeNode left = lowestCommonAncestorI(root.left, a, b);
		TreeNode right = lowestCommonAncestorI(root.right, a, b);
		if (left != null && right != null) {
			return root;
		}
		return left == null ? right : left;
	}

	/*
	 * 127. Lowest Common Ancestor II
	 * 
	 * Given two nodes in a binary tree (with parent pointer available), find their
	 * lowest common ancestor.
	 * 
	 * Assumptions
	 * 
	 * There is parent pointer for the nodes in the binary tree
	 * 
	 * The given two nodes are not guaranteed to be in the binary tree
	 * 
	 * Examples
	 * 
	 * 5
	 * 
	 * / \
	 * 
	 * 9 12
	 * 
	 * / \ \
	 * 
	 * 2 3 14
	 * 
	 * The lowest common ancestor of 2 and 14 is 5
	 * 
	 * The lowest common ancestor of 2 and 9 is 9
	 * 
	 * The lowest common ancestor of 2 and 8 is null (8 is not in the tree)
	 */

	public TreeNodeP lowestCommonAncestorII(TreeNodeP one, TreeNodeP two) {
		int l1 = height(one);
		int l2 = height(two);
		if (l1 <= l2) {
			return mergeNode(one, two, l2 - l1);
		} else {
			return mergeNode(two, one, l1 - l2);
		}
	}

	private TreeNodeP mergeNode(TreeNodeP one, TreeNodeP two, int diff) {
		while (diff > 0) {
			two = two.parent;
			diff--;
		}
		while (one != two) {
			one = one.parent;
			two = two.parent;
		}
		return one;
	}

	private int height(TreeNodeP node) {
		int height = 0;
		while (node != null) {
			height++;
			node = node.parent;
		}
		return height;
	}

	/*
	 * 128. Lowest Common Ancestor III
	 * 
	 * Given two nodes in a binary tree, find their lowest common ancestor (the
	 * given two nodes are not guaranteed to be in the binary tree).
	 * 
	 * Return null If any of the nodes is not in the tree.
	 * 
	 * Assumptions
	 * 
	 * There is no parent pointer for the nodes in the binary tree
	 * 
	 * The given two nodes are not guaranteed to be in the binary tree
	 * 
	 * Examples
	 * 
	 * 5
	 * 
	 * / \
	 * 
	 * 9 12
	 * 
	 * / \ \
	 * 
	 * 2 3 14
	 * 
	 * The lowest common ancestor of 2 and 14 is 5
	 * 
	 * The lowest common ancestor of 2 and 9 is 9
	 * 
	 * The lowest common ancestor of 2 and 8 is null (8 is not in the tree)
	 */
	public TreeNode lowestCommonAncestorIII(TreeNode root, TreeNode one, TreeNode two) {
		TreeNode node = lowestCommonAncestorI(root, one, two);
		if (node == one) {
			return find(one, two) ? one : null;
		} else if (node == two) {
			return find(two, one) ? two : null;
		} else {
			return node;
		}
	}

	private boolean find(TreeNode root, TreeNode target) {
		if (root == null) {
			return false;
		}
		if (root == target) {
			return true;
		}
		return find(root.left, target) || find(root.right, target);
	}

	/*
	 * 299. Distance Of Two Nodes In Binary Tree
	 * 
	 * Find distance between two given keys of a Binary Tree, no parent pointers are
	 * given. Distance between two nodes is the minimum number of edges to be
	 * traversed to reach one node from other.
	 * 
	 * Assumptions:
	 * 
	 * There are no duplicate keys in the binary tree. The given two keys are
	 * guaranteed to be in the binary tree. The given two keys are different.
	 * Examples:
	 * 
	 * 1
	 * 
	 * / \
	 * 
	 * 2 3
	 * 
	 * / \ / \
	 * 
	 * 4 5 6 7
	 * 
	 * \
	 * 
	 * 8
	 * 
	 * distance(4, 5) = 2
	 * 
	 * distance(4, 6) = 4
	 */
	public int distance(TreeNode root, int k1, int k2) {
		TreeNode node = lowestCommonAncestorI(root, k1, k2);
		return dist(node, k1, 0) + dist(node, k2, 0);
	}

	private TreeNode lowestCommonAncestorI(TreeNode root, int a, int b) {
		if (root == null || root.value == a || root.value == b) {
			return root;
		}
		TreeNode left = lowestCommonAncestorI(root.left, a, b);
		TreeNode right = lowestCommonAncestorI(root.right, a, b);
		if (left != null && right != null) {
			return root;
		}
		return left == null ? right : left;
	}

	private int dist(TreeNode root, int a, int level) {
		if (root == null) {
			return -1;
		}
		if (root.value == a) {
			return level;
		}
		int left = dist(root.left, a, level + 1);
		if (left == -1) {
			return dist(root.right, a, level + 1);
		}
		return left;
	}

}
