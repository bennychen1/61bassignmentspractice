import org.junit.Test;
import static org.junit.Assert.*;

import ucb.junit.textui;

/** Cursory tests of WeirdListClient.
 *  @author Josh Hug
 *  @author P. N. Hilfinger
 */
public class WeirdListClientTest {
    @Test
    public void testAdd() {
        WeirdList w1 = new WeirdList(3, WeirdList.EMPTY);
        WeirdList w2 = new WeirdList(5, w1);
        WeirdList result = new WeirdList(7, new WeirdList(5, WeirdList.EMPTY));

        assertEquals(result.toString(), WeirdListClient.add(w2, 2).toString());

        WeirdList zero = WeirdList.EMPTY;

        assertEquals(" 2", WeirdListClient.add(zero, 2).toString());

    }
    @Test
    public void testSum() {
        WeirdList wl1 = new WeirdList(5, WeirdList.EMPTY);
        WeirdList wl2 = new WeirdList(6, wl1);
        WeirdList wl3 = new WeirdList(10, wl2);

        assertEquals(10 + 6 + 5, WeirdListClient.sum(wl3));
        assertEquals(5, WeirdListClient.sum(wl1));
        assertEquals(21, WeirdListClient.sum(wl3));
    }

    @Test
    public void testAddSum() {
        WeirdList wl1 = new WeirdList(5, WeirdList.EMPTY);
        WeirdList wl2 = new WeirdList(6, wl1);

        WeirdList nwl = WeirdListClient.add(wl2, 4);
        assertEquals((6 + 4) + (5 + 4), WeirdListClient.sum(nwl));
    }

    public static void main(String[] args) {
        System.exit(textui.runClasses(WeirdListClientTest.class));
    }

}
