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

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(MatrixUtilsTest.class));
    }
}
