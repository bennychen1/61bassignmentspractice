/* NOTE: The file Utils.java contains some functions that may be useful
 * in testing your answers. */

/** HW #2, Problem #1. */

/** List problem.
 *  @author
 */
class Lists {
    /** Return the list of lists formed by breaking up L into "natural runs":
     *  that is, maximal strictly ascending sublists, in the same order as
     *  the original.  For example, if L is (1, 3, 7, 5, 4, 6, 9, 10, 10, 11),
     *  then result is the four-item list
     *            ((1, 3, 7), (5), (4, 6, 9, 10), (10, 11)).
     *  Destructive: creates no new IntList items, and may modify the
     *  original list pointed to by L. */
    static IntList2 naturalRuns(IntList L) {
        /* *Replace this body with the solution. */

        if (L == null) {
            return null;
        }

        IntList e = L.tail;
        IntList cur = L;
        IntList2 res = new IntList2(null, null);
        IntList2 p = res;

        for(; e != null; e = e.tail) {
            if (cur.head < e.head) {
                cur = e;

            } else {
                p.tail = IntList2.list(L);
                L = e;
                cur.tail = null;
                cur = L;
                p = p.tail;
            }
        }

        p.tail = IntList2.list(L);
        return res.tail;
    }
}
