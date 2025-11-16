package trees;
import java.util.List;
import java.util.ArrayList;
import support.trees.*;
public class TreeUtilities<E extends Comparable<E>> implements TU<E>{
    //#1
    public List<E> inOrder(Node<E> node){// each recursion created a new ArrayList
        if(node == null){
            return new ArrayList<>();
        }
        List<E> toReturn = new ArrayList<>();
        toReturn.addAll(inOrder(node.left));
        toReturn.add(node.data);
        toReturn.addAll(inOrder(node.right));
        return toReturn;
    }
//----------------------------------------------------------------------------
    //#2
    public List<E> efficientInOrder(Node<E> node){//only created one ArrayList, high efficiency.
        List<E> list = new ArrayList<>();
        efficientInOrderHelper(node,list);
        return list;
    }
    private void efficientInOrderHelper(Node<E> node,List<E> list){// each recursion stored data to the same list "list"
        if(node == null){
            return;
        }
        efficientInOrderHelper(node.left,list);
        list.add(node.data);
        efficientInOrderHelper(node.right,list);
    }
//----------------------------------------------------------------------------
    public int height(Node<E> n){
        if(n == null){
            return -1;
        }
        return 1+Math.max(height(n.left),height(n.right));// only accumulate the higher value.
    }
//-----------------------------------------------------------------------------
    public BinarySearchTree<E> intoBalanced(BinarySearchTree<E> bst){
        if(bst.getRoot() == null){
            return new BinarySearchTree<>();
        }
        List<E> inordered = inOrder(bst.getRoot());
        Node<E> newRootNode = intoBalancedHelper(inordered,0,inordered.size()-1);
        BinarySearchTree<E> newBst = new BinarySearchTree<>();
        newBst.setRoot(newRootNode);// remember to assign the "root" and the "size".
        newBst.setSize(bst.size());// go back and read the BinarySearchTree.java to see how it is constructed.
        return newBst;
    }
    private Node<E> intoBalancedHelper(List<E> list,int left, int right){
        if(left > right){// this is reasonable. When nothing left the child of the current node is null! We just assign null to its child.
            return null;
        }
        int mid = left + (right - left)/2;
        Node<E> n = new Node<>(list.get(mid));//each recursion will start a new root.
        n.left = intoBalancedHelper(list, left, mid - 1);
        if(n.left != null){//clear logic of avoiding null pointer exception!
            n.left.parent = n;
        }//no need to set the root's parent.(first recursion)
        n.right = intoBalancedHelper(list, mid + 1, right);
        if(n.right != null){// same here!
            n.right.parent = n;
        }
        return n;
    }
//-----------------------------------------------------------------------------
    public boolean isBST(Node<E> n){
        return isBSTHelper(n,null,null);
    }
    private boolean isBSTHelper(Node<E> n,E min, E max){//this is just for thinking process, do not deal with the null pointer exception first.
        if(n == null){
            return true;
        }else{
            if(!(min.compareTo(n.data) < 0 && max.compareTo(n.data) > 0)){//do not deal with the null pointer exception first.
                return false;
            }
            return isBSTHelper(n.left,min,n.data) && isBSTHelper(n.right,n.data,max);
        }

    }
}
