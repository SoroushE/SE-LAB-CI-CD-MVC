import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SimpleTest {

    @Test

    public void testConcatenate() {

        Simple s = new Simple();
        String result = s.concat("one", "two");
        assertEquals("onetwo", result);

    }
}