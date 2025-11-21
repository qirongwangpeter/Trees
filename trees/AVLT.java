package trees;
import support.trees.*;
public interface AVLT<E extends Comparable<E>> {
    int size();
    int height();
    boolean isAVL();
    boolean contains();
    Node<E> find();
    E get();
    void add();
    E remove();
    
}
