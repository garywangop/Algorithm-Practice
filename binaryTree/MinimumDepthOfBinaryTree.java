package binaryTree;
import solution.TreeNode;

public class MinimumDepthOfBinaryTree {

	public int minDepth(TreeNode root) {
	    if (root == null) {
	        return 0;
	      }
	      int left = minDepth(root.left);
	      int right = minDepth(root.right);
	      
	      if (left != 0 && right != 0) {
	        return Math.min(left, right) + 1;
	      } else if (left == 0) {
	        return right + 1;
	      } else { // right == 0
	        return left + 1;
	      }
	    }
}
