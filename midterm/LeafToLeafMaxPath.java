package midterm;

import binarySearchTree.Tree;
import binarySearchTree.TreeImpl;
import solution.TreeNode;


public class LeafToLeafMaxPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LeafToLeafMaxPath sol = new LeafToLeafMaxPath();
		Tree tree = new TreeImpl();
		Integer[] list = new Integer[] {1,2,3};
		// Integer[] list = new Integer[] {-15, 5, 6, -8, 1, 3, 9, 2, 6, null, null, null, null, null, 0, null, null, null, null, 4, -1, null, null, 10};
		TreeNode root = tree.create(list);
		tree.print(root);
		System.out.println(sol.maxPath(root));
	}
	
	public int maxPath(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int[] res = new int[] {Integer.MIN_VALUE};
		helper(root, res);
		return res[0];
	}
	
	private int helper(TreeNode root, int[] res) {
		if (root == null) {
			return 0;
		}
		
		int left = helper(root.left, res);
		int right = helper(root.right, res);
		if (root.left != null && root.right != null) {
			res[0] = Math.max(res[0], left + right + root.value);
			return root.value + Math.max(left, right);
		}
		return root.left == null ? right + root.value : left + root.value;
		
	}

}
