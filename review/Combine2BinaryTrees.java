package review;

import solution.TreeNode;

public class Combine2BinaryTrees {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public TreeNode combine(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return null;
		}
		if (root1 == null || root2 == null) {
			root1 = root2 == null ? root1 : root2;
		}
		if (root1 != null && root2 != null) {
			root1.value = root1.value + root2.value;
		}
		combine(root1.left, root2.left);
		combine(root1.right, root2.right);
		return root1;
	}

}
