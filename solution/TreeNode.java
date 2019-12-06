package solution;

public class TreeNode {
	public int value; // Key
	public TreeNode left; // By default = null 
	public TreeNode right; // By default = null
	TreeNode parent; // Optional: point to this node's parent node.
	public TreeNode(int value){
		this.value = value;
	}
}
