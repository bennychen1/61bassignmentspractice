import java.io.Reader;
import java.io.IOException;

/** Translating Reader: a stream that is a translation of an
 *  existing reader.
 *  @author
 */
public class TrReader extends Reader {
    /** A new TrReader that produces the stream of characters produced
     *  by STR, converting all characters that occur in FROM to the
     *  corresponding characters in TO.  That is, change occurrences of
     *  FROM.charAt(i) to TO.charAt(i), for all i, leaving other characters
     *  in STR unchanged.  FROM and TO must have the same length. */
    Reader str;
    String from;
    String to;
    public TrReader(Reader str, String from, String to) {
        // FILL IN
        this.str = str;
        this.from = from;
        this.to = to;

    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int curCharInt = this.str.read();

        if (curCharInt == -1) {
            return -1;
        }

        char curChar = (char) curCharInt;

        int count = 0;
        while (curChar != -1 && len > 0 && off < cbuf.length) {
           if (this.from.indexOf(curChar) != -1) {
               curChar = this.to.charAt(this.from.indexOf(curChar));
           }

           cbuf[off] = curChar;

            off += 1;
            len -= 1;
            count += 1;
            curChar = (char) this.str.read();
        }

        return count;
    }

    @Override
    public void close() throws IOException {
        this.str.close();
    }

    // FILL IN
    // NOTE: Until you fill in the right methods, the compiler will
    //       reject this file, saying that you must declare TrReader
    //     abstract.  Don't do that; define the right methods instead!
}


