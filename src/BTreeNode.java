/**
 * BTree Node
 * @author Luke Ptomey
 * BTree Node object that will make up BTree
 */
import java.util.Arrays;
public class BTreeNode {
	Long[] keys; // An array of key values
    int degree; // Minimum degree 
    Long children[] ; // An array of child pointers 
    boolean leaf; // Whether node is leaf
    Long location; // location of node in file
    int numbKeys; //number of keys
    int numbChildren; //numberofChildren

	BTreeNode(Long location, int degree ){
       
		this.degree=degree;
        keys = new Long[2*degree - 1]; 
        children = new Long [2*degree]; 
        Arrays.fill(keys,-1);
        leaf = true; //Every node starts as leaf
        this.location =location;
        numbKeys=0;
        numbChildren=0;
    }
/**
 * Gets value of key
 * @param index
 * @return key value
 */
    public long getValue (int index){
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
