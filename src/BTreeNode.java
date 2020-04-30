/**
 * BTree Node
 * @author Luke Ptomey
 * BTree Node object that will make up BTree
 */
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
        keys = new String[2*t - 1]; //key values
        children = new BTreeNode [2*t]; //references
        leaf = true; //Every node starts as leaf
        n =0;

    }
/**
 * Gets value of key
 * @param index
 * @return key value
 */
    public String getValue (int index){
        return keys [index];
    }
    /**
     * Gets child node at given index
     * @param index
     * @return child node
     */
    public BTreeNode getChild(int index){
        return children[index];
    }
    /**
     * Sets status if node is a leaf
     * @param statement
     */
    public void setIfLeaf(boolean statement){
        leaf=statement;
    }


    
	
}
