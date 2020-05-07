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

/**
 * Btree Constructor
 * @param useCache
 * @param degree
 * @param gbkFilename
 * @param sequenceLength
 * @param cacheSize
 */
public BTree(int useCache, int degree, String gbkFilename, int sequenceLength, int cacheSize){
    this.degree=degree;
try{
    //change sequenceLength to byteLength
    file = new FileRW(gbkFilename + ".btree.data." + sequenceLength + "." + degree, degree,sequenceLength);
}
catch (IOException e){
    System.out.println("File could not be created");
}
 root = file.createNode();
 root.setLeaf(true);
		file.writeNode(root);
		file.setRootLocation(root.getLocation());
}
    
    public BTree(int useCache, FileRW file2, int cacheSize) {
}

	/**
     * Inserts new node
     * @param BTree
     * @param newKey to be inserted
     */
    public void insert(Long key){
    
    for(int i = 0; i < root.getAmountOfKeys(); i++)
    {
        if(key == root.getKeyAtIndex(i).dna)
        {
            root.getKeyAtIndex(i).addFrequency(); //if there is duplicate increment frequency
            file.writeNode(root);
            return;
        }
    }
    //check node if key exits, if so increase frequcy by one    
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
        root = newNode;
    }
       else{
        insertNonFull(root,key); 
       }


         }
    

    /**
     * Splits node if full
     * @param current node to split
     * @param  child node location of full node
     */
    public void split(BTreeNode x, int index){
        //x is parent 
        //y is left child full child
        //z is right child

        y = file.getNode(x.getChildAtIndex(index));
        z = file.createNode(); //create node z
		z.setLeaf(y.checkLeaf());
        z.setNumbOfChildren((degree-1));
       
        //Puts object from full node into right child
        for(int j=0; j < (degree-1 ); j++){ 
            z.setKeyAtIndex(j,y.getKeyAtIndex(j+degree));
        }

            // Move parent node child pointers and add the right child node
        if(!y.checkLeaf()){ 
            for(int j = 0; j < degree; j++) {
                z.setChildAtIndex(j,file.getNode(y.getChildAtIndex(j+degree)));
            }
            y.setNumbOfChildren(degree);
        }
        y.setKeyNumb(degree-1);


        
        for(int j= x.getAmountOfKeys()+1 ; j > index+1; j-- ){ //insert z as a child of x
            x.setChildAtIndex(j+1, file.getNode(x.getChildAtIndex(j)));
        }
        x.setChildAtIndex((index+1), z); 
        for(int j = x.getAmountOfKeys(); j > index; j--){ // move the median key from y up to x in order to separate y from z
            x.setKeyAtIndex( j+1,x.getKeyAtIndex(j));
        }
       x.setKeyAtIndex( index,y.getKeyAtIndex(degree-1));
        x.setKeyNumb(x.getAmountOfKeys() +1);
        x.setNumbOfChildren(x.getAmountOfChildren()+1);

        //writing nodes to file
        file.writeNode(y); 
        file.writeNode(z); //correct
        file.writeNode(x);
    }
    
    /**
     * Nonful node insetion 
     * @param x current nonfull node
     * @param key new key
     */
    public void insertNonFull(BTreeNode x, long key){
     
        for(int i = 0; i < x.getAmountOfKeys(); i++)
    {
        if(key == x.getKeyAtIndex(i).dna)
        {
            x.getKeyAtIndex(i).addFrequency(); //if there is duplicate increment frequency
            file.writeNode(x);
            return;
        }
    }
     // when reading from file fill null keys with empty object @lukeptomey
        int i = x.getAmountOfKeys();
        if (x.checkLeaf()){ //x is a leaf and nonfull, insert k into x directly
         while(i>=0 && key < x.getKeyAtIndex(i).dna ){
             x.setKeyAtIndex(i+1, x.getKeyAtIndex(i));
             i--;
          }
       
        BTreeObject fill = new BTreeObject(key, 1);
          x.setKeyAtIndex(i,fill);
          x.setKeyNumb(x.getAmountOfKeys() +1);
          file.writeNode(x);
         return; //done
     }
     //insert is broken @lukeptomey
        else{
           while(i>0 && key < x.getKeyAtIndex(i-1).dna ){  //find x'x child x.xi to hold the key k
              i--;
     }
   
      y=file.getNode(x.getChildAtIndex(i)); 
    }
     if(y.getAmountOfKeys()== (2*degree)-1){
         split(x, i); //split child of x into 2 nonfull children
         if(key > x.getKeyAtIndex(i).dna){ //determine which of the 2 children is the correct one
          i++;
         }
    }
    insertNonFull(file.getNode(x.getChildAtIndex(i)),key); //recurses to insert k into subtree
 }
/**
 * Searches for query sequences of length k the search returns the frequency of occurency of the query
 * @param x (root node)
 * @param targetKey
 * @return
 */
 public int search(BTreeNode x, long targetKey){
     int i=0;
     while(i < x.getAmountOfKeys() && targetKey > x.getKeyAtIndex(i).dna){ 
    // search from the first key in x node till you find the smallest i that k<=x.keyi
        i++;
     }
     if(i < x.getAmountOfKeys() && targetKey == x.getKeyAtIndex(i).dna){
    //Search hit, return frequency
         return x.getKeyAtIndex(i).frequency;
     }
     else if(x.checkLeaf()){
         return 0; //search miss
     }
     else {
        y=file.getNode(x.getChildAtIndex(i)); //read from file
        return search (y, targetKey);
     }

 }
 /**
  * Returns RWFile with nodes
  * @return FileRW
  */
 public FileRW getFileRW(){
    return file;
}
 /**
  * Returns root Node
  * @return BtreeNode at the root
  */
 //written by Daniel 5/5
 public BTreeNode getBase() {
     return root;
 }
}



