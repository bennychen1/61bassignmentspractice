import org.junit.Test;
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

        int[] Ares = new int[]{1, 2, 3, 5};
        int[] Bres = new int[]{1, 2};

        assertArrayEquals(Ares, Arrays.remove(A, 3, 2));
        assertArrayEquals(Bres, Arrays.remove(B, 2, 5));
        assertArrayEquals(A, Arrays.remove(A, 1, 0));


    }
    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ArraysTest.class));
    }
}
