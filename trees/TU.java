package trees;
import java.util.List;
import support.trees.*;

public interface TU<E extends Comparable<E>> {
    public  List<E> inOrder(Node<E> node);
    public  int height(Node<E> n);
    public  BinarySearchTree<E> intoBalanced(BinarySearchTree<E> bst);
    public  boolean isBST(Node<E> n);
    public  boolean isAVLTree(Node<E> n);
    public  boolean equalSubtrees(Node<E> n,Node<E> m);
}
