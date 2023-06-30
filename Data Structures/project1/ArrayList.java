import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayList.
 *
 * @author Rahul Sunkara
 * @version 1.0
 * @userid rsunkara3
 * @GTID 903401231
 *
 * Collaborators: Nathaniel Greve, Alfonso Garcia, and public JUnit tests, and Stack Overflow
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class ArrayList<T> {

    /**
     * The initial capacity of the ArrayList.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    // Do not add new instance variables or modify existing ones.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * Java does not allow for regular generic array creation, so you will have
     * to cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Adds the element to the specified index.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be amortized O(1) for index size and O(n) for all other cases.
     *
     * @param index the index at which to add the new element
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
        }
        //checking to see if the array has reached capacity, if so double capacity
        if (size == backingArray.length) {
            doublingTheBackingArray();
        }
        //moving the index positions right
        for (int i = size; i > index; i--) {
            backingArray[i] = backingArray[i - 1];
        }
        //if everything is good, then adding the data at the mentioned index
        backingArray[index] = data;
        //updating the size
        size++;

    }

    /**
     * Helper method.
     *
     * Doubles the backingArray if the size becomes equal to the
     * capacity to allow for more elements to be added in.
     *
     * Must be O(1).
     */
    private void doublingTheBackingArray() {
        T[] new2xArray = (T[]) new Object[backingArray.length * 2];
        for (int i = 0; i < backingArray.length; i++) {
            new2xArray[i] = backingArray[i];
        }
        backingArray = new2xArray;
    }

    /**
     * Adds the element to the front of the list.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be O(n).
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
        addAtIndex(0, data);
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be amortized O(1).
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
        addAtIndex(size, data);
    }

    public void swapPairs() {
        for (int i = 0; i < size - 1; i += 2) {
            T temp = this.backingArray[i];
            this.backingArray[i] = this.backingArray[i + 1];
            this.backingArray[i + 1] = temp;
            System.out.println(backingArray.toString());
        }
    }


    /**
     * Removes and returns the element at the specified index.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(1) for index size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        //checking to see if the index is outOfBounds
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        T removingData = backingArray[index];
        //moving all the elements left
        for (int i = index; i < size - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        //setting last element to null
        backingArray[size - 1] = null;
        //updating the size
        size--;
        //returning the deleted data
        return removingData;
    }

    /**
     * Removes and returns the first element of the list.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(n).
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
        return removeAtIndex(0);
    }

    /**
     * Removes and returns the last element of the list.
     *
     * Must be O(1).
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
        return removeAtIndex(size - 1);
    }

    /**
     * Returns the element at the specified index.
     *
     * Must be O(1).
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        //checking to see if the index is outOfBounds
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return backingArray[index];
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
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the backing array of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
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
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
