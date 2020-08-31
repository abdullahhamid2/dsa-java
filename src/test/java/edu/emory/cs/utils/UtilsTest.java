package edu.emory.cs.utils;

public class UtilsTest {

    import org.junit.Test;
import static org.junit.Assert.assertEquals;

    @Test
    public void getMiddleIndexTest() {
        assertEquals(5, Utils.getMiddleIndex(0, 10));
    }
}
