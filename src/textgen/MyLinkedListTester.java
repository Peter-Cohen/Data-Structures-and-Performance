/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

    private static final int LONG_LIST_LENGTH = 10;

    MyLinkedList<String> shortList;
    MyLinkedList<Integer> emptyList;
    MyLinkedList<Integer> longerList;
    MyLinkedList<Integer> list1;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        // Feel free to use these lists, or add your own
        shortList = new MyLinkedList<String>();
        shortList.add("A");
        shortList.add("B");
        emptyList = new MyLinkedList<Integer>();
        longerList = new MyLinkedList<Integer>();
        for (int i = 0; i < LONG_LIST_LENGTH; i++) {
            longerList.add(i);
        }
        list1 = new MyLinkedList<Integer>();
        list1.add(65);
        list1.add(21);
        list1.add(42);

    }

    /**
     * Test if the get method is working correctly.
     */
    /*
     * You should not need to add much to this method. We provide it as an example
     * of a thorough test.
     */
    @Test
    public void testGet() {
        // test empty list, get should throw an exception
        try {
            emptyList.get(0);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }

        // test short list, first contents, then out of bounds
        assertEquals("Check first", "A", shortList.get(0));
        assertEquals("Check second", "B", shortList.get(1));

        try {
            shortList.get(-1);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            shortList.get(2);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }
        // test longer list contents
        for (int i = 0; i < LONG_LIST_LENGTH; i++) {
            assertEquals("Check " + i + " element", (Integer) i, longerList.get(i));
        }

        // test off the end of the longer array
        try {
            longerList.get(-1);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            longerList.get(LONG_LIST_LENGTH);
            fail("Check out of bounds");
        } catch (IndexOutOfBoundsException e) {
        }

    }

    /**
     * Test removing an element from the list. We've included the example from the
     * concept challenge. You will want to add more tests.
     */
    @Test
    public void testRemove() {
        int a = list1.remove(0);
        assertEquals("Remove: check a is correct ", 65, a);
        assertEquals("Remove: check element 0 is correct ", (Integer) 21, list1.get(0));
        assertEquals("Remove: check size is correct ", 2, list1.size());

        // Check if index is within bounds:

        try {
            String removed = shortList.remove(-1);
            fail("Index cannot be lower than 0");
        } catch (IndexOutOfBoundsException e) {

        }

        // Check upper bound
        try {
            String removed = shortList.remove(shortList.size());
            fail("Index cannot be higher than size minus 1.");
        } catch (IndexOutOfBoundsException e) {

        }

    }

    /**
     * Test adding an element into the end of the list, specifically public boolean
     * add(E element)
     */
    @Test
    public void testAddEnd() {

        // Test if NPE thrown when adding a null value
        try {
            new MyLinkedList<String>().add(null);
            fail("Cannot add a null element");
        } catch (NullPointerException e) {

        }

        // Check adding to empty list
        boolean b = emptyList.add(123456);
        assertEquals("Add to empty list: check if element 0 is correct", (Integer) 123456, emptyList.get(0));
        assertEquals("Add to empty list: check return value", true, b);
        assertEquals("Add to empty list: check if size correctly updated", 1, emptyList.size());

        // Check adding to a non-empty list
        b = shortList.add("AAP");
        // Assumed that checking for for correct linking is already done by iterating
        // over the list
        // and comparing expected vs actual values
        String[] expectedElements = { "A", "B", "AAP" };
        String[] actualElements = new String[expectedElements.length];
        for (int i = 0; i < actualElements.length; i++) {
            actualElements[i] = shortList.get(i);
        }
        assertArrayEquals("Add to non-empty list: check coorect contents", actualElements, expectedElements);
        assertEquals("Add to non-empty list: check return value", true, b);
        assertEquals("Add to non-empty list: check if size correctly updated", expectedElements.length,
                shortList.size());

    }

    /** Test the size of the list */
    @Test
    public void testSize() {
        // TODO: implement this test
        assertEquals(0, emptyList.size());
        emptyList.add(123);
        assertEquals(1, emptyList.size());
        emptyList.remove(0);
        assertEquals(0, emptyList.size());
    }

    /**
     * Test adding an element into the list at a specified index, specifically:
     * public void add(int index, E element)
     */
    @Test
    public void testAddAtIndex() {

        // Test special case adding element at index 0 to an empty list
        emptyList.add(0, 1);
        assertEquals("Test adding element at idx 0 to empty list. value.", (Integer) 1, emptyList.get(0));
        assertEquals("Test adding element at idx 0 to empty list, size.", 1, emptyList.size());

        // test for adding a null value
        try {
            longerList.add(0, null);
            fail("Cannot add a null value");
        } catch (NullPointerException e) {

        }

        // Check for adding at a negative index
        try {
            longerList.add(-1, 999);
            fail("Cannot add at a negative index");
        } catch (IndexOutOfBoundsException e) {

        }

        // Check for upper bounds index
        try {
            longerList.add(longerList.size() + 1, 999);
            fail("Cannot add at an index higher than size.");
        } catch (IndexOutOfBoundsException e) {

        }

        // add at beginning (i.e. index 0)
        shortList.add(0, "Peet");
        assertEquals("Add at beginning of list", "Peet", shortList.get(0));
        assertEquals("Add at beginning of list", "A", shortList.get(1));
        assertEquals("Add at beginning of list", "B", shortList.get(2));
        assertEquals("Add at beginning of list", 3, shortList.size());

        // add at last position of the list (i.e. at index length - 1)
        shortList.add(shortList.size() - 1, "Aap");
        assertEquals("Add at last position of list", "Peet", shortList.get(0));
        assertEquals("Add at last position of list", "A", shortList.get(1));
        assertEquals("Add at last position of list", "Aap", shortList.get(2));
        assertEquals("Add at last position of list", "B", shortList.get(3));
        assertEquals("Add at last position of list", 4, shortList.size());

        // add in the middle
        shortList.add(1, "Jen");
        assertEquals("Add in middle of list", "Peet", shortList.get(0));
        assertEquals("Add in middle of list", "Jen", shortList.get(1));
        assertEquals("Add in middle of list", "A", shortList.get(2));
        assertEquals("Add in middle of list", "Aap", shortList.get(3));
        assertEquals("Add in middle of list", "B", shortList.get(4));
        assertEquals("Add in middle of list", 5, shortList.size());

    }

    /** Test setting an element in the list */
    @Test
    public void testSet() {
        // TODO: implement this test

        // Test setting value of null
        try {
            shortList.set(0, null);
            fail("A node cannot have a value of null");
        } catch (NullPointerException e) {

        }

        // Test invalid index
        try {
            shortList.set(-1, "Aap");
            fail("Index cannot be 0.");
        } catch (IndexOutOfBoundsException e) {

        }

        try {
            shortList.set(shortList.size(), "Aap");
            fail("Index cannot be higher than list size minus 1");
        } catch (IndexOutOfBoundsException e) {

        }

        // Test if new value is correct
        String old = shortList.set(0, "Aap");
        assertEquals("Check if set to the correct value.", "Aap", shortList.get(0));
        assertEquals("Check if the correct value was returned", "A", old);
    }

    // TODO: Optionally add more test methods.

}
