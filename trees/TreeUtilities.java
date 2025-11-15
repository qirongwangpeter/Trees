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
    public List<E> efficientInOrder(Node<E> node){//only created one ArrayList
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
    public static <E> int height(Node<E> n){

    }
}
