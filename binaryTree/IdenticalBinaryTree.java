package binaryTree;
import solution.TreeNode;

public class IdenticalBinaryTree {
	public static void main (String args[]) {
		TreeNode t1 = new TreeNode(5);
		TreeNode t2 = new TreeNode(3);
		TreeNode t3 = new TreeNode(8);
		t1.left = t2;
		t1.right = t3;
		TreeNode tr1 = new TreeNode(5);
		TreeNode tr2 = new TreeNode(3);
		TreeNode tr3 = new TreeNode(8);
		tr1.left = tr2;
		tr1.right = tr3;
		
		IdenticalBinaryTree test = new IdenticalBinaryTree();
		System.out.println(test.isIdentical(t1, tr1));
	}
	
	public boolean isIdentical(TreeNode one, TreeNode two) {
	    if (one == null && two == null) {
	      return true;
	    } else if (one == null || two == null) {
	      return false;
	    } else if (one.value != two.value) {
	      return false;
	    }
	    return isIdentical(one.right, two.right) && isIdentical(one.left, two.left);
	  }
}
