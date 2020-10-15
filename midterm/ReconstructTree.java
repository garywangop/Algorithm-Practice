package midterm;

import java.util.*;

public class ReconstructTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public TreeNode reconstruct(int[] inOrder, int[] preOrder) {
	    Map<Integer, Integer> inIndex = indexMap(inOrder);
	    return helper213(preOrder, inIndex, 0, inOrder.length - 1, 0, preOrder.length - 1);
	  }

	  private Map<Integer, Integer> indexMap(int[] inOrder) {
	    Map<Integer, Integer> map = new HashMap<>();
	    for (int i = 0; i < inOrder.length; i++) {
	      map.put(inOrder[i], i);
	    }
	    return map;
	  }

	  private TreeNode helper213(int[] pre, Map<Integer, Integer> inIndex, int inLeft, int inRight, int preLeft, int preRight) {
	    if (preLeft > preRight) {
	      return null;
	    }
	    TreeNode root = new TreeNode(pre[preLeft]);
	    // Get the position of the root in inOrder sequence, so that we will know the size of left/right subtrees.
	    int inMid = inIndex.get(root.val);
	    root.left = helper213(pre, inIndex, inLeft, inMid - 1, preLeft + 1, preLeft + inMid - inLeft);
	    root.right = helper213(pre, inIndex, inMid + 1, inRight, preRight + inMid - inRight + 1, preRight);
	    return root;
	  }

}
