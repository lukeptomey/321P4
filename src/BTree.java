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
    public void insert( Long key){
    if(root.numbChildren== 2 *order-1){
       BTreeNode s= new BTreeNode(order);
       this.root=s;
       s.leaf=false;
       s.n=0;
       s.children[0]=start.ownLocation;
       split(s,0,start);
       insertNonFull(s,key);
    }
       else{
        insertNonFull(start,key); 
       }


         }

    /**
     * Splits node if full
     * @param BTreeNode parent node
     * @param  child node location
     */
    public void split(BTreeNode x, int i, BTreeNode y){
        BTreeNode z = new BTreeNode(order);
        y=x.

     
      
    }
    
    /**
     * Nonfull node insertion
     */
    public void insertNonFull(BTreeNode x, Long key){
        int i =x.n;
        BTreeObject tObj= new BTreeObject(key);
            if(x.leaf){
                while(i>0 && key < x.keys[i].dna){ 
                    x.keys[i+1]=x.keys[i];   //shift
                    i--;
                } 
                x.keys[i+1] =key;
                x.n=x.n +1;
                //diskWrite(x)




                    
                    
                
            }

    }

}
