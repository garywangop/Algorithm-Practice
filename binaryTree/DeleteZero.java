package binaryTree;

import solution.TreeNode;

public class DeleteZero {

	public static void main(String[] args) {
		DeleteZero sol = new DeleteZero();
		TreeNode node1 = new TreeNode(0);
		TreeNode node2 = new TreeNode(0);
		TreeNode node3 = new TreeNode(45);
		TreeNode node4 = new TreeNode(14);
		TreeNode node5 = new TreeNode(0);
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		sol.deleteZero(node1);

	}

	/*
	 * 322. Delete Zero Nodes From Leaf
	 * 
	 * Given a binary tree, delete the nodes only if it is 0 and all the nodes on
	 * the paths from the node to any leaf nodes are all 0.
	 * 
	 * In another word, delete the leaf nodes with 0 recursively until there are no
	 * such nodes in the tree.
	 * 
	 * You only need to return the final tree after deletion.
	 * 
	 * Examples:
	 * 
	 * 0 / \ 0 3 / \ / \ 0 0 0 7 / \ 0 0
	 * 
	 * \
	 * 
	 * 0
	 * 
	 * After first round, deleting all the leaf nodes with 0, the tree becomes:
	 * 
	 * 0 / \ 0 3 / / \ 0 0 7 / 0
	 * 
	 * After second round, deleting all the leaf nodes with 0, the tree becomes:
	 * 
	 * 0 / \ 0 3 / \ 0 7
	 * 
	 * After third round, deleting all the leaf nodes with 0, the tree becomds:
	 * 
	 * 0 / \ 0 3 \ 7
	 * 
	 * After another round, deleting all the leaf nodes with 0, the tree becomds:
	 * 
	 * 0 \ 3 \ 7
	 * 
	 * The deletion end at this step since there are no more nodes to delete.
	 * 
	 * You only need to return the final binary tree after deletion.
	 */
	public TreeNode deleteZero(TreeNode root) {
		if (root == null) {
			return root;
		}
		root.left = deleteZero(root.left);
		root.right = deleteZero(root.right);
		if (root.value == 0 && root.left == null && root.right == null) {
			return null;
		} else {
			return root;
		}
	}
}
