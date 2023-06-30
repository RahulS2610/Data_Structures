import java.util.NoSuchElementException;

/**
 * Your implementation of a CircularSinglyLinkedList without a tail pointer.
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
public class CircularSinglyLinkedList<T> {


    // Do not add new instance variables or modify existing ones.
    private CircularSinglyLinkedListNode<T> head;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        //checking to see if the data is a null, if so throwing an illegalArgumentException
        if (data == null) {
            throw new IllegalArgumentException();
        }
        //checking to see if the index is outOfBounds
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            size++;
            CircularSinglyLinkedListNode<T> dummy = head;
            for (int i = 1; i < index; i++) {
                dummy = dummy.getNext();
            }
            CircularSinglyLinkedListNode<T> newNode = new CircularSinglyLinkedListNode<T>(data);
            newNode.setNext(dummy.getNext());
            dummy.setNext(newNode);
        }


    }

    /**
     * Adds the data to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        //checking to see if the data is a null, if so throwing an illegalArgumentException
        if (data == null) {
            throw new IllegalArgumentException();
        }
        //if data not a null, then adding it to the front
        if (head == null) {
            head = new CircularSinglyLinkedListNode<T>(data);
            head.setNext(head);
            size = 1;
        } else {
            CircularSinglyLinkedListNode<T> newHead = new CircularSinglyLinkedListNode<T>(null);
            newHead.setNext(head.getNext());
            head.setNext(newHead);

            //moving the data
            newHead.setData(head.getData());
            head.setData(data);
            size++;
        }
    }

    /**
     * Adds the data to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        //checking to see if the data is a null, if so throwing an illegalArgumentException
        if (data == null) {
            throw new IllegalArgumentException();
        }
        //if data not a null, then adding it to the back
        if (head == null) {
            addToFront(data);
            size = 1;
        } else {
            CircularSinglyLinkedListNode<T> newTail = new CircularSinglyLinkedListNode<T>(null);
            newTail.setNext(head.getNext());
            head.setNext(newTail);

            //moving the data
            newTail.setData(head.getData());
            head.setData(data);
            head = head.getNext();
            size++;
        }
    }

    /**
     * Removes and returns the data at the specified index.
     *
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        //checking to see if the index is outOfBounds
        T backupData;
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            backupData = removeFromFront();
        } else if (index == size) {
            backupData = removeFromBack();
        } else {
            CircularSinglyLinkedListNode<T> dummy = head;
            for (int i = 1; i < index; i++) {
                dummy = dummy.getNext();
            }
            backupData = dummy.getNext().getData();
            dummy.setNext(dummy.getNext().getNext());
            size--;
        }
        return backupData;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        //checking to see if the list is empty
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        //if not empty then removing the first element
        T backupData = head.getData();
        if (size == 1) {
            head = null;
            size--;
        } else {
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
            size--;
        }
        return backupData;
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        //checking to see if the list is empty
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        //if not empty then removing the last element
        T backupData = head.getData();
        if (size == 1) {
            removeFromFront();
        } else {
            CircularSinglyLinkedListNode<T> dummy = head;
            for (int i = 1; i < size - 1; i++) {
                dummy = dummy.getNext();
            }
            backupData = dummy.getNext().getData();
            dummy.setNext(head);
            size--;
        }
        return backupData;
    }

    /**
     * Returns the data at the specified index.
     *
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        //checking to see if the index is outOfBounds
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        CircularSinglyLinkedListNode<T> dummy = head;
        for (int i = 1; i <= index; i++) {
            dummy = dummy.getNext();
        }
        return dummy.getData();
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {

        return size == 0;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        } else {
            CircularSinglyLinkedListNode<T> dummy = head;
            int lastOcc = -1;
            for (int i = 1; i < size; i++) {
                if (dummy.getData().equals(data)) {
                    lastOcc = i - 1;
                }
                dummy = dummy.getNext();
            }
            if (lastOcc == -1) {
                throw new NoSuchElementException();
            } else {
                return removeAtIndex(lastOcc);
            }
        }
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {
        T[] array = (T[]) new Object[size];
        CircularSinglyLinkedListNode<T> dummy = head;
        for (int i = 0; i < size; i++) {
            array[i] = dummy.getData();
            dummy = dummy.getNext();
        }
        return array;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public CircularSinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
