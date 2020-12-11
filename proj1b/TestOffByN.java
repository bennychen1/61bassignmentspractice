import static org.junit.Assert.*;
import org.junit.Test;
public class TestOffByN {
    @Test
    public void testOffByNEqualsChars() {
        OffByN offby5 = new OffByN(5);
        assertFalse(offby5.equalChars('a', 'b'));
        assertTrue(offby5.equalChars('B', 'G'));
    }
}
