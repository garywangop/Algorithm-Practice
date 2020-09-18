package binarySearchTree;

import solution.TreeNode;

public class ReverseTreeUpsideDown {

	public static void main(String[] args) {
		ReverseTreeUpsideDown sol = new ReverseTreeUpsideDown();
		Tree tree = new TreeImpl();
		Integer[] list = new Integer[] {1,2,5,3,4};
		TreeNode root = tree.create(list);
		tree.print(sol.reverse(root));
	}
	
	public TreeNode reverse(TreeNode root) {
		if (root == null || root.left == null) {
			return root;
		}
		
		TreeNode newHead = reverse(root.left);
		root.left.left = root;
		root.left.right = root.right;
		root.left = null;
		root.right = null;
		return newHead;
		/*
		TreeNode left = reverse(root.left);
		left.left = root;
		left.right = root.right;
		root.left = null;
		root.right = null;
		return left;
		*/
	}

}
