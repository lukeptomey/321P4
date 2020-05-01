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
    }
    /**
     * Inserts new node
     */
    public void insert(BTree tree, int keys){
    BTreeNode start = tree.root;
    if(start.n == 2 *order-1){
       BTreeNode s= new BTreeNode(order);
         }
    }
    /**
     * Splits node if full
     */
    public void split(){

    }

}
