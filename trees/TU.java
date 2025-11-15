package trees;
import java.util.List;
import support.trees.*;

public interface TU {
    public <E> List<E> inOrder(Node<E> node);
    public <E> int height(Node<E> n);
    public <E extends Comparable<E>> BinarySearchTree<E> intoBalanced(BinarySearchTree<E> bst);
    public <E extends Comparable<E>> boolean isBST(Node<E> n);
    public <E extends Comparable<E>> boolean isAVLTree(Node<E> n);
    public <E> boolean equalSubtrees(Node<E> n,Node<E> m);
}
