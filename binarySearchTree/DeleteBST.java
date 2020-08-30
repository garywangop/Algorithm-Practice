package binarySearchTree;

import solution.TreeNode;

public class DeleteBST {
	
	public TreeNode deleteTree(TreeNode root, int target) {
		if (root == null) {
			return null;
		}
		
		// Find the target node
		if (root.value < target) {
			root.right = deleteTree(root.right, target);
			return root;
		} else if (root.value > target) {
			root.left = deleteTree(root.left, target);
			return root;
		}
		
		// 当代码跑到这里来的时候，证明target找到了且root != null
		// 找到target之后，可以把target左子树的最大值挪上来，也可以把右子树的最小值挪上来
		// 这个题移右子树的最小值
		
		// 如果target是leave，那就没得移了，直接return null
		// 如果target没有左子树，那就直接把右边拎上来
		// 如果target没有右子树，那就把左边拎上来
		// 如果root.right.left == null，那root.right就是最小值，那也是直接把右边拎上来
		if (root.left == null && root.right == null) {
			return null;
		} else if (root.left == null) {
			return root.right;
		} else if (root.right == null) {
			return root.left;
		} else if (root.right.left == null) {
			// 一定要先把root.left连到root.right.left上才能return回去，不然root.left就丢了
			root.right.left = root.left;
			return root.right;
		}
		
		// 当代码跑到这里来的时候，那所有的特殊情况都处理完了，那就从root.right一路往左跑找最小值
		TreeNode node = findSmallest(root.right);
		node.left = root.left;
		node.right = root.right;
		return node;
	}
	
	private TreeNode findSmallest(TreeNode root) {
		TreeNode prev = null;
		while (root.left != null) {
			prev = root;
			root = root.left;
		}
		prev.left = root.right;
		root.right = null;
		return root;
	}
}
