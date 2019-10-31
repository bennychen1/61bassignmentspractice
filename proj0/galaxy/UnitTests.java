package galaxy;

import static org.junit.Assert.*;
import org.junit.Test;
import ucb.junit.textui;

import java.util.HashSet;

/** The suite of all JUnit tests for the galaxy package.
 *  @author P. N. Hilfinger
 */
public class UnitTests {

    Model _m = new Model(3, 3);
    Model _m2 = new Model(6, 2);
    Model _m3 = new Model(5, 5);

    @Test
    public void testInit() {
        Model m2 = new Model(2, 5);
        m2.copy(_m);
        assertArrayEquals(_m.board(), m2.board());
    }

    @Test
    public void testXlim() {
        assertEquals(7, _m.xlim());
        assertEquals(5, _m2.xlim());
    }

    @Test
    public void testYlim() {
        assertEquals(7, _m.ylim());
        assertEquals(13, _m2.ylim());
    }

    @Test
    public void testIsCenter() {
        _m3.board()[2][2] = 2;
        _m3.board()[5][3] = 2;
        Place cen = Place.pl(5, 3);

        assertTrue(_m3.isCenter(2, 2));
        assertTrue(_m3.isCenter(cen));
        assertFalse(_m3.isCenter(0, 1));
    }

    @Test
    public void testIsBoundary() {
        _m3.board()[0][5] = 3;
        _m3.board()[1][2] = 3;

        Place bound = Place.pl(1, 2);

        assertFalse(_m3.isBoundary(5, 1));
        assertTrue(_m3.isBoundary(0, 5));
        assertTrue(_m3.isBoundary(bound));
        assertTrue(_m3.isBoundary(10, 0));
        assertTrue(_m3.isBoundary(3, 10));
    }

    @Test
    public void testPlaceCenter() {
        _m3.clear();

        Place intersection = Place.pl(2, 2);
        Place cell = Place.pl(3, 9);
        Place vEdge = Place.pl(6, 3);
        Place hEdge = Place.pl(5, 6);
        Place oEdge = Place.pl(5, 10);

        HashSet<Place> p = new HashSet<Place>();

        p.add(intersection);
        p.add(cell);
        p.add(vEdge);
        p.add(hEdge);

        for (Place pl : p) {
            _m3.placeCenter(pl);
        }

        _m3.placeCenter(oEdge);

        assertFalse(_m3.isCenter(5, 10));

        for (Place pl : p) {
            assertTrue(_m3.isCenter(pl));
        }



    }




    /** Run the JUnit tests in this package. Add xxxTest.class entries to
     *  the arguments of runClasses to run other JUnit tests. */
    public static void main(String[] ignored) {
        textui.runClasses(ModelTests.class);

    }

}
