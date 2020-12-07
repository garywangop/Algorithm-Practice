package leetcode;

import java.util.*;

public class FindBottomLeftValue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// 513
	// Note: You may assume the tree (i.e., the given root node) is not NULL.
	public int findBottomLeftValue(TreeNode root) {
        int res = root.val;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
        	int size = q.size();
        	for (int i = 0; i < size; i++) {
        		TreeNode cur = q.poll();
        		if (i == 0) {
        			res = cur.val;
        		}
        		if (cur.left != null) {
        			q.offer(cur.left);
        		}
        		if (cur.right != null) {
        			q.offer(cur.right);
        		}
        	}
        }
        return res;
    }

}
