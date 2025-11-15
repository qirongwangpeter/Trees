package support.trees;
public class Node<E>{
    public Node<E> parent;
    public Node<E> left;
    public Node<E> right;
    public E data;
    public Node(E data){
        this.data = data;
    }
}
