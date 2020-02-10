package binaryTree;
import solution.TreeNode;

public class Test {
	
	public static void main(String args[]) {
		TreeNode t1 = new TreeNode(5);
		TreeNode t2 = new TreeNode(3);
		TreeNode t3 = new TreeNode(2);
		TreeNode t4 = new TreeNode(1);
		TreeNode t5 = new TreeNode(0);
		TreeNode t6 = new TreeNode(11);
		t1.left = t2;
		t1.right = t3;
		t2.left = t4;
		t2.right = t5;
		t3.right = t6;
		Test test = new Test();
		System.out.print(test.longest(t1));
	}

	
	public int longest(TreeNode root) {
	    int[] max = new int[]{1};
	    helper388(root, max);
	    return max[0];
	  }

	  private int helper388(TreeNode root, int[] max) {
	    if (root == null) {
	      return 0;
	    }
	    if (root.left == null && root.right == null) {
	    	return 1;
	    }
	    
	    int left = helper388(root.left, max);
	    int right = helper388(root.right, max);
	    
	    if (root.left == null) {
	    	if (root.value < root.right.value) {
	    		max[0] = Math.max(max[0], right + 1);
	    		return right + 1;
	    	}
	    }
	    if (root.right == null) {
	    	if (root.value < root.left.value) {
	    		max[0] = Math.max(max[0], left + 1);
	    		return left + 1;
	    	}
	    }
	    if (root.value < root.left.value || root.value < root.right.value) {
	    	max[0] = Math.max(max[0], Math.max(left, right) + 1);
	    	return Math.max(left, right) + 1;
	    }
	    return 1;
	    
	  }
}
