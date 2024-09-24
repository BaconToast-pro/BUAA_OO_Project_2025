import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EquipmentTest {
    
    @Test
    public void testConstructor() {
        Equipment equipment = new Equipment(1, "equ", 100, 20);
        assertEquals(1, equipment.getId());
        assertEquals("equ", equipment.getName());
        assertEquals(100, equipment.getCe());
        assertEquals(20, 20);
    }

    @Test
    public void testChangeDurability() {
        Equipment equipment = new Equipment(1, "equ", 100, 20);
        equipment.changeDurability(1);
    }
}
