package support.trees;
public class Node<E>{
    Node<E> parent;
    Node<E> left;
    Node<E> right;
    E data;
    public Node(E data){
        this.data = data;
    }
}
