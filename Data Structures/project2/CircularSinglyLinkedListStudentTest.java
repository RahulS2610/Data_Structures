import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

public class CircularSinglyLinkedListStudentTest {
    private static final int TIMEOUT = 200;
    private CircularSinglyLinkedList<String> list;

    @Before
    public void setUp() {
        list = new CircularSinglyLinkedList<>();
    }

    @Test (timeout = TIMEOUT)
    public  void testAddAtIndex1() {
        list.addAtIndex(0,"h");
        list.addAtIndex(1,"hey");
        assertEquals(2, list.size());
        CircularSinglyLinkedListNode<String> temp = list.getHead();
        assertEquals(list.getHead(),list.getHead().getNext().getNext());
    }
    @Test (timeout = TIMEOUT)
    /**
     * Test to see if your head will point back to itself after adding
     * with AddAtIndex.
     */
    public void AddAtIndexHeadPontAtItSelfCheck() {
        list.addAtIndex(0, "Did I Work?");
        CircularSinglyLinkedListNode<String> temp = list.getHead();
        assertEquals(1,list.size());
        assertEquals(temp.getData(), list.getHead().getNext().getData());
        assertEquals(temp,list.getHead().getNext());
    }
    @Test (timeout = TIMEOUT)
    /**
     * Test to see if your head will point back to itself after adding
     * with Adding to the front.
     */
    public void AddToFrontHeadPontAtItSelfCheck() {
        list.addToFront("Did I Work?");
        CircularSinglyLinkedListNode<String> temp = list.getHead();
        assertEquals(1,list.size());
        assertEquals(temp.getData(), list.getHead().getNext().getData());
        assertEquals(temp,list.getHead().getNext());
    }
    @Test (timeout = TIMEOUT)
    /**
     * Test to see if your head will point back to itself after adding
     * with Adding to the Back.
     */
    public void AddToBackHeadPontAtItSelfCheck() {
        list.addToBack("Did I Work?");
        CircularSinglyLinkedListNode<String> temp = list.getHead();
        assertEquals(1,list.size());
        assertEquals(temp.getData(), list.getHead().getNext().getData());
        assertEquals(temp,list.getHead().getNext());
    }
    @Test (timeout = TIMEOUT)
    public void addAtIndexTest() {
        for (int i = 0 ; i < 10; i++ ) {
            list.addAtIndex(i,i +"");
        }
        assertEquals("2",list.get(2));
        assertEquals(10, list.size());
    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void addAtIndexIndexCheck1() {
        list.addAtIndex(-1,"hi");
    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void addAtIndexIndexCheck2() {
        list.addAtIndex(54545,"hi");
    }
    @Test (expected = IllegalArgumentException.class)
    public void addAtIndexNullCheck() {
        list.addAtIndex(0, null);
    }
    @Test(timeout = TIMEOUT)
    public void addAtIndexTest2() {
        list.addAtIndex(list.size(), "hi");
        list.addAtIndex(list.size(), "you" );
        list.addAtIndex(1,"T");
        assertEquals(3, list.size());
        assertEquals("T",list.get(1));
    }
    @Test (timeout = TIMEOUT)
    public void addToFrontTest1() {
        for (int i =0; i <= 10; i++) {
            list.addToFront(i + "");
        }
        assertEquals(11, list.size());
        assertEquals("0", list.get(list.size() - 1));
    }
    @Test (expected = IllegalArgumentException.class)
    public void addToFrontTest() {
        list.addToFront(null);
    }
    @Test (timeout = TIMEOUT)
    public void addToBackTest1() {
        for (int i =0; i <= 10; i++) {
            list.addToBack(i + "");
        }
        assertEquals(11, list.size());
        assertEquals("10", list.get(list.size() - 1));
    }
    @Test (timeout = TIMEOUT)
    public void addToBackTest2() {
        list.addToBack("Hi");
        assertEquals(1, list.size());
        assertEquals("Hi",list.getHead().getNext().getData());
    }
    @Test(timeout = TIMEOUT)
    public void removeAtIndexTest() {
        list.addToFront("H");
        list.addToBack("T");
        assertEquals("H", list.removeAtIndex(0));
        assertEquals("T", list.getHead().getData());
        assertEquals(1,list.size());
    }
    @Test(timeout = TIMEOUT)
    public void removeAtBackTest() {
        list.addToFront("H");
        list.addToBack("T");
        assertEquals("T", list.removeFromBack());
        assertEquals("H", list.getHead().getNext().getData());
        assertEquals(1,list.size());
    }
    @Test(timeout = TIMEOUT)
    public void removeAtBackTest2() {
        list.addToBack("H");
        list.removeFromBack();
        assertEquals(0, list.size());
        assertEquals(null, list.getHead());
    }
    @Test (timeout = TIMEOUT)
    public void removeAtFrontTest() {
        list.addToFront("Hello");
        list.removeFromFront();
        assertEquals(0,list.size());
        assertNull(list.getHead());
    }
    @Test (timeout = TIMEOUT)
    public void removeAtFrontTest3() {
        list.addToFront("t");
        list.addToFront("h");
        list.addToFront("x");
        list.removeFromFront();
        assertEquals(2,list.size());
        assertEquals("h",list.getHead().getNext().getNext().getData());
        assertEquals("h",list.getHead().getData());
    }
    @Test(timeout = TIMEOUT)
    public void removeAtFrontTest2() {
        list.addToFront("H");
        list.addToBack("T");
        assertEquals("H", list.removeFromFront());
        assertEquals("T", list.getHead().getNext().getData());
        assertEquals(1,list.size());
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void getIndexOutOfBoundsExceptionTest() {
        list.addToFront("h");
        list.get(1);
    }
    @Test (timeout = TIMEOUT)
    public void getTest() {
        for (int i = 0; i < 9; i++) {
            list.addToBack(i + "");
        }
        assertEquals("8",list.get(list.size() - 1));
    }
    @Test (timeout = TIMEOUT)
    public void removeLastOccurrenceTest() {
        list.addToFront("h");
        list.addToFront("h");
        list.addToFront("h");
        assertEquals("h",list.removeLastOccurrence("h"));
        assertEquals(2,list.size());
        assertEquals("h",list.get(0));
        assertEquals("h",list.get(1));
    }
    @Test (timeout = TIMEOUT)
    public void removeLastOccurrenceTest2() {
        list.addToFront("t");
        list.addToFront("h");
        list.addToFront("x");
        list.removeLastOccurrence("x");
        assertEquals(2,list.size());
        assertEquals("h",list.getHead().getData());
    }
    @Test(expected = NoSuchElementException.class)
    public void removeLastOccurrenceTest3() {
        list.addToFront("t");
        list.addToFront("gygt");
        list.removeLastOccurrence("NNYNYNRT");
    }
    @Test(expected = IllegalArgumentException.class)
    public void removeLastOccurrenceTest4() {
        list.removeLastOccurrence(null);
    }
    @Test(expected = NoSuchElementException.class)
    public void removeLastOccurrenceTest5(){
        list.removeLastOccurrence("h");
    }
    @Test (timeout = TIMEOUT)
    public void toArrayTest() {
        list.addToFront("h");
        list.addToBack("t");
        list.addToBack("k");
        String [] t = new String[3];
        t[0] = "h";
        t[1] = "t";
        t[2] = "k";
        assertArrayEquals(t,list.toArray());
    }
    @Test (timeout = TIMEOUT)
    public void toArrayTest2() {
        String[] t = new String[0];
        assertArrayEquals(t,list.toArray());
    }
}