package lca;

public class TreeNodeP {
	public TreeNodeP left;
	public TreeNodeP right;
	public TreeNodeP parent;
	public int key;
	
	public TreeNodeP(TreeNodeP parent, int key) {
		this.parent = parent;
		this.key = key;
	}
}
