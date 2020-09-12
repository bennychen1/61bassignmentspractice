import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void testAddFirst() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(3);
        a.addFirst(5);
        a.addFirst(6);

        assertEquals(3, a.size());
        assertEquals(Integer.valueOf(6), a.get(0));
        assertEquals(Integer.valueOf(5), a.get(1));
    }

    @Test
    public void testAddLast() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addLast(3);
        a.addLast(5);
        a.addLast(6);

        assertEquals(3, a.size());
        assertEquals(Integer.valueOf(3), a.get(0));
        assertEquals(Integer.valueOf(6), a.get(2));
    }
}
