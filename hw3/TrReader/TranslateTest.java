import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.IOException;

import org.junit.Test;
import static org.junit.Assert.*;

import ucb.junit.textui;

public class TranslateTest {

    @Test
    public void testTranslate() {
        String S = "hello there";
        String from = "ht";
        String to = "xr";
        String result = "xello rxere";
        assertEquals(result, Translate.translate(S, from, to));

    }

    @Test
    public void testNoTranslate() {
        String s = "green and yellow";
        String from = "zbc";
        String to = "gye";
        String result = "green and yellow";
        assertEquals(result, Translate.translate(s, from, to));
    }

    @Test
    public void testSpace() {
        String s = "  abc ";
        String from = " a";
        String to = "ne";
        String result = "nnebcn";
        assertEquals(result, Translate.translate(s, from, to));
    }

    public static void main(String[] args) {
        System.exit(textui.runClasses(TranslateTest.class));
    }
}
