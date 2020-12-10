import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne obo = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("b"));
        assertTrue(palindrome.isPalindrome(" "));
        assertFalse(palindrome.isPalindrome("orange"));
        assertTrue(palindrome.isPalindrome("civic"));
    }

    @Test
    public void testOBOPalindrome() {
        assertTrue(palindrome.isPalindrome("aabb", obo));
        assertTrue(palindrome.isPalindrome("a", obo));
        assertTrue(palindrome.isPalindrome(" ", obo));
        assertTrue(palindrome.isPalindrome("", obo));
        assertFalse(palindrome.isPalindrome("hello", obo));
        assertTrue(palindrome.isPalindrome("cwizhxd", obo));
    }
}
/*Uncomment this class once you've created your Palindrome class. */