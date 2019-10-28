import org.junit.Test;
import static org.junit.Assert.*;

/** FIXME
 *  @author FIXME
 */

public class MatrixUtilsTest {
    /** FIXME
     */
    @Test
    public void testAccumulateVeritcal(){
        double[][] e = new double[][]{{1, 5, 10}, {6, 7, 3}, {13, 9, 11}};
        double[][] am = new double[][]{{1, 5, 10}, {7, 8, 8}, {20, 16, 19}};

        double[][] oneCol = new double[][]{{1}, {6}, {13}};
        double[][] oneColAm = new double[][]{{1}, {7}, {20}};

        double[][]ones = new double[][]{{1, 1, 1}, {1, 1, 1}};
        double[][]onesAm = new double[][]{{1, 1, 1}, {2, 2, 2}};

        assertArrayEquals(am, MatrixUtils.accumulateVertical(e));
        assertArrayEquals(new double[][]{{1, 5, 10}, {6, 7, 3}, {13, 9, 11}}, e); /* Check if non-destructive */

        assertArrayEquals(oneColAm, MatrixUtils.accumulateVertical(oneCol));

        assertArrayEquals(onesAm, MatrixUtils.accumulateVertical(ones)); /* Test equals */
    }

    @Test
    public void testAccumulate() {

        double[][] e = new double[][]{{1, 5, 10}, {6, 7, 3}, {13, 9, 11}};
        double[][] eCopy = new double[][]{{1, 5, 10}, {6, 7, 3}, {13, 9, 11}};
        double[][] amV = new double[][]{{1, 5, 10}, {7, 8, 8}, {20, 16, 19}};
        double[][]amO = new double[][]{{1, 6, 16}, {6, 8, 9}, {13, 15, 19}};

        double[][] ones = new double[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        double[][] onesAm = new double[][]{{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};

        assertArrayEquals(amV, MatrixUtils.accumulate(e, MatrixUtils.Orientation.VERTICAL));
        assertArrayEquals(amO, MatrixUtils.accumulate(e, MatrixUtils.Orientation.HORIZONTAL));
        assertArrayEquals(eCopy, e);

        assertArrayEquals(onesAm, MatrixUtils.accumulate(ones, MatrixUtils.Orientation.HORIZONTAL));
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(MatrixUtilsTest.class));
    }
}
