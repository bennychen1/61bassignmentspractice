import org.junit.Test;
import static org.junit.Assert.*;

import ucb.junit.textui;

import java.lang.ref.WeakReference;

/** Tests of WeirdList.
 *  @author Josh Hug
 *  @author P. N. Hilfinger
 */
public class WeirdListTest {

    @Test
    public void testList() {
        WeirdList wl1 = new WeirdList(5, WeirdList.EMPTY);
        WeirdList wl2 = new WeirdList(6, wl1);
        WeirdList wl3 = new WeirdList(10, wl2);
        assertEquals(3, wl3.length());
        assertEquals(1, wl1.length());

        assertEquals(" 10 6 5", wl3.toString());
    }

    @Test
    public void testListLength() {
        WeirdList w1 = new WeirdList(2, new WeirdList(5, WeirdList.EMPTY));
        assertEquals(2, w1.length());
    }

    @Test
    public void testListToString() {
        WeirdList w1 = new WeirdList(2, WeirdList.EMPTY);
        assertEquals(" 2", w1.toString());
    }

    @Test
    public void testMap() {
        WeirdList w1 = new WeirdList(2, WeirdList.EMPTY);
        WeirdList w2 = new WeirdList(3, w1);
        WeirdList w3 = new WeirdList(5, w2);

        WeirdList multipled = w3.map(new multByTwo());

        assertEquals(" 10 6 4", multipled.toString());
    }


    public static void main(String[] args) {
        System.exit(textui.runClasses(WeirdListTest.class));
    }

}
