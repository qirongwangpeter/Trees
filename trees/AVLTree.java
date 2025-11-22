package trees;
import support.trees.*;

public class AVLTree<E extends Comparable<E>> implements AVLT<E>{
    Node<E> root;
    int size;

    public int size(){
        return size;
    }

    public int height(Node<E> node){
        if( node == null){
            return -1;
        }
        return 1+Math.max(height(node.left),height(node.right));
    }

    public Node<E> find(Node<E> node, E e){
        if(node == null){// the first two said that only two cases:contain or not.
            return null;
        }
        if(e.equals(node.data)){
            return node;
        }
        if(e.compareTo(node.data) < 0){//these two bring the recursion to the correct half.
            return find(node.left, e);
        }else{
            return find(node.right,e);
        }
    }

    public E find(E e){
        return find(root, e).data;
    }

    public void add(E e){
        if(root == null){
            root = new Node<>(e);
        }
        add(root,e);
    }
    private void add(Node<E> node, E e){// will add the parent and the insersioncheck method later.
        if(e.equals(node.data)){// first, deal with the same data, just swap the same data.
            node.data = e;
        }else{//since there no more equals, check if it is greater or smaller.
            if(e.compareTo(node.data) < 0){
                if(node.left == null){//if the place we are going to add is null, we can add!
                    node.left = new Node<>(e);
                    insertioncheck(node.left);//hints:we call .left or right so it won't just change the local variable node. It will change the tree!
                    size++;
                    return;
                }else{//if not, we have to go to the next place, the left
                    add(node.left,e);
                }
            }else{
                if(node.right == null){
                    node.right = new Node<>(e);
                    insertioncheck(node.right);
                    size++;
                    return;
                }else{
                    add(node.right,e);
                }
            }
        }
    }
    private void rotateleft(Node<E> node){
        if(node == null || node.right == null){
            return;//if there is only one or no element starting at the place you want to rotate, nothing will change.
        }
        Node<E> A, B, T1, T2, T3;
        A = node;
        B = A.right;
        T1 = A.left;
        T2 = B.left;
        T3 = B.right;
        
        if(A == root){
            root = B;
            B.parent = null;//put B in the right place first, also its parent.
        }else{
            if(A == A.parent.left){//left or right? we need if.
                A.parent.left = B;
                B.parent = A.parent;
            }else{
                A.parent.right = B;
                B.parent = A.parent;
            }
        }
        B.left = A;//dealing with A
        A.parent = B;// and its parent.

        A.right = T2;// dealing with B.left, which is already assigned by T2
        if(T2 != null){//dealing with T2's parent unless it is a null.
            T2.parent = A;
        }
    }

    private void rotateright(Node<E> node){

    }

}
