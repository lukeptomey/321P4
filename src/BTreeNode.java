/**
 * BTree Node
 * @author Luke Ptomey
 * BTree Node object that will make up BTree
 */
import java.util.Arrays;
public class BTreeNode {
	BTreeObject[] keys; // An array of key values
    int degree; // Minimum degree 
    long children[] ; // An array of child pointers 
    boolean leaf; // Whether node is leaf
    long location; // location of node in file
    int numbKeys; //number of keys
    int numbChildren; //numberofChildren

/**
 * Node constructor
 * @param location offset in random access file
 * @param degree t
 */
	BTreeNode(Long location, int degree ){
       
		this.degree=degree;
        keys = new BTreeObject[2*degree - 1]; 
        children = new long [2*degree]; 
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
    /**
     * Set number of keys
     * @param numb
     */
    public void setKeyNumb(int numb){
        numbKeys=numb;
    }
/**
 * Gets amount of keys in node
 * @return number of keys
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
/**
 * Set child is node input at given index
 * @param index
 * @param node
 */
  public void setChildAtIndex(int index, BTreeNode node){
      children[index]= node.getLocation();
  }
/**
 * Sets number of children for node
 * @param i amount of children node now has
 */
  public void setNumbOfChildren(int i){
      numbChildren=i;
  }

  public long getChildAtIndex(int i){
    return children[i];
  }

  /**
   * Returns if node is a leaf or not
   * @return boolean true or false
   */
  public boolean checkLeaf(){
      return leaf;
  }

  /**
   * Sets key at given index
   * @param key to be set
   * @param i index
   */
  public void setKeyAtIndex(int i, BTreeObject key){
      keys[i]=key;
  }

  /**
   * Gets key at index
   * @param i index
   * @return key from array of keys
   */
  public BTreeObject getKeyAtIndex(int i){
    return keys[i];
  }


	
}
