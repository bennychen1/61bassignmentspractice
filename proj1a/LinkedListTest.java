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

}
