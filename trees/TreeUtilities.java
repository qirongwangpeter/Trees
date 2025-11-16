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
//----------------------------------------------------------------------------
    public boolean isBST(Node<E> n){
        return isBSTHelper(n,null,null);// using max and min to check each branch
    }
    private boolean isBSTHelper(Node<E> n,E min, E max){
        if(n == null){
            return true;
        }else{
            if(min != null && min.compareTo(n.data) >= 0){//if min or max is null, it means that there is no limitations, just pass it.
                return false;
            }
            if(max != null && max.compareTo(n.data) <= 0){
                return false;
            }
            return isBSTHelper(n.left,min,n.data) && isBSTHelper(n.right,n.data,max);
        }
    }
//----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
    public boolean isAVLTree(Node<E> n){
        if(!isBST(n)){
            return false;
        }
        return isAVLTreeHelper(n);
    }
    public boolean isAVLTreeHelper(Node<E> n){//Need to check every subNodes!!!
        if(n == null){
            return true;
        }
        if(!(Math.abs(height(n.right)-height(n.left)) <= 1)){
            return false;
        }
        return isAVLTreeHelper(n.left) && isAVLTreeHelper(n.right);// but this is so inefficient! O(n^2)
    }

    // public boolean efficientIsAVLTree(Node<E> n){ // this version has serious bug.
    //     if(n == null){
    //         return true;
    //     }
    //     if(!isBST(n)){
    //         return false;
    //     }
    //     return checkBalanceAndGetHeight(n) != -1;
    // }
    // private int checkBalanceAndGetHeight(Node<E> n){
    //     if(n == null){// the root is not null, the efficientIsAVLTree already checked!
    //         return -1;
    //     }
    //     int checkOrHeightLeft = checkBalanceAndGetHeight(n.left);
    //     if(checkOrHeightLeft == -1){
    //         return -1;
    //     }
    //     int checkOrHeightRight = checkBalanceAndGetHeight(n.right);
    //     if(checkOrHeightRight == -1){
    //         return -1;
    //     }
    //     if(Math.abs(checkOrHeightRight - checkOrHeightLeft) > 1){
    //         return -1;
    //     }
    //     return 1+Math.max(checkOrHeightLeft,checkOrHeightRight);
    // }
//-----------------------------------------------------------------------------
    public boolean equalSubtrees(Node<E> m, Node<E> n){
        if(m == null && n == null){
            return true;
        }
        if((m == null && n != null) || (m!= null && n == null)){
            return false;
        }
        if(!(m.data.equals(n.data))){
            return false;
        }
        return equalSubtrees(m.left, n.left) && equalSubtrees(m.right, n.right);
    }
    // time spent: six hours and twenty-one minutes consecutively!
}
