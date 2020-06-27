/** Functions to increment and sum the elements of a WeirdList. */
class WeirdListClient {

    /** Return the result of adding N to each element of L. */
    static WeirdList add(WeirdList L, int n) {
        return L.map(new addFunc(n)); // REPLACE THIS LINE WITH THE RIGHT ANSWER.
    }

    /** Return the sum of the elements in L. */
    static int sum(WeirdList L) {
        cumulutiveSum s = new cumulutiveSum();
        L.map(s);
        return s.curSum;// REPLACE THIS LINE WITH THE RIGHT ANSWER.
    }

    /* As with WeirdList, you'll need to add an additional class or
     * perhaps more for WeirdListClient to work. Again, you may put
     * those classes either inside WeirdListClient as private static
     * classes, or in their own separate files.

     * You are still forbidden to use any of the following:
     *       if, switch, while, for, do, try, or the ?: operator.
     */
    private static class addFunc implements IntUnaryFunction {
        private int toAdd;
        public addFunc(int x) {
            this.toAdd = x;
        }

        public int apply(int x) {
            return x + toAdd;
        }
    }

    private static class cumulutiveSum implements IntUnaryFunction {
        private int curSum; /* use the fact that this is a instance variable to store values */

        cumulutiveSum() {
        }

        public int apply(int x) {
            curSum += x;
            return x;
        }
    }
}
