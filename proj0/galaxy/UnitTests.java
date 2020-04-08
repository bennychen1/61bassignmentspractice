package galaxy;

import static org.junit.Assert.*;
import org.junit.Test;
import ucb.junit.textui;

import java.util.*;

import static java.util.Arrays.asList;
import static galaxy.Place.pl;

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

        Place intersection = pl(2, 2);
        Place cell = pl(3, 9);
        Place vEdge = pl(6, 3);
        Place hEdge = pl(5, 6);
        Place oEdge = pl(5, 10);

        HashSet<Place> p = new HashSet<Place>();

        p.addAll(asList(intersection, cell, vEdge, hEdge));

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

        Place p0 = pl(3, 5);
        Place p1 = pl(1, 5);
        Place p2 = pl(1, 7);
        Place p3 = pl(3, 3);
        Place p4 = pl(1, 3);
        Place p5 = pl(3, 9);

        Place p1Opp = pl(5, 5);
        Place p2Opp = pl(5, 3);
        Place p3Opp = pl(3, 7);
        Place p4Opp = pl(5, 7);
        Place p5Opp = pl(3, 1);

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
        assertNull(_m3.opposing(p0, pl(7, 5)));

        Place edgeCell = pl(9, 3);

        assertNull(_m3.opposing(edgeCell, pl(7, 3)));
    }

    @Test
    public void testOpposingIntersection() {
        _m3.clear();

        Place p0 = pl(2, 8);

        Place p1 = pl(1, 7);
        Place p1Opp = pl(3, 9);

        Place p2 = pl(1, 9);
        Place p2Opp = pl(3, 7);

        assertEquals(p1Opp, _m3.opposing(p0, p1));
        assertEquals(p2Opp, _m3.opposing(p0, p2));
    }

    @Test
    public void testOpposingHEdge() {

        Place p0 = pl(5, 6);

        Place p1 = pl(5, 9);
        Place p1Opp = pl(5, 3);

        Place p2 = pl(1, 7);
        Place p2Opp = pl(9, 5);

        assertEquals(p1Opp, _m3.opposing(p0, p1));
        assertEquals(p2Opp, _m3.opposing(p0, p2));
    }

    @Test
    public void testOpposingVEdge() {
        Place p0 = pl(6, 7);

        Place p1 = pl(3, 9);
        Place p1Opp = pl(9, 5);

        assertEquals(p1Opp, _m3.opposing(p0, p1));
    }

    @Test
    public void testMarkAll() {
        _m3.clear();
        _m3.markAll(3);

        assertEquals(3, _m3.mark(1, 5));
        assertEquals(3, _m3.mark(7, 3));


        HashSet<Place> c = new HashSet<Place>();

        c.add(pl(1, 1));
        c.add(pl(7, 1));

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

        toggleManyBoundaries(_m3, new int[][]{{3, 2}, {3, 8}, {2, 5}, {2, 7},
                                              {2, 3}, {4, 3}, {4, 5}, {4, 7}});

        _m3.placeCenter(3, 5);


        HashSet<Place> expectedCell = new HashSet<>();

        expectedCell.add(pl(3, 5));
        expectedCell.add(pl(3, 7));
        expectedCell.add(pl(3, 3));

        assertEquals(expectedCell, _m3.findGalaxy(pl(3, 5)));

    }

    @Test
    public void testModelCenters() {
        Model testModel = new Model(5, 6);

        Place center1 = pl(3, 9);
        Place center2 = pl(3, 3);
        Place center3 = pl(6, 10);

        testModel.placeCenter(center1);
        testModel.placeCenter(center2);
        testModel.placeCenter(center3);

        int[][] boundaries = new int[][]{{1, 10}, {2, 9}, {2, 7}, {3, 6},
                                         {5, 6}, {6, 7}, {5, 8}, {4, 9},
                                         {4, 11}, {1, 6}, {1, 4},
                                         {1, 2}, {2, 3}, {4, 3}, {5, 4},
                                         {5, 2}, {6, 1}, {6, 5}, {7, 8},
                                         {8, 9}, {8, 11}};

        toggleManyBoundaries(testModel, boundaries);

        HashSet<Place> expectedCellsCenter1 = new HashSet<Place>();
        expectedCellsCenter1.addAll(asList(pl(1, 11), pl(3, 11), pl(3, 9),
                pl(3, 7), pl(5, 7)));


        HashSet<Place> expectedCellsCenter2 = new HashSet<Place>();
        expectedCellsCenter2.addAll(asList(pl(1, 5), pl(3, 5),
                pl(5, 5), pl(3, 3), pl(1, 1), pl(3, 1), pl(5, 1)));


        HashSet<Place> expectedCells3 = new HashSet<Place>();
        expectedCells3.addAll(asList(pl(5, 11), pl(7, 11), pl(5, 9), pl(7, 9)));

        assertEquals(expectedCellsCenter1, testModel.findGalaxy(center1));
        assertEquals(expectedCellsCenter2, testModel.findGalaxy(center2));
        assertEquals(expectedCells3, testModel.findGalaxy(center3));
    }

    @Test
    public void testFindVertical() {
        Model testModel = new Model(3, 3);

        Place center = pl(2, 3);

        HashSet<Place> expected = new HashSet<Place>();

        expected.addAll(asList(pl(1, 1), pl(1, 3), pl(1, 5), pl(3, 1),
                pl(3, 3), pl(3, 5)));

        toggleManyBoundaries(testModel, new int[][]{{4, 1}, {4, 3}, {4, 5}});

        assertEquals(expected, testModel.findGalaxy(center));
    }

    @Test
    public void testFindHoriz() {
        Model testModel = new Model(3, 3);

        Place center = pl(3, 4);

        HashSet<Place> expected = new HashSet<>();


        expected.add(pl(1, 5));
        expected.add(pl(3, 5));
        expected.add(pl(3, 3));
        expected.add(pl(5, 3));

        toggleManyBoundaries(testModel, new int[][]{{1, 4}, {2, 3}, {3, 2},
                                                    {5, 2}, {4, 5}, {5, 4}});

        assertEquals(expected, testModel.findGalaxy(center));
    }

    @Test
    public void testStrayBoundaries() {
        _m3.clear();

        Place c1 = pl(3, 3);
        _m3.placeCenter(c1);

        toggleManyBoundaries(_m3, new int[][]{{1, 6}, {3, 6}, {5, 6},
                {6, 5}, {6, 1}, {5, 4}, {5, 2}, {1, 2}, {4, 3}});

        assertNull(_m3.findGalaxy(c1));

        _m3.toggleBoundary(2, 1);
        _m3.toggleBoundary(4, 3);

        assertNull(_m3.findGalaxy(c1));

        _m3.toggleBoundary(2, 1);
        Place c2 = pl(2, 5);

        assertNull(_m3.findGalaxy(c1));
    }

    @Test
    public void testUnmarkedContaining() {
        _m3.clear();

        Place intersection = pl(2, 8);
        Place hEdge = pl(3, 4);
        Place vEdge = pl(6, 3);
        Place cell = pl(7, 7);

        List<Place> iCellsExpected = asList(pl(1, 9), pl(1, 7),
                pl(3, 9), pl(3, 7));
        List<Place> iCellsActual = _m3.unmarkedContaining(intersection);

        List<Place> hEdgeCells = asList(pl(3, 5), pl(3, 3));
        List<Place> hCellsActual = _m3.unmarkedContaining(hEdge);

        List<Place> vEdgeCells = asList(pl(5, 3), pl(7, 3));
        List<Place> vCellsActual = _m3.unmarkedContaining(vEdge);

        List<Place> cellCells = asList(pl(7, 7));

        checkListEquals(iCellsActual, iCellsExpected);
        checkListEquals(hEdgeCells, hCellsActual);
        checkListEquals(vEdgeCells, vCellsActual);
        assertEquals(cellCells, _m3.unmarkedContaining(cell));
    }

    @Test
    public void testUnMarkedSymAdjacent() {
        _m3.clear();

        Place center = pl(5, 5);
        _m3.mark(3, 7, 1);
        _m3.mark(3, 3, 1);
        _m3.mark(7, 9, 1);
        _m3.mark(9, 5, 1);

        List<Place> region = asList(pl(3, 5), pl(7, 5), pl(5,5));

        List<Place> addedRegion = _m3.unmarkedSymAdjacent(center, region);

        assertTrue(addedRegion.containsAll(asList(pl(5, 7), pl(5, 3))));
    }

    @Test
    public void testUMSAEmpty() {
        _m3.clear();

        Place center = pl(9, 3);
        List<Place> region = asList(pl(9, 5));
        _m3.mark(9, 1, 1);

        assertEquals(asList(center), _m3.unmarkedSymAdjacent(center, region));
    }

    @Test
    public void testUMSA2() {
        _m3.clear();

        Place c = pl(3, 5);

        _m3.mark(3, 7, 1);

        Place r1 = pl(1, 1);
        Place r2 = pl(3, 1);
        Place r3 = pl(5, 1);
        Place r4 = pl(5, 3);
        Place r5 = pl(5, 5);
        Place r6 = pl(5, 7);
        Place r7 = pl(5, 9);

        Place a1 = pl(3, 9);
        Place a4 = pl(1, 5);
        Place a5 = pl(1, 3);

        List<Place> region = asList(r1, r2, r3, r4, r5, r6, r7, c);

        List<Place> addedRegion = _m3.unmarkedSymAdjacent(c, region);

        assertTrue(addedRegion.containsAll(asList(a1, a4, a5)));
    }

    @Test
    public void testMaxUnmarked() {
        _m3.clear();

        _m3.placeCenter(4, 8);
        _m3.mark(7, 9, 1);
        _m3.mark(9, 7, 2);

        assertSetEquals("find max region", asList(pl(3, 7), pl(3,9),
                pl(5, 7), pl(5, 9)),
                _m3.maxUnmarkedRegion(pl(4, 8)));
    }

    @Test
    public void testMaxUnmarkedBoundariesAndCenters() {
        _m3.clear();
    }

/** Unmarked symetric adjacent: test above */

    /** A helper function to toggle the boundaries of model M according
     * to x, y coordinates in B, an ix2 matrix */
    public void toggleManyBoundaries(Model m, int[][]b) {

        for (int i = 0; i < b.length; i++) {
            m.toggleBoundary(b[i][0], b[i][1]);
        }
    }

    /** A helper function to see if LIST A and LIST B
     * have the same size and same elements regardless of order. */
    public void checkListEquals(List<Place> A, List<Place>B) {
        assertTrue(A.size() == B.size() && A.containsAll(B));
    }

    /** Checks if EXPECTED has the same elements as ACTUAL. Displays MSG if not. */
    private <T> void assertSetEquals(String msg,
                                     Collection<T> expected,
                                     Collection<T> actual) {
        assertNotNull(msg, actual);
        assertEquals(msg, new HashSet<T>(expected), new HashSet<T>(actual));
    }





    /** Run the JUnit tests in this package. Add xxxTest.class entries to
     *  the arguments of runClasses to run other JUnit tests. */
    public static void main(String[] ignored) {
        textui.runClasses(UnitTests.class);
       /* textui.runClasses(ModelTests.class); */

    }

}
