import static org.junit.Assert.*;
import org.junit.Test;

public class CompoundInterestTest {

    @Test
    public void testNumYears() {
        /** Sample assert statement for comparing integers.

        assertEquals(0, 0); */
        assertEquals(0, CompoundInterest.numYears(2019));
        assertEquals(-1, CompoundInterest.numYears(2018));
    }

    @Test
    public void testFutureValue() {
        double tolerance = 0.01;
        assertEquals(10.38, CompoundInterest.futureValue(10, 1.9, 2021), tolerance);
        assertEquals(10, CompoundInterest.futureValue(10, 100, 2019), tolerance);
        assertEquals(81, CompoundInterest.futureValue(100, -10, 2021), tolerance );
    }

    @Test
    public void testFutureValueReal() {
        double tolerance = 0.01;
        assertEquals(11.82, CompoundInterest.futureValueReal(10, 12, 2021, 3), tolerance);
        assertEquals(101.79, CompoundInterest.futureValueReal(100, 1.9, 2021, 1), tolerance);
    }

    @Test
    public void testTotalSavings() {
        double tolerance = 0.01;
        assertEquals(16550, CompoundInterest.totalSavings(5000,  2021, 10), tolerance);
        assertEquals(4115.45, CompoundInterest.totalSavings(1000, 2022, 1.9), tolerance);
    }

    @Test
    public void testTotalSavingsReal() {
        double tolerance = 0.01;
        assertEquals(15599.96, CompoundInterest.totalSavingsReal(5000, 2021, 10, 3), tolerance);
        assertEquals(3994.42, CompoundInterest.totalSavingsReal(1000, 2022, 1.9, 1), tolerance);
    }


    /* Run the unit tests in this file. */
    public static void main(String... args) {
        System.exit(ucb.junit.textui.runClasses(CompoundInterestTest.class));
    }
}
