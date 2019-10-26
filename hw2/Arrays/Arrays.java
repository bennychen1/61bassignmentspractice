/* NOTE: The file Arrays/Utils.java contains some functions that may be useful
 * in testing your answers. */

/** HW #2 */

/** Array utilities.
 *  @author
 */
class Arrays {
    /* C. */
    /** Returns a new array consisting of the elements of A followed by the
     *  the elements of B. */
    static int[] catenate(int[] A, int[] B) {
        /* *Replace this body with the solution. */
        if (A == B || B == null) {
            return A;
        }

        if (A == null) {
            return B;
        }

        int[] res = new int[A.length + B.length];

        int p = 0;

        for (int count = 0; count < A.length; count++, p++) { /* counters run everytime besides the first iteration */
            res[count] = A[count];
        }

        for (int count = 0; count < B.length; count++, p++) {
            res[p] = B[count];
        }

        return res;
    }

    /** Returns the array formed by removing LEN items from A,
     *  beginning with item #START. */
    static int[] remove(int[] A, int start, int len) {
        /* *Replace this body with the solution. */

        if (A == null || A.length == 0) {
            return A;
        }

        if (start + len > A.length) {
            retu
        }

        int[] res = new int[A.length - len];

        int p = 0;

        return new int[4];
    }

    /* E. */
    /** Returns the array of arrays formed by breaking up A into
     *  maximal ascending lists, without reordering.
     *  For example, if A is {1, 3, 7, 5, 4, 6, 9, 10}, then
     *  returns the three-element array
     *  {{1, 3, 7}, {5}, {4, 6, 9, 10}}. */
    static int[][] naturalRuns(int[] A) {
        /* *Replace this body with the solution. */
        return null;
    }
}
