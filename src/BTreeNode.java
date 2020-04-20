
public class BTreeNode {
	String[] keys; // An array of key values
    int t; // Minimum degree 
    BTreeNode[] C; // An array of child pointers 
    int n; // Current number of keys 
    boolean leaf; // Whether node is leaf
    BTreeNode parent;// Parent Node

	BTreeNode(int t, BTreeNode parent ){
		this.t=t;
		this.parent= parent;
		
	}
	
}
