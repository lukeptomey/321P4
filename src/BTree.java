/**
 * BTree
 * @author Luke Ptomey
 * BTree that holds BTreeObjects
 */
public class BTree {
    int order;
    BTreeNode root;

public BTree(int order){
    this.order=order;
    root = new BTreeNode(order);
    root.setIfLeaf(true);
    
    //Allocate space on the disk what type of file am I creating/writing to?
    }
    /**
     * Inserts new node
     * @param BTree
     * @param key to be inserted
     */
    public void insert(BTree tree, Long key){
    BTreeNode start = tree.root;
    if(start.n == 2 *order-1){
       BTreeNode s= new BTreeNode(order);
       tree.root=s;
       s.leaf=false;
       s.n=0;
       s.children[0]=start.ownLocation;
       split(s,1);
       insertNonFull(s,key);
    }
       else{
        insertNonFull(start,key); 
       }


         }

    /**
     * Splits node if full
     * @param BTreeNode
     * @param  int key to be inserted
     */
    public void split(BTreeNode x, int y){

    }
    
    /**
     * Nonfull node insertion
     */
    public void insertNonFull(BTreeNode x, Long key){
        int i =x.n;
            if(x.leaf){
               //key variable type??? while(i>=1 && key < x.keys[i]{ 
                   

                    
                    
                
            }

    }

}
