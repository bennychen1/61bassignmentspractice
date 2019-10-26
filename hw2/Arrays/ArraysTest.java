import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/** FIXME
 *  @author FIXME
 */

public class ArraysTest {

    @Test
    public void testCatenate() {
        int[] A = new int[]{1, 2, 3};
        int[] B = new int[]{4, 5, 6};
        int[] BNull = new int[]{};
        int[] CNull = new int[]{};
        int[] AB = new int[]{1, 2, 3, 4, 5, 6};

        assertArrayEquals(AB, Arrays.catenate(A, B));
        assertArrayEquals(A, Arrays.catenate(A, BNull));
        assertArrayEquals(B, Arrays.catenate(BNull, B));
        assertArrayEquals(new int[]{}, Arrays.catenate(CNull, BNull));
    }

    @Test
    public void testRemove() {
        int[] A = new int[]{1, 2, 3, 4, 5, 6};
        int[] B = new int[]{1, 2, 3};

        int[] Ares = new int[]{1, 2, 3, 6};
        int[] Bres = new int[]{1};

        assertArrayEquals(Ares, Arrays.remove(A, 4, 2));
        assertArrayEquals(Bres, Arrays.remove(B, 2, 5));
        assertArrayEquals(A, Arrays.remove(A, 1, 0));

    }

    @Test
    public void testArrayNaturalRuns() {
        int[] A = new int[]{5, 6, 3, 2, 7};
        int[] B = new int[]{1, 1, 1};
        int[] C = new int[]{1};

        assertArrayEquals(new int[][]{{5, 6}, {3}, {2, 7}}, Arrays.naturalRuns(A));
        assertArrayEquals(new int[][]{{1}, {1}, {1}}, Arrays.naturalRuns(B));
        assertArrayEquals(new int[][]{{1}}, Arrays.naturalRuns(C));
    }
    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ArraysTest.class));
    }
}
