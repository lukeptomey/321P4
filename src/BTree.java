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
    //Allocate space on the disk
    }
    /**
     * Inserts new node
     * @param BTree
     * @param key
     */
    public void insert(BTree tree, BTreeObject
     keys){
    BTreeNode start = tree.root;
    if(start.n == 2 *order-1){
       BTreeNode s= new BTreeNode(order);
       tree.root=s;
       s.leaf=false;
       s.n=0;
       s.children[0]=start.ownLocation;
       split(s,1);
       B-Tree-Insert-Nonfull(s,key);
    }
       else{
        B-Tree-Insert-Nonfull(root,key); 
       }


         }

    /**
     * Splits node if full
     */
    public void split(BTreeNode x, int i){

    }
    
    /**
     * Nonfull ode insertion
     */

}
