import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FragmentTest {
    
    @Test
    public void testConstructor() {
        Fragment fragment = new Fragment(0, "fra");
        assertEquals(0, fragment.getId());
        assertEquals("fra", fragment.getName());
    }
}
