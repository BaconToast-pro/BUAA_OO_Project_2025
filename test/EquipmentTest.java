import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EquipmentTest {
    
    @Test
    public void testConstructor() {
        Equipment equipment = new Equipment(1, "equ", 100, "Axe", 20);
        assertEquals(1, equipment.getId());
        assertEquals("equ", equipment.getName());
        assertEquals(20, equipment.getCe());
        assertEquals(20, 20);
    }

    @Test
    public void testChangeDurability() {
        Equipment equipment = new Equipment(1, "equ", 100, "Axe", 20);
        equipment.changeDurability(1);
    }

    @Test
    public void testGetDurability() {
        Equipment equipment = new Equipment(1, "equ", 100, "Axe", 20);
        assertEquals(100, equipment.getDurability());
    }

    @Test
    public void testGetIsCarried() {
        Equipment equipment = new Equipment(1, "equ", 100, "Axe", 20);
        assertEquals(false, equipment.getIsCarried());
    }

    @Test
    public void testGetType() {
        Equipment equipment = new Equipment(1, "equ", 100, "Axe", 20);
        assertEquals("Axe", equipment.getType());
    }
}
