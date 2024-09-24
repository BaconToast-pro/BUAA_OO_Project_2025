import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AdventurerTest {
    
    @Test
    public void testConstructor() {
        int expectedId = 100;
        String expectedName = "adv";
        Adventurer adventure = new Adventurer(expectedId, expectedName);
        assertEquals(expectedId, adventure.getId());
        assertEquals(expectedName, adventure.getName());
    }

    @Test
    public void testBottle() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.bottle(1, "bottle", 1, 1, "HpBottle");
        adventure.bottle(1, "bottle", 1, 1, "AtkBottle");
        adventure.bottle(1, "bottle", 1, 1, "DefBottle");
    }

    @Test
    public void testEquipment() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.equipment(1, "equ", 100, 80);
    }

    @Test
    public void testAddDurability() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.equipment(1, "equ", 100, 20);
        adventure.addDurability(1);
    }

    @Test
    public void testDeleteItem() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.bottle(2, "bot", 100, 20, "DefBottle");
        adventure.deleteBottle(1);
        adventure.equipment(1, "equ", 100, 20);
        adventure.deleteEquipment(1);
        adventure.deleteItem(1);
    }

    @Test 
    public void testDeleteBottle() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.bottle(2, "bot", 100, 20, "DefBottle");
        adventure.deleteBottle(1);
    }

    @Test
    public void testDeleteEquipment() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.equipment(1, "equ", 100, 20);
        adventure.deleteEquipment(1);
    }

    @Test
    public void testCarry() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.equipment(1, "equ", 100, 20);
        adventure.carry(1);
        adventure.bottle(2, "bot", 100, 20, "DefBottle");
        adventure.carry(2);
    }

    @Test
    public void testUse() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.bottle(1, "bot", 100, 20, "HpBottle");
        adventure.use(1);
    }

    @Test
    public void testFindBottleName() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.bottle(1, "bot", 100, 20, "HpBottle");
        adventure.findBottleName(1);
    }

    @Test
    public void testPrintStatus() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.printStatus();
    }
}
