import org.junit.Test;
import static org.junit.Assert.*;

/** FIXME
 *
 *  @author FIXME
 */

public class ListsTest {

    @Test
    public void testNaturalRuns() {
        IntList A = IntList.list(2, 3, 5, 1, 7, 9, 10, 9, 3);
        IntList B = IntList.list(1, 1, 1);
        IntList C = IntList.list(3);

        IntList2 ARuns = IntList2.list(new int[][]{{2, 3, 5}, {1, 7, 9, 10}, {9},{3}});
        IntList2 BRuns = IntList2.list(new int[][]{{1}, {1}, {1}});
        IntList2 CRuns = IntList2.list(new int[][]{{3}});

        assertEquals(ARuns, Lists.naturalRuns(A));
        assertEquals(BRuns, Lists.naturalRuns(B));
        assertEquals(CRuns, Lists.naturalRuns(C));
    }

    // It might initially seem daunting to try to set up
    // Intlist2 expected.
    //
    // There is an easy way to get the IntList2 that you want in just
    // few lines of code! Make note of the IntList2.list method that
    // takes as input a 2D array.

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ListsTest.class));
    }
}
