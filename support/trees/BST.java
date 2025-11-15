package support.trees;

public interface BST<E extends Comparable<E>> {
    public int size();
    public void add(E e);
    public void splice(Node<E> node);
    public E remove(E e);
    public Node<E> find(E e, Node<E> node);
    public boolean contains(E e);
}
