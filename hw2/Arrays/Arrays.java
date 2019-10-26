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

        if (A == null || A.length == 0 || len == 0) {
            return A;
        }

        if (start + len > A.length) {
            int [] res = new int[start - 1]; /* Copy up to but not including the START-th element */
            System.arraycopy(A, 0, res, 0, res.length);
            return res;
        }

        int[] res = new int[A.length - len];
        System.arraycopy(A, 0, res, 0, start - 1);  /* Copy up to but not including the START-th element */
        System.arraycopy(A, start + len - 1, res, start - 1, res.length - start + 1);
        /* Start copying at the item at index after the last removed item. Already copied START - 1 elements.  */
        /* start copying to start - 1 because of 0 indexing */

        return res;
    }

    /* E. */
    /** Returns the array of arrays formed by breaking up A into
     *  maximal ascending lists, without reordering.
     *  For example, if A is {1, 3, 7, 5, 4, 6, 9, 10}, then
     *  returns the three-element array
     *  {{1, 3, 7}, {5}, {4, 6, 9, 10}}. */
    static int[][] naturalRuns(int[] A) {
        /* *Replace this body with the solution. */

        int numRuns = 0;
        int index = 0;

        while (index < A.length - 1) {
            if (A[index] >= A[index + 1]) {
                numRuns += 1;
            }

            index += 1;
        }

        int[][]res = new int[numRuns + 1][]; /* Plus one to get the last run */

        int start = 0;
        int i = 0;
        int c = 1;
        int resP = 0;

        for (; c < A.length; i++, c++) {
            if (A[i] < A[c]) {
                continue;
            } else {
                int[] curArr = new int[i - start + 1];
                System.arraycopy(A, start, curArr, 0, i - start + 1);
                res[resP] = curArr;
                start = c;
                resP += 1;
            }
        }

        int[] curArr = new int[i - start + 1];
        System.arraycopy(A, start, curArr, 0, curArr.length);
        res[resP] = curArr;
        
        return res;
    }
}
