/*
 * CS2050 - Computer Science II - Spring 2020
 * Instructor: Thyago Mota
 * Description: Prg03 - BinaryTree class
 * Name(s):
 */

import java.util.Iterator;

public class BinaryTree<T extends Comparable<T>> implements Iterable<T> {

    private BinNode<T> root;

    public BinaryTree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private BinNode<T> addRecursively(BinNode<T> current, final T data) {
        if (current == null)
            return new BinNode<T>(data);
        else {
            if (data.compareTo(current.getData()) <= 0)
                current.setLeft(addRecursively(current.getLeft(), data));
            else if (data.compareTo(current.getData()) > 0)
                current.setRight(addRecursively(current.getRight(), data));
            return current;
        }
    }

    public void add(final T data) {
        if (isEmpty())
            root = new BinNode<T>(data);
        else
            addRecursively(root, data);
    }

    private String inOrder(final BinNode<T> current) {
        if (current != null)
            return inOrder(current.getLeft()) +
                    current.getData() + " " +
                    inOrder(current.getRight());
        return "";
    }

    // TODO: implement the toString method based on inOrder tree traversal
    @Override
    public String toString() {
        return inOrder(this.root);
    }

    // TODOd: implement the clear method
    public void clear()
    {
        clear(root);
    }
    // clear() helper
    private void clear(BinNode<T> current)
    {
        if(current == null)
        {
            return;
        }
        current.setData(null);
        clear(current.getRight());
        clear(current.getLeft());
        current.setLeft(null);
        current.setRight(null);
    }

    // TODO: implement a binary tree iterator
    @Override
    public Iterator<T> iterator()  { // class within a class (1)

        return new Iterator<T>() {
            BinNode<T> parent = root;
            Queue<BinNode<T>> queue = new Queue<>(parent);

            //Can have variables within this class.
            @Override
            public boolean hasNext() {
               return !queue.isEmpty();
            }

            @Override
            public T next() {
                if(hasNext()){
                    parent = queue.pop();
                    T data = parent.getData();
                    if(parent.getLeft() != null){
                        queue.push(parent.getLeft());
                    }
                    if(parent.getRight() != null){
                        queue.push(parent.getRight());
                    }
                    return data;
                }
                return null;
            }
        }; //(1)
    }
}