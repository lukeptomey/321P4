/**
 * BTree Node
 * @author Luke Ptomey
 * BTree Node object that will make up BTree
 */
import java.util.Arrays;
public class BTreeNode {
	BTreeObject[] keys; // An array of key values
    int t; // Minimum degree 
    Long children[] ; // An array of child pointers 
    int n; // Current number of keys 
    boolean leaf; // Whether node is leaf
    Long ownLocation; // location of node in file

	BTreeNode(int t ){
       
		this.t=t;
        keys = new BTreeObject[2*t - 1]; //key values
        children = new Long [2*t]; //references
        Arrays.fill(keys,-1);
        leaf = true; //Every node starts as leaf
        n =0;

    }
/**
 * Gets value of key
 * @param index
 * @return key value
 */
    public BTreeObject getValue (int index){
        return keys [index];
    }
    /**
     * Gets child node at given index
     * @param index
     * @return child node
     */
    public Long getChild(int index){
        return children[index];
    }
    /**
     * Sets status if node is a leaf
     * @param statement
     */
    public void setIfLeaf(boolean statement){
        leaf=statement;
    }
    public String printInfo(){
        return "nothing yet";
    }


    
	
}
