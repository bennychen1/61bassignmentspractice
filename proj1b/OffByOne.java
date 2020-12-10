public class OffByOne implements CharacterComparator {

    @Override
    /** Returns TRUE if the characters X and Y are next to each other in terms of order.
     * Returns FALSE otherwise */
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == 1;
    }
}
