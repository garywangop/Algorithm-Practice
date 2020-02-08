package binaryTree;
import solution.TreeNode;

public class Test {
	
	public static void main(String args[]) {
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		t1.left = t2;
		t1.right = t3;
		t2.left = t4;
		Test test = new Test();
		System.out.print(test.diameterOfBinaryTree(t1));
	}

	
	public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] diameter = new int[]{0};
        helper(root, diameter);
        return diameter[0];
    }
    
    private int helper(TreeNode root, int[] diameter) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return helper(root.right, diameter);
        }
        if (root.right == null) {
            return helper(root.left, diameter);
        }
        int left = helper(root.left, diameter);
        int right = helper(root.right, diameter);
        diameter[0] = Math.max(left + right, diameter[0]);
        return Math.max(left, right);
    }
}
