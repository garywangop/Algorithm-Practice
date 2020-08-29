package binarySearchTree;

import solution.TreeNode;

public class Delete {

	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node8 = new TreeNode(8);
		TreeNode node9 = new TreeNode(9);
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node3.right = node5;
		node4.left = node6;
		node4.right = node7;
		node5.left = node8;
		node5.right = node9;
		PrintTree print = new PrintTree(node1);
		print.levelOrder();

	}

	public TreeNode delete(TreeNode root, int target) {
		if (root == null) {
			return null;
		}
		
		// Find the target node
		if (root.value < target) {
			root = root.right;
			return root;
		} else if (root.value > target) {
			root = root.left;
			return root;
		}
		
		// If code can be executed to this line, it means root != null && root.value == target
		if (root.left == null) {
			return root.right;
		} else if (root.right == null) {
			return root.left;
		}
		
		// If code reaches this line, it means root.left != null && root.right != null
		
		
		return root;
	}
}
