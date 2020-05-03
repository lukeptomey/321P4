import java.io.IOException;

/**
 * BTree
 * @author Luke Ptomey
 * BTree that holds BTreeNodes along with keys
 */
public class BTree {
    private FileRW file;
	private BTreeNode root;
	private BTreeNode newNode;
	private BTreeNode y;
	private BTreeNode z;
	private int degree;


public BTree(int useCache, int degree, String gbkFilename, int sequenceLength, int cacheSize){
    this.degree=degree;
try{
    file = new FileRW(gbkFilename + ".btree.data." + sequenceLength + "." + degree, degree, sequenceLength);
}
catch (IOException e){
    System.out.println("File could not be created");
}
 root = file.createNode();
 root.setLeaf(true);
		file.writeNode(root);
		file.setRootLocation(root.getLocation());
}
    
    /**
     * Inserts new node
     * @param BTree
     * @param key to be inserted
     */
    public void insert( Long key){
    if(root.getAmountOfKeys()== 2 *degree-1){
       newNode=file.createNode();
        file.setRootLocation(newNode.getLocation());
        newNode.setLeaf(false);
        newNode.setKeyNumb(0);
        newNode.setChildAtIndex(0,root);
        newNode.setNumbOfChildren(1);
        file.writeNode(newNode);
      
       split(newNode,0);
       insertNonFull(newNode,key);
    }
       else{
        insertNonFull(root,key); 
       }


         }

    /**
     * Splits node if full
     * @param current node to split
     * @param  child node location
     */
    public void split(BTreeNode x, int i){
        y = file.getNode(x.getChildAtIndex(i));
        z = file.createNode();
		z.setLeaf(y.checkLeaf());
        z.setNumbOfChildren(degree-1);
        
        for(int j=0; j < (degree-1 ); j++){
            x.setKeyAtIndex(y.getKeyAtIndex(j+degree), j);
            //copy freqency?
        }
		
		


     
      
    }
    
    /**
     * Nonful node insetion 
     * @param x current nonfull node
     * @param key new key
     */
    public void insertNonFull(BTreeNode x, Long key){
    




                    
                    
                
            }

    }



