public class OffByN implements CharacterComparator {

    /** The difference between a character and its equivalent*/
    int N;

    public OffByN(int N) {
        this.N = N;
    }

    @Override
    /** Returns TRUE if character X equals character Y if X is
     * off of Y by N*/
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == N;
    }
}
