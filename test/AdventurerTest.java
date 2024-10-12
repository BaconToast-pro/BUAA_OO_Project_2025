import java.util.ArrayList;

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
    public void testGetHp() {
        Adventurer adventure = new Adventurer(100, "adv");
        assertEquals(500, adventure.getHp());
    }

    @Test
    public void testSetHp() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.setHp(500);
    }

    @Test
    public void testGetDef() {
        Adventurer adventure = new Adventurer(100, "adv");
        assertEquals(0, adventure.getDef());
    }

    @Test
    public void testBottle() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.bottle(1, "bottle", 1, "HpBottle", 1);
        adventure.bottle(1, "bottle", 1, "AtkBottle", 1);
        adventure.bottle(1, "bottle", 1, "DefBottle", 1);
    }

    @Test
    public void testEquipment() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.equipment(1, "equ", 100, "Axe", 80);
    }

    @Test
    public void testFragment() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.fragment(1, "fra");
    }

    @Test
    public void testAddDurability() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.equipment(1, "equ", 100, "Axe", 20);
        adventure.addDurability(1);
    }

    @Test
    public void testDeleteItem() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.bottle(2, "bot", 100, "DefBottle", 20);
        adventure.deleteBottle(2);
        adventure.equipment(1, "equ", 100, "Axe", 20);
        adventure.deleteEquipment(1);
    }

    @Test 
    public void testDeleteBottle() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.bottle(2, "bot", 100, "DefBottle", 20);
        adventure.deleteBottle(2);
    }

    @Test
    public void testDeleteEquipment() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.equipment(1, "equ", 100, "Axe", 20);
        adventure.deleteEquipment(1);
    }

    @Test
    public void testCarry() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.equipment(1, "equ", 100, "Axe", 20);
        adventure.carry(1);
        adventure.bottle(2, "bot", 100, "DefBottle", 20);
        adventure.carry(2);
    }

    @Test
    public void testUse() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.bottle(1, "bot", 100, "HpBottle", 20);
        adventure.bottle(2, "bot", 100, "AtkBottle", 20);
        adventure.bottle(3, "bot", 100, "DefBottle", 20);
        adventure.carry(1);
        adventure.carry(2);
        adventure.carry(3);
        adventure.use(1);
        adventure.use(2);
        adventure.use(3);
        adventure.use(1);
    }

    @Test
    public void testFindBottleName() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.bottle(1, "bot", 100, "HpBottle", 20);
        adventure.findBottleName(1);
    }

    @Test
    public void testFindMaxDef() {
        Adventurer adventure = new Adventurer(100, "adv");
        ArrayList<Adventurer> advs = new ArrayList<>();
        advs.add(adventure);
        adventure.findMaxDef(advs);
    }

    @Test
    public void testNameFindEquipment() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.equipment(1, "Axe", 100, "Axe", 1);
        assertEquals(adventure.nameFindEquipment("Axe"), adventure.nameFindEquipment("Axe"));
    }

    @Test
    public void testUseFragment() {
        Adventurer adventure = new Adventurer(100, "adv");
        for (int i = 0; i < 5; i++) {
            adventure.fragment(i, "name");
        }
        adventure.useFragment("name", 1);
        adventure.useFragment("name", 1);
    }
    
    @Test
    public void testFight() { 
        Adventurer adventure = new Adventurer(100, "adv");
        Equipment equ = new Equipment(0, "name", 1, "Blade", 2);
        ArrayList<Adventurer> advs = new ArrayList<>();
        advs.add(new Adventurer(1, "1"));
        advs.add(new Adventurer(2, "2"));
        adventure.fight(equ, advs);
    }

    @Test
    public void testPrintStatus() {
        Adventurer adventure = new Adventurer(100, "adv");
        adventure.printStatus();
    }
}
