/**
 * BTree Node
 * @author Luke Ptomey
 * BTree Node object that will make up BTree
 */
import java.util.Arrays;
public class BTreeNode {
	Long[] keys; // An array of key values
    int[] freqValue; //Frequency value at each key index
    int degree; // Minimum degree 
    Long children[] ; // An array of child pointers 
    boolean leaf; // Whether node is leaf
    Long location; // location of node in file
    int numbKeys; //number of keys
    int numbChildren; //numberofChildren

/**
 * Node constructor
 * @param location offset in random access file
 * @param degree t
 */
	BTreeNode(Long location, int degree ){
       
		this.degree=degree;
        keys = new Long[2*degree - 1]; 
        freqValue  = new int[2*degree - 1];
        children = new Long [2*degree]; 
        Arrays.fill(keys,-1);
        leaf = true; //Every node starts as leaf
        this.location =location;
        numbKeys=0;
        numbChildren=0;
    }
/**
 * Set whether if node is leaf
 * @param statement true or false
 */
    public void setLeaf(boolean statement){
        leaf=statement;
    }
    public void setKeyNumb(int numb){
        numbKeys=numb;
    }
/**
 * Gets amount of keys in node
 * @return nuber of keys
 */
  public int getAmountOfKeys(){
    return numbKeys;
  }
  /**
   * Gets location of node
   * @return location of node
   */
  public Long getLocation(){
      return location;
  }
	
}
