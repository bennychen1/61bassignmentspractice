package galaxy;

import static org.junit.Assert.*;
import org.junit.Test;
import ucb.junit.textui;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

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


    @Test
    public void testToggleBoundary() {
        _m3.clear();

        int[][]p = new int[][]{{2, 7}, {3, 8}, {10, 9}, {3, 1}, {6, 3}, {6, 3}};

        for (int[] i : p) {
            _m3.toggleBoundary(i[0], i[1]);
        }

        assertFalse(_m3.isBoundary(6, 3));
        assertFalse(_m3.isBoundary(3, 1));

        for (int i = 0; i < 3; i++) {
            assertTrue(_m3.isBoundary(p[i][0], p[i][1]));
        }
    }

    @Test
    public void testCenterAndBoundary() {
        _m3.clear();

        _m3.placeCenter(2, 3);
        _m3.toggleBoundary(2, 3);


        assertTrue(_m3.isBoundary(2, 3));
        assertTrue(_m3.isCenter(2, 3));
    }

    @Test
    public void testOpposingCell() {
        _m3.clear();

        Place p0 = Place.pl(3, 5);
        Place p1 = Place.pl(1, 5);
        Place p2 = Place.pl(1, 7);
        Place p3 = Place.pl(3, 3);
        Place p4 = Place.pl(1, 3);
        Place p5 = Place.pl(3, 9);

        Place p1Opp = Place.pl(5, 5);
        Place p2Opp = Place.pl(5, 3);
        Place p3Opp = Place.pl(3, 7);
        Place p4Opp = Place.pl(5, 7);
        Place p5Opp = Place.pl(3, 1);

        HashMap<Place, Place> p = new HashMap<Place, Place>();

        p.put(p1, p1Opp);
        p.put(p2, p2Opp);
        p.put(p3, p3Opp);
        p.put(p4, p4Opp);
        p.put(p5, p5Opp);

        for (Map.Entry<Place, Place> entry : p.entrySet()) {
            assertEquals(entry.getValue(), _m3.opposing(p0, entry.getKey()));
        }

        assertEquals(p0, _m3.opposing(p0, p0));
        assertNull(_m3.opposing(p0, Place.pl(7, 5)));

        Place edgeCell = Place.pl(9, 3);

        assertNull(_m3.opposing(edgeCell, Place.pl(7, 3)));
        assertNull(_m3.opposing(Place.pl(6, 5), Place.pl(7, 3)));
    }

    @Test
    public void testMarkAll() {
        _m3.clear();
        _m3.markAll(3);

        assertEquals(3, _m3.mark(1, 5));
        assertEquals(3, _m3.mark(7, 3));


        HashSet<Place> c = new HashSet<Place>();

        c.add(Place.pl(1, 1));
        c.add(Place.pl(7, 1));

        _m3.markAll(c, 1);

        assertEquals(1, _m3.mark(1, 1));
        assertEquals(1, _m3.mark(7, 1));

        _m3.mark(3, 5, 2);
        assertEquals(2, _m3.mark(3, 5));
    }





    /** Run the JUnit tests in this package. Add xxxTest.class entries to
     *  the arguments of runClasses to run other JUnit tests. */
    public static void main(String[] ignored) {
        textui.runClasses(ModelTests.class);

    }

}
