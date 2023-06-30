import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Your implementation of a BST.
 *
 * @author Rahul Snkara
 * @version 1.0
 * @userid rsunkara3
 * @GTID 903401231
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work? for-each
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException();
        }
        for (T element : data) {
            add(element);
        }
    }

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException();
        }
        root = addHelper(root, data);
    }

    /**
     * Helper method for add
     *
     * @param root the root node
     * @param data the data that needs to be added
     * @return current node after change
     */
    private BSTNode addHelper(BSTNode<T> root, T data) {
        BSTNode<T> newNode = new BSTNode<>(data);
        if (root == null) {
            root = newNode;
            size++;
        } else if (root.getData().compareTo(data) < 0) {
            root.setRight(addHelper(root.getRight(), data));
        } else if (root.getData().compareTo(data) > 0) {
            root.setLeft(addHelper(root.getLeft(), data));
        }
        return root;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data. You MUST use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException();
        }
        BSTNode<T>[] returnedNodes = removeHelper(root, data);
        root = returnedNodes[0];
        return returnedNodes[1].getData();
    }

    /**
     * Helper method for remove
     *
     * @param node the node to be removed
     * @param data the data value that the removed node should have
     * @return the node
     */
    private BSTNode[] removeHelper(BSTNode<T> node, T data) {
        BSTNode<T>[] returnedNodes = null;
        if (node == null) {
            throw new java.util.NoSuchElementException();
        } else if (node.getData().compareTo(data) > 0) {
            returnedNodes = removeHelper(node.getLeft(), data);
            node.setLeft(returnedNodes[0]);
        } else if (node.getData().compareTo(data) < 0) {
            returnedNodes = removeHelper(node.getRight(), data);
            node.setRight(returnedNodes[0]);
        } else {
            BSTNode<T> removedNode = new BSTNode<T>(node.getData());
            size--;
            if (node.getRight() != null && node.getLeft() != null) {
                BSTNode<T> successor = new BSTNode<>(null);
                node.setRight(setSuccesssor(node.getRight(), successor));
                node.setData(successor.getData());
                return new BSTNode[]{node, removedNode};
            } else if (node.getRight() != null || node.getLeft() != null) {
                return new BSTNode[]{node.getLeft() == null ? node.getRight() : node.getLeft(), removedNode};
            } else {
                return new BSTNode[]{null, removedNode};
            }
        }
        return new BSTNode[]{node, returnedNodes[1]};
    }


    /**
     * Creates a list containing all data in the BST that is strictly greater
     * than the data passed in.
     *
     * Note that the data passed in may or may not exist in the tree.
     *
     * @param data the data to retrieve values greater than
     * @return a list containing any values from the BST greater than data
     * @throws IllegalArgumentException if the data passed in is null
     */
    public List<T> dataGreaterThan(T data) {
        // IMPLEMENT THIS METHOD
        ArrayList<T> set = new ArrayList<T>();
        if (data == null) {
            throw new java.lang.IllegalArgumentException();
        } else (node.data.compareTo(data) > 0) {
            dataGreaterThan(node.left.data);
            set.add(node.data);
        }
        return set;
    }


    private ArrayList<T> removeAllLeaves(BSTNode<T> node){
        ArrayList<T> leaves = new ArrayList<T>();
        if(node == null) {
            throw new java.util.NoSuchElementException();
        } else if (node.getRight() == null && node.getLeft() == null) {
            leaves.add(node.getData());
        } else {
            removeAllLeaves(node.getLeft());
            removeAllLeaves(node.getRight());
        }
        return leaves;
    }


    private int countGaps(BSTNode<T> node){
       int gaps = 0;
        if(node == null) {
          gaps++;
        } else if (node.getLeft() == null && node.getRight() == null) {
            gaps += 2;
        } else if (node.getLeft() == null || node.getRight() == null) {
            if (node.getRight() == null) {
                countGaps(node.getLeft());
            } else {
                countGaps(node.getRight());
            }
            gaps++;
        }
        return gaps;
    }

    private int sumRightNode(BSTNode<T> node) {
        int sumRight = 0;
        if(node == null){
            throw new java.util.NoSuchElementException();
        } else if (node.getRight() != null) {
            ;
        }
        return sumRight;
    }
    /**
     * Helper method for finding the successor
     *
     * @param node the node of whose successor we're trying to find
     * @param successor the successor node
     * @return the node
     */
    private BSTNode setSuccesssor(BSTNode<T> node, BSTNode<T> successor) {
        if (node.getLeft() == null) {
            successor.setData(node.getData());
            return node.getRight();
        } else {
            node.setLeft(setSuccesssor(node.getLeft(), successor));
            return node;
        }
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException();
        }
        return getHelper(root, data);
    }

    /**
     * Helper method for get
     *
     * @param root the root node
     * @param data the data value we're looking for
     * @return the data
     */
    private T getHelper(BSTNode root, T data) {
        if (root == null) {
            throw new java.util.NoSuchElementException();
        }
        if (root.getData().compareTo(data) == 0) {
            return (T) root.getData();
        } else if (root.getData().compareTo(data) > 0) {
            return  getHelper(root.getLeft(), data);
        } else {
            return getHelper(root.getRight(), data);
        }
    }


    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException();
        }
        return containsHelper(root, data);
    }

    /**
     * Helper for contains
     *
     * @param root the root node
     * @param data the data we're looking for
     * @return true or false depending on if we find it
     */
    private boolean containsHelper(BSTNode<T> root, T data) {
        if (root.getData().compareTo(data) == 0) {
            return true;
        } else if (root.getData().compareTo(data) > 0) {
            return containsHelper(root.getLeft(), data);
        } else {
            return containsHelper(root.getRight(), data);
        }
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        ArrayList<T> preorderList = new ArrayList<>();
        preorderHelper(root, preorderList);
        return preorderList;
    }

    /**
     * Helper for PreOrder
     *
     * @param node the inital node
     * @param preorderList the order after done
     */
    private void preorderHelper(BSTNode<T> node, ArrayList<T> preorderList) {
        if (node == null) {
            return;
        }
        if (node.getRight() == null && node.getLeft() == null) {
            preorderList.add(node.getData());
        } else {
            preorderList.add(node.getData());
            preorderHelper(node.getLeft(), preorderList);
            preorderHelper(node.getRight(), preorderList);
        }
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        ArrayList<T> inorderList = new ArrayList<>();
        inorderHelper(root, inorderList);
        return inorderList;
    }

    /**
     * Helper for inOrder
     *
     * @param node the inital node
     * @param inorderList the order after done
     */
    private void inorderHelper(BSTNode<T> node, ArrayList<T> inorderList) {
        if (node == null) {
            return;
        }
        if (node.getRight() == null && node.getLeft() == null) {
            inorderList.add(node.getData());
        } else {
            inorderHelper(node.getLeft(), inorderList);
            inorderList.add(node.getData());
            inorderHelper(node.getRight(), inorderList);
        }
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        ArrayList<T> postorderList = new ArrayList<>();
        postOrderHelper(root, postorderList);
        return postorderList;
    }

    /**
     * Helper for postOrder
     *
     * @param node the inital node
     * @param postorderList the order after done
     */
    private void postOrderHelper(BSTNode<T> node, ArrayList<T> postorderList) {
        if (node == null) {
            return;
        }
        if (node.getRight() == null && node.getLeft() == null) {
            postorderList.add(node.getData());
        } else {
            postOrderHelper(node.getLeft(), postorderList);
            postOrderHelper(node.getRight(), postorderList);
            postorderList.add(node.getData());
        }
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        ArrayList<T> levelorderList = new ArrayList<>();

        LinkedList<BSTNode<T>> levelorderQueue = new LinkedList<>();
        levelorderQueue.add(root);
        while (levelorderQueue.size() != 0) {
            BSTNode<T> topElement = levelorderQueue.remove(levelorderQueue.size() - 1);
            if (topElement != null) {
                levelorderList.add(topElement.getData());
                levelorderQueue.addFirst(topElement.getLeft());
                levelorderQueue.addFirst(topElement.getRight());
            }
        }
        return levelorderList;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return heightHelper(root);
    }

    /**
     * Helper for height
     *
     * @param node the inital node
     * @return the height of the node
     */
    private int heightHelper(BSTNode<T> node) {
        if (node == null) {
            return -1;
        } else {
            return java.lang.Math.max(heightHelper(node.getLeft()), heightHelper(node.getRight())) + 1;
        }
    }
    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Determines if a binary tree is a valid BST.
     *
     * This must be done recursively. Do NOT modify the tree passed in.
     *
     * If the order property is violated, this method may not need to traverse
     * the entire tree to function properly, so you should only traverse the
     * branches of the tree necessary to find order violations and only do so once.
     * Failure to do so will result in an efficiency penalty.
     *
     * EXAMPLES: Given the BST below composed of Integers:
     *
     *                50
     *              /    \
     *            25      75
     *           /  \
     *          12   37
     *         /  \    \
     *        10  15    40
     *           /
     *          13
     *
     * isBST(50) should return true, since for every node, the node's left
     * subtree is less than the node's data, and the node's right subtree is
     * greater than the node's data.
     *
     *             20
     *           /    \
     *         21      38
     *        /          \
     *       6          50
     *        \
     *         12
     *
     * isBST(20) should return false, since 21 is in 20's left subtree.
     *
     *
     * Should have a worst-case running time of O(n).
     *
     * @param node the root of the binary tree
     * @return true if the tree with node as the root is a valid BST,
     *         false otherwise
     */
    public boolean isBST(BSTNode<T> node) {
        return isBSTHelper(node);
    }

    /**
     * Helper method to determine if it is a BST
     *
     * @param root the root of BST
     * @return true if is and false if isn't
     */
    private boolean isBSTHelper(BSTNode<T> root) {
        if (root == null) {
            return false;
        } else if (root.getLeft().getData().compareTo(root.getData()) < 0
                    && root.getRight().getData().compareTo(root.getData()) > 0) {
            return true;
        } else {
            return false;
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
    public BSTNode<T> getRoot() {
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
