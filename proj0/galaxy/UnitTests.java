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
        assertEquals(13, _m2.xlim());
    }

    @Test
    public void testYlim() {
        assertEquals(7, _m.ylim());
        assertEquals(5, _m2.ylim());
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
    }

    @Test
    public void testOpposingIntersection() {
        _m3.clear();

        Place p0 = Place.pl(2, 8);

        Place p1 = Place.pl(1, 7);
        Place p1Opp = Place.pl(3, 9);

        Place p2 = Place.pl(1, 9);
        Place p2Opp = Place.pl(3, 7);

        assertEquals(p1Opp, _m3.opposing(p0, p1));
        assertEquals(p2Opp, _m3.opposing(p0, p2));
    }

    @Test
    public void testOpposingHEdge() {

        Place p0 = Place.pl(5, 6);

        Place p1 = Place.pl(5, 9);
        Place p1Opp = Place.pl(5, 3);

        Place p2 = Place.pl(1, 7);
        Place p2Opp = Place.pl(9, 5);

        assertEquals(p1Opp, _m3.opposing(p0, p1));
        assertEquals(p2Opp, _m3.opposing(p0, p2));
    }

    @Test
    public void testOpposingVEdge() {
        Place p0 = Place.pl(6, 7);

        Place p1 = Place.pl(3, 9);
        Place p1Opp = Place.pl(9, 5);

        assertEquals(p1Opp, _m3.opposing(p0, p1));
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

    @Test
    public void testModelCopy() {
        Model modelCopy = new Model(5, 5);
        modelCopy.toggleBoundary(5, 2);
        modelCopy.placeCenter(6, 1);

        Model modeltoCopy = new Model(3, 3);
        modeltoCopy.placeCenter(5, 5);
        modeltoCopy.toggleBoundary(3, 2);

        modelCopy.copy(modeltoCopy);
        modelCopy.toggleBoundary(3, 2);

        assertEquals(7, modelCopy.xlim());
        assertEquals(7, modelCopy.ylim());
        assertFalse(modelCopy.isBoundary(3, 2));
        assertTrue(modeltoCopy.isBoundary(3, 2));
        assertTrue(modelCopy.isCenter(5, 5));
    }



    @Test
    public void testFindGalaxy() {
        _m3.clear();

        _m3.placeCenter(3, 5);
        _m3.toggleBoundary(3, 2);
        _m3.toggleBoundary(3, 8);
        _m3.toggleBoundary(2, 5);
        _m3.toggleBoundary(2, 7);
        _m3.toggleBoundary(2, 3);
        _m3.toggleBoundary(4, 3);
        _m3.toggleBoundary(4, 5);
        _m3.toggleBoundary(4, 7);

        HashSet<Place> expectedCell = new HashSet<>();

        expectedCell.add(Place.pl(3, 5));
        expectedCell.add(Place.pl(3, 7));
        expectedCell.add(Place.pl(3, 3));

        assertEquals(expectedCell, _m3.findGalaxy(Place.pl(3, 5)));

    }

    @Test
    public void testModelCenters() {
        Model testModel = new Model(5, 6);

        Place center1 = Place.pl(3, 9);
        Place center2 = Place.pl(3, 3);
        Place center3 = Place.pl(6, 10);


        testModel.placeCenter(center1);
        testModel.placeCenter(center2);
        testModel.placeCenter(center3);

        int[][] boundaries = new int[][]{{1, 10}, {2, 9}, {2, 7}, {3, 6}, {5, 6}, {6, 7}, {5, 8}, {4, 9},
                {4, 11}, {1, 6}, {1, 4}, {1, 2}, {2, 3}, {4, 3}, {5, 4}, {5, 2}, {6, 1}, {6, 5}, {7, 8},
                {8, 9}, {8, 11}};

        testModel.toggleBoundary(1, 10);
        testModel.toggleBoundary(2, 9);
        testModel.toggleBoundary(2, 7);
        testModel.toggleBoundary(3, 6);
        testModel.toggleBoundary(5, 6);
        testModel.toggleBoundary(6, 7);
        testModel.toggleBoundary(5, 8);
        testModel.toggleBoundary(4, 9);
        testModel.toggleBoundary(4, 11);
        testModel.toggleBoundary(1, 6);
        testModel.toggleBoundary(1, 4);
        testModel.toggleBoundary(1, 2);
        testModel.toggleBoundary(2, 3);
        testModel.toggleBoundary(4, 3);
        testModel.toggleBoundary(5, 4);
        testModel.toggleBoundary(5, 2);
        testModel.toggleBoundary(6, 1);
        testModel.toggleBoundary(6, 5);
        testModel.toggleBoundary(7, 8);
        testModel.toggleBoundary(8, 9);
        testModel.toggleBoundary(8, 11);

        HashSet<Place> expectedCellsCenter1 = new HashSet<Place>();

        expectedCellsCenter1.add(Place.pl(1, 11));
        expectedCellsCenter1.add(Place.pl(3, 11));
        expectedCellsCenter1.add(Place.pl(3, 9));
        expectedCellsCenter1.add(Place.pl(3, 7));
        expectedCellsCenter1.add(Place.pl(5, 7));

        HashSet<Place> expectedCellsCenter2 = new HashSet<Place>();

        expectedCellsCenter2.add(Place.pl(1, 5));
        expectedCellsCenter2.add(Place.pl(3, 5));
        expectedCellsCenter2.add(Place.pl(5, 5));
        expectedCellsCenter2.add(Place.pl(3, 3));
        expectedCellsCenter2.add(Place.pl(1, 1));
        expectedCellsCenter2.add(Place.pl(3, 1));
        expectedCellsCenter2.add(Place.pl(5, 1));

        HashSet<Place> expectedCells3 = new HashSet<Place>();

        expectedCells3.add(Place.pl(5, 11));
        expectedCells3.add(Place.pl(7, 11));
        expectedCells3.add(Place.pl(5, 9));
        expectedCells3.add(Place.pl(7, 9));


        assertEquals(expectedCellsCenter1, testModel.findGalaxy(center1));
        assertEquals(expectedCellsCenter2, testModel.findGalaxy(center2));
        assertEquals(expectedCells3, testModel.findGalaxy(center3));
    }

    @Test
    public void testFindVertical() {
        Model testModel = new Model(3, 3);

        Place center = Place.pl(2, 3);

        HashSet<Place>expected = new HashSet<Place>();

        expected.add(Place.pl(1, 1));
        expected.add(Place.pl(1, 3));
        expected.add(Place.pl(1, 5));
        expected.add(Place.pl(3, 1));
        expected.add(Place.pl(3, 3));
        expected.add(Place.pl(3, 5));

        testModel.toggleBoundary(4, 1);
        testModel.toggleBoundary(4, 3);
        testModel.toggleBoundary(4, 5);

        assertEquals(expected, testModel.findGalaxy(center));
    }

    @Test
    public void testFindHoriz() {
        Model testModel = new Model(3, 3);

        Place center = Place.pl(3, 4);

        HashSet<Place>expected = new HashSet<>();

        expected.add(Place.pl(1, 5));
        expected.add(Place.pl(3, 5));
        expected.add(Place.pl(3, 3));
        expected.add(Place.pl(5, 3));

        testModel.toggleBoundary(1, 4);
        testModel.toggleBoundary(2, 3);
        testModel.toggleBoundary(3, 2);
        testModel.toggleBoundary(5, 2);
        testModel.toggleBoundary(4, 5);
        testModel.toggleBoundary(5, 4);

        assertEquals(expected, testModel.findGalaxy(center));
    }





    /** Run the JUnit tests in this package. Add xxxTest.class entries to
     *  the arguments of runClasses to run other JUnit tests. */
    public static void main(String[] ignored) {
        textui.runClasses(UnitTests.class);
       /* textui.runClasses(ModelTests.class); */

    }

}
