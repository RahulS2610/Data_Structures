import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * @author Lisa Shi
 * @version 1.0
 */
public class ArrayListStudentTest {

    private static final int TIMEOUT = 200;
    private ArrayList<String> list;
    private ArrayList<Integer> intList;

    @Before
    public void setUp() {
        list = new ArrayList<>();
        intList = new ArrayList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, list.size());
        assertEquals(0, intList.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                list.getBackingArray());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                intList.getBackingArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexContiguous() {
        list.addAtIndex(9, "hello");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexNegative() {
        list.addAtIndex(-12, "hello");
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex() {
       /** intList.addAtIndex(0, 1);   // 1
        intList.addAtIndex(1, 2);   // 1, 2
        intList.addAtIndex(0, 3);   // 3, 1, 2
        intList.addAtIndex(2, 4);   // 3, 1, 4, 2
        intList.addAtIndex(3, 5);   // 3, 1, 4, 5, 2
        intList.addAtIndex(3, 1);   // 3, 1, 4, 1, 5, 2
        intList.addAtIndex(5, 9);   // 3, 1, 4, 1, 5, 9, 2
        intList.addAtIndex(7, 6);   // 3, 1, 4, 1, 5, 9, 2, 6
        intList.addAtIndex(8, 5);   // 3, 1, 4, 1, 5, 9, 2, 6, 5
        intList.addAtIndex(0, 0);   // 0, 3, 1, 4, 1, 5, 9, 2, 6, 5
        assertEquals(10, intList.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 2];
        expected[0] = 0;
        expected[1] = 3;
        expected[2] = 1;
        expected[3] = 4;
        expected[4] = 1;
        expected[5] = 5;
        expected[6] = 9;
        expected[7] = 2;
        expected[8] = 6;
        expected[9] = 5;

        assertArrayEquals(expected, intList.getBackingArray());
        */
       intList.addAtIndex(33, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAtIndexNull() {
        list.addAtIndex(0, null);
    }


    public void testSwapPairs(){
        list.swapPairs();
    }
    @Test(timeout = TIMEOUT)
    public void testAddToFrontBig() {
        /**
        list.addToFront("BTS");
        list.addToFront("StrayKids");
        list.addToFront("NCT127");
        list.addToFront("NCTDream");
        list.addToFront("Day6");
        list.addToFront("Got7");
        list.addToFront("WayV");
        list.addToFront("EpikHigh");
        list.addToFront("LeeHi");
        list.addToFront("Crush");
        list.addToFront("Zico");
        list.addToFront("Seori");
        list.addToFront("IU");
        list.addToFront("Blackpink");
        list.addToFront("CrashLandingOnYou");
        list.addToFront("ItaewonClass");
        list.addToFront("EternalMonarch");
        list.addToFront("It'sOkayToNotBeOkay");
        list.addToFront("StartUp");
        list.addToFront("HotelDelLuna");
        list.addToFront("TrueBeauty");

        assertEquals(21, list.size());

        Object[] expected = new Object[36];
        expected[0] = "TrueBeauty";
        expected[1] = "HotelDelLuna";
        expected[2] = "StartUp";
        expected[3] = "It'sOkayToNotBeOkay";
        expected[4] = "EternalMonarch";
        expected[5] = "ItaewonClass";
        expected[6] = "CrashLandingOnYou";
        expected[7] = "Blackpink";
        expected[8] = "IU";
        expected[9] = "Seori";
        expected[10] = "Zico";
        expected[11] = "Crush";
        expected[12] = "LeeHi";
        expected[13] = "EpikHigh";
        expected[14] = "WayV";
        expected[15] = "Got7";
        expected[16] = "Day6";
        expected[17] = "NCTDream";
        expected[18] = "NCT127";
        expected[19] = "StrayKids";
        expected[20] = "BTS";
        assertArrayEquals(expected, list.getBackingArray());
         */

    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack() {
        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 4];
        for (int i = 0; i < 23; i++) {
            intList.addToBack(i);
            expected[i] = i;
        }
        assertEquals(23, intList.size());
        assertArrayEquals(expected, intList.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndex() {
        for (int i = 0; i < 23; i++) {
            list.addAtIndex(i, i + "");
        }
        assertEquals(23, list.size());

        list.removeAtIndex(4);
        list.removeAtIndex(3);
        list.removeAtIndex(2);
        list.removeAtIndex(1);
        assertEquals(19, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 4];
        expected[0] = "0";
        for (int i = 5; i < 23; i++) {
            expected[i - 4] = i + "";
        }
        assertArrayEquals(expected, list.getBackingArray());

        String temp = "Oy Felix";
        String temp1 = "C'm 'ere bro";

        list.addAtIndex(7, temp);
        list.addAtIndex(8, temp1);

        assertEquals(21, list.size());

        assertSame(temp1, list.removeAtIndex(8));
        assertSame(temp, list.removeAtIndex(7));
        assertEquals(19, list.size());
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexNegativeIndex() {
        list.removeAtIndex(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexNonContiguous() {
        list.addToBack("Jin");
        list.addToBack("Suga");
        list.addToBack("J-hope");
        list.addToBack("RM");
        list.addToBack("Jimin");
        list.addToBack("V");
        list.addToBack("Jungkook");
        assertEquals(7, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "Jin";
        expected[1] = "Suga";
        expected[2] = "J-hope";
        expected[3] = "RM";
        expected[4] = "Jimin";
        expected[5] = "V";
        expected[6] = "Jungkook";
        assertArrayEquals(expected, list.getBackingArray());
        list.removeAtIndex(7);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromFrontEmptyList1() {
        list.removeFromFront();
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromFrontEmptyList2() {
        //adding all elements to front
        Object[] expected = new Object[18];
        for (int i = 0; i < 11; i++) {
            intList.addToBack(i);
            expected[i] = i;
        }
        assertEquals(11, intList.size());
        assertArrayEquals(expected, intList.getBackingArray());

        //removing 1 element from front
        intList.removeFromFront();
        for (int i = 1; i < 11; i++) {
            expected[i - 1] = i;
        }
        expected[10] = null;
        assertEquals(10, intList.size());
        assertArrayEquals(expected, intList.getBackingArray());

        //removing until empty
        for (int i = 0; i < 11; i++) {
            intList.removeFromFront();
        }
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        String[] tempVar = new String[11];

        tempVar[0] = "TrueBeauty";
        tempVar[1] = "HotelDelLuna";
        tempVar[2] = "StartUp";
        tempVar[3] = "It'sOkayToNotBeOkay";
        tempVar[4] = "EternalMonarch";
        tempVar[5] = "ItaewonClass";
        tempVar[6] = "CrashLandingOnYou";
        tempVar[7] = "TheCrown";
        tempVar[8] = "BojackHorseman";
        tempVar[9] = "TheOffice";
        tempVar[10] = "TheGoodPlace";

        Object[] expected = new Object[18];
        for (int i = 0; i <= 10; i++) {
            list.addToBack(tempVar[i]);
            expected[i] = tempVar[i];
        }
        assertEquals(11, list.size());

        assertArrayEquals(expected, list.getBackingArray());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        int lastInd = 10;
        for (int i = 0; i <= 10; i++) {
            assertSame(tempVar[i], list.removeFromFront());
            for (int j = i + 1; j <= 10; j++) {
                expected[j - i - 1] = tempVar[j];
            }
            expected[lastInd] = null;
            lastInd--;
            assertArrayEquals(expected, list.getBackingArray());
        }
        assertEquals(0, list.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromBackEmptyList1() {
        list.removeFromBack();
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromBackEmptyList2() {
        //adding all elements to front
        Object[] expected = new Object[18];
        for (int i = 0; i < 11; i++) {
            intList.addToBack(i);
            expected[i] = i;
        }
        assertEquals(11, intList.size());
        assertArrayEquals(expected, intList.getBackingArray());

        //removing 1 element from front
        intList.removeFromBack();
        for (int i = 0; i < 10; i++) {
            expected[i] = i;
        }
        expected[10] = null;
        assertEquals(10, intList.size());
        assertArrayEquals(expected, intList.getBackingArray());

        //removing until empty
        for (int i = 0; i < 11; i++) {
            intList.removeFromBack();
        }
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {
        String[] tempVar = new String[23];
        tempVar[0] = "They teach you to heed the word of a God who has never spoken";
        tempVar[1] = "To fear breaking the law when it's already broken";
        tempVar[2] = "That to feel is to be weak, to suppress emotion";
        tempVar[3] = "So no one sees you had a heart 'til your chest is open";
        tempVar[4] = "They got you hating who you are, to sell you pills and fictions";
        tempVar[5] = "Reaching for the stars when you were born up there with them";
        tempVar[6] = "Addicted to the news, views, superstitions";
        tempVar[7] = "To keep the visionaries glued to their televisions";
        tempVar[8] = "They want you busy stepping to the right side of history";
        tempVar[9] = "To keep you from the inside of history";
        tempVar[10] = "Give everyone a voice but leash them with the mic cord";
        tempVar[11] = "Feed you things to fight about instead of things to fight for";
        tempVar[12] = "Teach you everything you want, but nothing you need";
        tempVar[13] = "That everything's got a price, and nothing is free";
        tempVar[14] = "They'll turn everything to nothing, to make you believe";
        tempVar[15] = "That everything is under control and there's nothing to see";
        tempVar[16] = "No more lessons, please take me back to zero";
        tempVar[17] = "No more teachers, no more prophets, no more heroes";
        tempVar[18] = "No more lessons, please now I see the question to all answers";
        tempVar[19] = "Will only bring me to my knees";
        tempVar[20] = "And back to zero";
        tempVar[21] = "Artist: EpikHigh";
        tempVar[22] = "Song: Lesson Zero";

        Object[] expected = new Object[9 * 4];
        for (int i = 0; i < 23; i++) {
            list.addToBack(tempVar[i]);
            expected[i] = tempVar[i];
        }
        assertEquals(23, list.size());
        assertArrayEquals(expected, list.getBackingArray());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        for (int i = 22; i >= 0; i--) {
            assertSame(tempVar[i], list.removeFromBack());
            expected[i] = null;
            assertArrayEquals(expected, list.getBackingArray());
        }
        assertEquals(0, list.size());
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        list.addAtIndex(0, "1a");
        list.addAtIndex(1, "2a");
        list.addAtIndex(2, "3a");
        list.addAtIndex(3, "4a");
        list.addToFront("number + a");
        list.addToBack("5a");
        list.addToBack("6a");
        list.addToBack("8a");
        list.addToBack("list complete");
        list.addAtIndex(7, "7a");

        assertEquals(10, list.size());

        assertEquals("number + a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
        assertEquals("5a", list.get(5));
        assertEquals("6a", list.get(6));
        assertEquals("7a", list.get(7));
        assertEquals("8a", list.get(8));
        assertEquals("list complete", list.get(9));
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        // Should be empty at initialization
        assertTrue(list.isEmpty());

        // Should not be empty after adding elements
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a");
        list.addAtIndex(6, "6a");
        list.addAtIndex(7, "7a");
        list.addAtIndex(8, "8a");
        list.addAtIndex(9, "9a");
        list.addAtIndex(10, "10a");
        list.addAtIndex(11, "11a");

        assertEquals(12, list.size());

        assertFalse(list.isEmpty());

        for (int i = 0; i < 12; i++) {
            list.removeFromBack();
        }
        assertTrue(list.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a");
        list.addAtIndex(6, "6a");
        list.addAtIndex(7, "7a");
        list.addAtIndex(8, "8a");
        list.addAtIndex(9, "9a");
        list.addAtIndex(10, "10a");
        list.addAtIndex(11, "11a");
        assertEquals(12, list.size());

        // Clearing the list should empty the array and reset size
        list.clear();

        assertEquals(0, list.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                list.getBackingArray());
    }
}