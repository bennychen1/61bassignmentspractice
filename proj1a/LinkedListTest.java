import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class LinkedListTest {

    @Test
    public void addFirst() {
        LinkedListDeque<Integer> d = new LinkedListDeque<>();
        d.addFirst(5);
        d.addFirst(10);

        assertEquals(d.size(), 2);
    }

    @Test
    public void testAddLast() {
        LinkedListDeque<String> d = new LinkedListDeque<>();
        d.addLast("One");

        assertEquals("One", d.get(0));
        assertEquals(1, d.size());

        d.addLast("Two");
        assertEquals(2, d.size());
        assertEquals("Two", d.get(1));

        d.addFirst("NewOne");
        assertEquals("Two", d.get(2));
    }

    @Test
    public void testGet() {
        LinkedListDeque<String> s = new LinkedListDeque<>();
        LinkedListDeque<Integer> e = new LinkedListDeque<>();
        s.addFirst("Second");
        s.addFirst("First");

        assertEquals("Second", s.get(1));
        assertEquals("First", s.get(0));
        assertNull(s.get(5));
        assertNull(e.get(0));
    }

    @Test
    public void testIsEmpty() {
        LinkedListDeque<Integer> d = new LinkedListDeque<>();
        d.addFirst(1);
        d.addLast(2);
        LinkedListDeque<Integer> e = new LinkedListDeque<>();

        assertTrue(e.isEmpty());
        assertFalse(d.isEmpty());
    }

    @Test
    public void testRemoveFirst() {
        LinkedListDeque<Integer> d = new LinkedListDeque<>();
        d.addFirst(1);
        d.addFirst(2);
        d.addLast(3);

        Integer i = d.removeFirst();

        assertEquals(2, d.size());
        assertEquals(Integer.valueOf(1), d.get(0));
        assertEquals(Integer.valueOf(2), i);
        assertEquals(Integer.valueOf(3), d.get(1));

        Integer i2 = d.removeFirst();
        assertEquals(1, d.size());
        assertEquals(Integer.valueOf(1), i2);

        LinkedListDeque<String> e = new LinkedListDeque<>();
        e.addLast("One");
        e.removeFirst();

        assertNull(e.removeFirst());

    }

    @Test
    public void testRemoveLast() {
        LinkedListDeque<String> d = new LinkedListDeque<>();
        d.addFirst("Apple");
        d.addFirst("Orange");
        d.addLast("Pear");
        d.addLast("Grape");

        String r1 = d.removeLast();
        String r2 = d.removeLast();

        assertEquals("Grape", r1);
        assertEquals("Pear", r2);
        assertEquals(2, d.size());
        assertEquals("Apple", d.get(1));

        d.addLast("Strawberry");

        assertEquals("Strawberry", d.get(2));

        d.removeLast();
        d.removeLast();
        d.removeLast();

        assertNull(d.removeLast());
    }

}
