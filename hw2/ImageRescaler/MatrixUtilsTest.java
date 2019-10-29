import org.junit.Test;
import static org.junit.Assert.*;

/** FIXME
 *  @author FIXME
 */

public class MatrixUtilsTest {

    static double DELTA = 0;
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

    @Test
    public void testTranspose() {
        double[][] m = new double[][]{{1, 2, 3}, {4, 5, 6}};
        double[][] mt = new double[][]{{1, 4}, {2, 5}, {3, 6}};


        assertArrayEquals(mt, MatrixUtils.transpose(m));
    }

    @Test
    public void testFindVerticalSeam() {

        double[][] e = new double[][]{{2, 9, 5}, {3, 1, 7}, {5, 10, 7}};
        int[] s = new int[]{0, 1, 0};


        double[][] one = new double[][]{{1}, {10}, {15}};
        int[] oneSeam = new int[]{0, 0, 0};

        double[][]allOnes = new double[][]{{1, 1, 1}, {1, 1, 1}};
        int[]sAllOnes = new int[]{0, 0};

        assertEquals(totalE(e, s), totalE(e, MatrixUtils.findVerticalSeam(e)), DELTA);
        assertEquals(2, totalE(allOnes, MatrixUtils.findVerticalSeam(allOnes)), DELTA);

        assertArrayEquals(oneSeam, MatrixUtils.findVerticalSeam(one));
    }

    @Test
    public void testFindSeams(){

        double[][] horizontal = new double[][]{{2, 9, 5}, {3, 1, 7}, {5, 10 , 7}};
        double horizontalSeam = 8;
        double verticalSeam = 8;
        int[] hSeam = new int[]{0, 1, 0};

        double[][] allOnes = new double[][]{{1, 1, 1}, {1, 1, 1}};
        double allOnesHSeam = 3;
        double allOnesVSeam = 2;

        assertEquals(horizontalSeam, totalEH(horizontal,
                MatrixUtils.findSeam(horizontal, MatrixUtils.Orientation.HORIZONTAL)), DELTA);
        assertEquals(verticalSeam, totalE(horizontal,
                MatrixUtils.findSeam(horizontal, MatrixUtils.Orientation.VERTICAL)), DELTA);
        assertArrayEquals(hSeam, MatrixUtils.findSeam(horizontal, MatrixUtils.Orientation.HORIZONTAL));

        assertEquals(allOnesHSeam, totalEH(allOnes,
                MatrixUtils.findSeam(allOnes, MatrixUtils.Orientation.HORIZONTAL)), DELTA);
        assertEquals(allOnesVSeam, totalE(allOnes,
                MatrixUtils.findSeam(allOnes, MatrixUtils.Orientation.VERTICAL)), DELTA);
    }

    /* Finds the total value of selecting element S[i] in the ith row of E */
    public double totalE(double[][]e, int[]s) {
        double total = 0;
        for (int i = 0; i < e.length; i++) {
            total += e[i][s[i]];
        }

        return total;
    }

    public double totalEH(double[][]e, int[]s) {
        return totalE(MatrixUtils.transpose(e), s);
    }

    @Test
    public void testFindMin() {
        double[] m = new double[]{1, 5, 7, 2, 3};

        assertEquals(0, MatrixUtils.findMin(m, 0, 4));
        assertEquals(3, MatrixUtils.findMin(m, 3, 4));
        assertEquals(0, MatrixUtils.findMin(m, 0, 1));
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(MatrixUtilsTest.class));
    }
}
