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
    public Node<E> find(E e, Node<E> n){
        if(n == null){
            return null;
        }else if(n.data.equals(e)){
            return n;
        }else if(e.compareTo(n.data)< 0){
            return find(e,n.left);
        }else{
            return find(e,n.right);
        }
    }
    public Node<E> find(E e){
        return find(e,root);
    }
    public boolean contains(E e){
        return find(e) != null;
    }
    public void add(E e){
        if(root == null){
            root = new Node<E>(e);
            size++;
            return;
        }
        add(e,root);
    }
    private void add(E e, Node<E> n){
        if(e.equals(n.data)){
            n.data = e;
            return;
        }
        if(e.compareTo(n.data) < 0){
            if(n.left == null){
                n.left = new Node<E>(e);
                size++;
            }else{
                add(e,n.left);
            }
        }else{
            if(n.right == null){
                n.right = new Node<E>(e);
                size++;
            }else{
                add(e,n.right);
            }
        }
    }
    public void splice(Node<E> n){
        if(n == null){
            return;
        }
        if(n.right != null && n.left != null){
            throw new IllegalArgumentException();
        }
        Node<E> p;
        Node<E> c;
        if(n.left != null){
            c = n.left;
        }else{
            c = n.right;
        }
        if(n == root){
            root = c;
            p = null;
        }else{
            p = n.parent;
            if(p.left == n){//p.left != null won't work here!!!
                p.left = c;
            }else{
                p.right = c;
            }
        }
        if(c != null){
            c.parent = p;
        }
    }
    public E remove(E e){
        Node<E> n = find(e);
        if(n == null){
            return null;
        }
        Node<E> successor;
        E toReturn = n.data;
        if(n.left == null || n.right==null){
            splice(n);
        }else{
            successor = n.right;
            while(successor.left != null){
                successor = successor.left;
            }
            n.data = successor.data;
            splice(successor);
        }
        size--;
        return toReturn;
    }
}