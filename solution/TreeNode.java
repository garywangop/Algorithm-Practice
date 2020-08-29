package solution;

public class TreeNode {
	public Integer value; // Key
	public TreeNode left; // By default = null 
	public TreeNode right; // By default = null
	TreeNode parent; // Optional: point to this node's parent node.
	public TreeNode(Integer value){
		this.value = value;
	}
}
