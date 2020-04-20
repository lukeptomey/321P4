
public class BTreeNode {
	String[] keys; // An array of key values
    int t; // Minimum degree 
    BTreeNode children[] ; // An array of child pointers 
    int n; // Current number of keys 
    boolean leaf; // Whether node is leaf
    BTreeNode parent;// Parent Node

	BTreeNode(int t, BTreeNode parent ){
		this.t=t;
		this.parent= parent;
        keys = new String[2*t - 1];
        children = new BTreeNode [2*t];
        leaf = true; //Every node starts as leaf
        n =0;

    }

    public String getValue (int index){
        return keys [index];
    }

    public BTreeNode getChild(int index){
        return children[index];
    }
    public void setIfLeaf(boolean statement){
        leaf=statement;
    }

    
	
}
