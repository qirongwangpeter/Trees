package trees;
import support.trees.*;
public interface AVLT<E extends Comparable<E>> {
    int size();
    int height(Node<E> n);
    boolean isAVL(Node<E> n);
    boolean contains(E e);
    Node<E> find(E e);
    E get(E e);
    void add(E e);
    E remove(E e);
}
