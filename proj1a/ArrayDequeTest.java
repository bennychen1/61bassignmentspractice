import org.junit.Test;

import java.lang.reflect.Array;

import static org.junit.Assert.*;

import java.util.Timer;

public class ArrayDequeTest {

    public ArrayDeque<Integer> createArrayDeque() {
        ArrayDeque<Integer> a = new ArrayDeque<>();

        a.addFirst(20);
        a.addFirst(10);
        a.addLast(30);
        a.addLast(50);
        a.addLast(60);
        a.addLast(70);
        a.addLast(80);
        a.addLast(90);

        return a;

    }
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

    @Test
    public void addBoth() {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addLast(10);
        a.addLast(11);
        a.addLast(12);
        a.addFirst(13);
        a.addFirst(14);
        a.addFirst(17);
        a.addLast(15);
        a.addLast(16);

        assertEquals(Integer.valueOf(14), a.get(1));
        assertEquals(Integer.valueOf(13), a.get(2));
        assertEquals(Integer.valueOf(10), a.get(3));
        assertEquals(Integer.valueOf(15), a.get(6));
        assertEquals(Integer.valueOf(12), a.get(5));
    }

    @Test
    public void addBothLoop() {
        ArrayDeque<Integer> a = createArrayDeque();

        assertEquals(Integer.valueOf(70), a.get(5));
        assertEquals(Integer.valueOf(90), a.get(7));
    }

    @Test
    public void testRemoveFirst() {
        ArrayDeque<Integer> a = createArrayDeque();

        assertEquals(Integer.valueOf(10), a.removeFirst());
        assertEquals(7, a.size());

        assertEquals(Integer.valueOf(20), a.removeFirst());

        for (int i = 0; i < 6; i++) {
            a.removeFirst();
        }

        assertNull(a.removeFirst());
    }

    @Test
    public void testRemoveAndAdd() {
        ArrayDeque<Integer> a = createArrayDeque();

        a.removeFirst();
        a.removeFirst();

        a.addFirst(5);

        assertEquals(Integer.valueOf(5), a.get(0));
        assertEquals(Integer.valueOf(5), a.removeFirst());
    }

    @Test
    public void testRemoveLast() {
        ArrayDeque<Integer> a = createArrayDeque();

        assertEquals(Integer.valueOf(90), a.removeLast());

        a.addFirst(15);

        assertEquals(Integer.valueOf(15), a.get(0));
        assertEquals(Integer.valueOf(80), a.get(7));
    }

    @Test
    public void testRemoveAndAddMultiple() {
        ArrayDeque<Integer> a = createArrayDeque();

        a.removeLast();

        a.addFirst(12);

        a.removeLast();

        assertEquals(Integer.valueOf(12), a.get(0));

        for (int i = 0; i < 3; i++) {
            a.removeFirst();
        }

        assertEquals(Integer.valueOf(30), a.get(0));

        a.addLast(2);
        a.addLast(7);

        a.addFirst(7);
        a.addFirst(10);

        assertEquals(Integer.valueOf(7), a.get(7));
        assertEquals(Integer.valueOf(7), a.get(1));

        for (int i = 0; i < 9; i++) {
            a.removeFirst();
        }

        assertTrue(a.isEmpty());

        a.addLast(3);

        assertEquals(Integer.valueOf(3), a.get(0));

    }

    @Test
    public void testRemoveFirstOverlapWithLast() {
        ArrayDeque<Integer> a = createArrayDeque();

        for (int i = 0; i < 5; i++) {
            a.removeFirst();
        }

        assertEquals(Integer.valueOf(70), a.get(0));

    }

    @Test
    public void testRemoveLastOverLapWithFirst() {
        ArrayDeque<Integer> a = createArrayDeque();

        for (int i = 0; i < 6; i++) {
            a.removeLast();
        }

        assertEquals(Integer.valueOf(20), a.get(1));
    }

    @Test
    public void testResizeUp() {
        ArrayDeque<Integer> a = createArrayDeque();

        a.addFirst(20);
        a.addLast(10);

        assertEquals(Integer.valueOf(20), a.get(0));
        assertEquals(Integer.valueOf(10), a.get(9));
        assertEquals(Integer.valueOf(30), a.get(3));

        long start1000 = System.nanoTime();

        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                a.addFirst(i);
            } else {
                a.addLast(i);
            }
        }

        long fin1000 = System.nanoTime();

        long start100 = System.nanoTime();

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                a.addFirst(i);
            } else {
                a.addLast(i);
            }
        }

        long fin100 = System.nanoTime();

        long elapsed100 = fin100 - start100;
        long elapsed1000 = fin1000 - start1000;

        assertTrue(String.format("Elapsed time for 100 items was %d and elapsed time for 1000 items was %d",
                elapsed100, elapsed1000), fin1000/fin100 < 11);
    }

    @Test
    public void testResizeFirstBeforeLast() {
        ArrayDeque<Integer> a = createArrayDeque();
        a.removeLast();
        a.addFirst(35);

        a.addLast(15);
        a.addFirst(16);

        assertEquals(Integer.valueOf(16), a.get(0));
        assertEquals(Integer.valueOf(15), a.get(9));
        assertEquals(Integer.valueOf(10), a.get(2));

    }

    @Test
    public void testResizeSmall() {
        ArrayDeque<Integer> a = createArrayDeque();
        a.addFirst(35);
        for (int i = 0; i < 6; i++) {
            a.removeLast();
        }

        assertEquals(Integer.valueOf(35), a.get(0));
        assertEquals(Integer.valueOf(20), a.get(2));

        a.addFirst(15);
        a.addLast(16);

        assertEquals(Integer.valueOf(16), a.get(4));
        assertEquals(Integer.valueOf(35), a.get(1));
    }

    @Test
    public void testResizeNextFirstInFrontOfLast() {
        ArrayDeque<Integer> a = createArrayDeque();


        a.addLast(10);
        a.removeLast();

        for (int i = 0; i < 6; i++) {
            a.removeFirst();
        }

        a.addLast(10);
    }
}
