import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL.
 *
 * @author Rahul Sunkara
 * @version 1.0
 * @userid rsunkara3
 * @GTID 903401231
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class AVL<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVL(Collection<T> data) {
        if(data == null){
            throw new IllegalArgumentException("Data is null, which it can't be");
        }

        size = 0;
        data.forEach(this::add);
    }

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if(data == null){
            throw new IllegalArgumentException("Data is null, which it can't be");
        }
         root = addHelper(data, root);
    }

    /**
     * Helper method for add
     *
     * @param data the data to be added
     * @param node the root
     * @return the node which is balanced
     */
    private AVLNode<T> addHelper(T data, AVLNode<T> node) {
        if (node == null) {
            size++;
            return new AVLNode<>(data);
        }

        int dummy = data.compareTo(node.getData());
        if (dummy < 0) {
            node.setLeft(addHelper(data, node.getLeft()));
        } else if (dummy > 0) {
            node.setRight(addHelper(data, node.getRight()));
        } else {
            return node;
        }
        calculate(node);
        return balanceNodes(node);
    }

    /**
     * Balances the nodes
     *
     * @param node the node to be balanced
     * @return the balanced node
     */
    private AVLNode<T> balanceNodes(AVLNode<T> node) {
        if (node.getBalanceFactor() < -1) {
            if (node.getRight().getBalanceFactor() > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            node = rotateLeft(node);
        } else if (node.getBalanceFactor() > 1) {
            if (node.getLeft().getBalanceFactor() < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            node = rotateRight(node);
        }
        return node;
    }

    /**
     * Right rotation method
     *
     * @param node the node to be rotated
     * @return node after right rotation
     */
    private AVLNode<T> rotateRight(AVLNode<T> node) {
        AVLNode<T> dummy = node.getLeft();
        node.setLeft(dummy.getRight());
        dummy.setRight(node);
        calculate(node);
        calculate(dummy);
        return dummy;
    }

    /**
     * Left rotation method
     *
     * @param node the node to be rotated
     * @return node after right rotation
     */
    private AVLNode<T> rotateLeft(AVLNode<T> node) {
        AVLNode<T> dummy = node.getRight();
        node.setRight(dummy.getLeft());
        dummy.setLeft(node);
        calculate(node);
        calculate(dummy);
        return dummy;
    }

    /**
     * A mystery method that prints to the console! :)
     *
     * @param current the current node being examined
     */
    public void mystery(AVLNode<T> current) {
        if (current != null) {
            if (current.getBalanceFactor() < 0) {
                System.out.println(current.getData() + " " + current.getBalanceFactor());
                mystery(current.getRight());
            } else if (current.getBalanceFactor() > 0) {
                System.out.println(current.getData() + " " + current.getBalanceFactor());
                mystery(current.getLeft());
            } else {
                System.out.println(current.getHeight());
                mystery(current.getLeft());
                mystery(current.getRight());
            }
        }
    }

    private void calculate(AVLNode<T> node) {
        int LH = heighHelper(node.getLeft());
        int RH = heighHelper(node.getRight());
        node.setHeight(Math.max(LH, RH) + 1);
        node.setBalanceFactor(LH - RH);
    }
    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     *    simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     *    replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     *    replace the data, NOT successor. As a reminder, rotations can occur
     *    after removing the predecessor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if(data == null){
            throw new IllegalArgumentException("Data is null, which it can't be");
        }
        AVLNode<T> removedNode = new AVLNode<>(null);
        root = removeHelper(data, root, removedNode);
        return removedNode.getData();
    }

    /**
     * Remove helper method
     *
     * @param data the data needed to be removed
     * @param node the root that need to be checked
     * @param removedNode node that has the removed datta
     * @return the previous node of the removed node
     */
    private AVLNode<T> removeHelper(T data, AVLNode<T> node, AVLNode<T> removedNode) {
        if (node == null) {
            throw new IllegalArgumentException("Data is null, which it can't be");
        }
        int dummy = data.compareTo(node.getData());
        if (dummy < 0) {
            node.setLeft(removeHelper(data, node.getLeft(), removedNode));
        } else if (dummy > 0) {
            node.setRight(removeHelper(data, node.getRight(), removedNode));
        } else {
            removedNode.setData(node.getData());
            size--;
            if(node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                AVLNode<T> kid = new AVLNode<>(null);
                node.setRight(nextHelper(node.getRight(), kid));
                node.setData(kid.getData());
            }
        }
        calculate(node);
        return balanceNodes(node);
    }

    /**
     * Helper method to find next
     *
     * @param node node to check
     * @param kid the kid of the removed node
     * @return the next node of removed node
     */
    private AVLNode<T> nextHelper(AVLNode<T> node, AVLNode<T> kid) {
        if (node.getLeft() == null) {
            kid.setData(node.getData());
            return node.getRight();
        }
        node.setLeft(nextHelper(node.getLeft(), kid));
        calculate(node);
        return balanceNodes(node);
    }
    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if(data == null) {
            throw new IllegalArgumentException("Data is null, which it can't be");
        }
        return getHelper(data, root);
    }

    /**
     * Helper method for get
     *
     * @param data searching data
     * @param node node to check
     * @return the data of the node
     */
    private T getHelper(T data, AVLNode<T> node) {
        if (node == null) {
            throw new IllegalArgumentException("Data is null, which it can't be");
        }
        int dummy = data.compareTo(node.getData());
        if (dummy > 0) {
            return getHelper(data, node.getRight());
        } else if (dummy < 0) {
            return getHelper(data, node.getLeft());
        } else {
            return node.getData();
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        try {
            get(data);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns the height of the root of the tree. Do NOT compute the height
     * recursively. This method should be O(1).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return heighHelper(root);
    }

    /**
     * Height helper to get specific node's height
     *
     * @param node the node to get the height of
     * @return the height of node
     */
    private int heighHelper(AVLNode<T> node) {
        return node != null ? node.getHeight() : -1;
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the data on branches of the tree with the maximum depth. If you
     * encounter multiple branches of maximum depth while traversing, then you
     * should list the remaining data from the left branch first, then the
     * remaining data in the right branch. This is essentially a preorder
     * traversal of the tree, but only of the branches of maximum depth.
     *
     * Your list should not contain duplicate data, and the data of a branch
     * should be listed in order going from the root to the leaf of that branch.
     *
     * Should run in worst case O(n), but you should not explore branches that
     * do not have maximum depth. You should also not need to traverse branches
     * more than once.
     *
     * Hint: How can you take advantage of the balancing information stored in
     * AVL nodes to discern deep branches?
     *
     * Example Tree:
     *                           10
     *                       /        \
     *                      5          15
     *                    /   \      /    \
     *                   2     7    13    20
     *                  / \   / \     \  / \
     *                 1   4 6   8   14 17  25
     *                /           \          \
     *               0             9         30
     *
     * Returns: [10, 5, 2, 1, 0, 7, 8, 9, 15, 20, 25, 30]
     *
     * @return the list of data in branches of maximum depth in preorder
     * traversal order
     */
    public List<T> deepestBranches() {
        List<T> list = new ArrayList<>();
        deepestBranchesHelper(list, root);
        return list;
    }

    private void deepestBranchesHelper(List<T> list, AVLNode<T> node) {
        if (node == null) {
            return;
        } else {
            list.add(node.getData());
            if (node.getLeft() != null && !(node.getHeight() - node.getLeft().getHeight() > 1)) {
                deepestBranchesHelper(list, node.getLeft());
            }
            if (node.getRight() != null && !(node.getHeight() - node.getRight().getHeight() > 1)) {
                deepestBranchesHelper(list, node.getRight());
            }
        }
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}