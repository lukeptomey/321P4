
public class BTree {
    int order;
    BTreeNode root;

public BTree(int order){
    this.order=order;
    root = new BTreeNode(order,null);
    }
}
