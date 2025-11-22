package trees;
import support.trees.*;
public interface AVLT<E extends Comparable<E>> {
    int size();
    int height(Node<E> n);
    boolean isAVL();
    boolean contains();
    Node<E> find(Node<E> node, E e);
    E get();
    void add();
    E remove();
    
}
