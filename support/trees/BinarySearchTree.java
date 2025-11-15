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
            return find(e,n.left); // easy to forget to write return.
        }else{
            return find(e,n.right);// easy to forget to write return.
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
            root.parent = null;//not necessary but just to clearify.
            size++;
            return;
        }
        add(e,root);
    }
    private void add(E e, Node<E> n){ //remember n is a variable pointed to the root.
        if(e.equals(n.data)){         //do not just reassign the Node to n or you do nothing!!!
            n.data = e;
            return;
        }
        if(e.compareTo(n.data) < 0){
            if(n.left == null){
                n.left = new Node<E>(e);
                n.left.parent = n;
                size++;
            }else{
                add(e,n.left);
            }
        }else{
            if(n.right == null){
                n.right = new Node<E>(e);
                n.right.parent = n;
                size++;
            }else{
                add(e,n.right);
            }
        }
    }
    private void splice(Node<E> n){// means just remove Node n from the tree
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
            c = n.right; // also if n.right is null, c is null.
        }
        if(n == root){
            root = c;
            p = null;// will be assigned in the end.
        }else{
            p = n.parent;
            if(p.left == n){//p.left != null won't work here!!!
                p.left = c; //(What if p has two children?) one of the children could has one child and thus using splice!
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
        if(n.left == null || n.right==null){ //easy to mess up the logic here!
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