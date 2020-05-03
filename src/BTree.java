import java.io.IOException;

/**
 * BTree
 * @author Luke Ptomey
 * BTree that holds BTreeObjects
 */
public class BTree {
    private FileRW file;
	private BTreeNode root;
	private BTreeNode newNode;
	private BTreeNode child1;
	private BTreeNode child2;
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
		//file.writeNode(root);
		file.setRootLocation(root.getLocation());



}
    //Allocate space on the disk what type of file am I creating/writing to?
    
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
        
        //start here
       //file.writeNode(newNode);
       this.root=s;
       s.leaf=false;
       s.n=0;
       s.children[0]=start.ownLocation;
       split(s,0,start);
       insertNonFull(s,key);
    }
       else{
        insertNonFull(root,key); 
       }


         }

    /**
     * Splits node if full
     * @param BTreeNode parent node
     * @param  child node location
     */
    public void split(BTreeNode x, int i, BTreeNode y){
      

     
      
    }
    
    /**
     * Nonfull node insertion
     */
    public void insertNonFull(BTreeNode x, Long key){
    




                    
                    
                
            }

    }



