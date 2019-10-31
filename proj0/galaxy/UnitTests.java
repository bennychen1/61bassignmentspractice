package galaxy;

import static org.junit.Assert.*;
import org.junit.Test;
import ucb.junit.textui;

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
        _m3.board()[2][2] = "c";
        _m3.board()[5][3] = "c";
        Place cen = Place.pl(5, 3);

        assertTrue(_m3.isCenter(2, 2));
        assertTrue(_m3.isCenter(cen));
        assertFalse(_m3.isCenter(0, 1));
    }

    @Test
    public void testIsBoundary() {
        _m3.board()[0][5] = "b";
        _m3.board()[1][2] = "b";

        Place bound = Place.pl(1, 2);

        assertFalse(_m3.isBoundary(5, 1));
        assertTrue(_m3.isBoundary(0, 5));
        assertTrue(_m3.isBoundary(bound));
    }




    /** Run the JUnit tests in this package. Add xxxTest.class entries to
     *  the arguments of runClasses to run other JUnit tests. */
    public static void main(String[] ignored) {
        textui.runClasses(ModelTests.class);

    }

}
