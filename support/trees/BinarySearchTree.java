package support.trees;
public class BinarySearchTree<E extends Comparable<E>> implements BST<E>{
    Node<E> root;
    int size;
    public BinarySearchTree(){
        size = 0;
    }
    public int size(){
        return size;
    }
    public Node<E> find(E e, Node<E> root){
        if(root == null){
            return null;
        }else if(root.data.equals(e)){
            return root;
        }else if(e.compareTo(root.data)< 0){
            find(e,root.left);
        }else{
            find(e,root.right);
        }
    }
    public Node<E> find(E e){
        return find(e,root);
    }

}