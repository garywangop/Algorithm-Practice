package review;

import java.util.Queue;

import binarySearchTree.Tree;
import binarySearchTree.TreeImpl;

import java.util.ArrayDeque;

public class DeleteBinaryTree {

	public static void main(String[] args) {
		
	
	}
	
	

	public TreeNode delete(TreeNode root) {
		if (root == null) {
			return null;
		}
		root.left = delete(root.left);
		root.right = delete(root.right);
		if (root.left == null && root.right == null) {
			return root;
		} else if (root.left == null || root.right == null) {
			return root.left == null ? root.right : root.left;
		} else {
			return root;
		}
	}
}
