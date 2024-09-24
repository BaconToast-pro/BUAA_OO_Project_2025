import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BottleTest {

    @Test
    public void testConstructor() {
        int expectedId = 100;
        String expectedName = "bottleTest";
        int expectedCe = 1;
        int expectedCapacity = 30;
        String expectedType = "HpBottle";
        Bottle bottle = new Bottle(expectedId, expectedName, expectedCe, 
            expectedCapacity, expectedType);
        int actualId = bottle.getId();
        assertEquals(expectedId, actualId);
        String actualName = bottle.getName();
        assertEquals(expectedName, actualName);
        int actualCe = bottle.getCe();
        assertEquals(expectedCe, actualCe);
        int actualCapacity = bottle.getCapacity();
        assertEquals(expectedCapacity, actualCapacity);
        String actualType = bottle.getType();
        assertEquals(expectedType, actualType);
    }

    @Test
    public void testSetIsEmpty() {
        Bottle bottle = new Bottle(1, "bottle", 1, 1, "HpBottle");
        bottle.setIsEmpty(true);
        assertEquals(bottle.getIsEmpty(), true);
    }

    @Test
    public void testGetIsEmpty() {
        Bottle bottle = new Bottle(1, "bottle", 1, 1, "HpBottle");
        boolean actualIsEmpty = bottle.getIsEmpty();
        assertEquals(false, actualIsEmpty);
    }

    @Test
    public void testGetType() {
        Bottle bottle = new Bottle(1, "bottle", 1, 1, "HpBottle");
        assertEquals("HpBottle", bottle.getType());
    }

    @Test
    public void testGetCapacity() {
        Bottle bottle = new Bottle(1, "bottle", 1, 1, "HpBottle");
        assertEquals(1, bottle.getCapacity());
    }

    @Test
    public void testGetCe() {
        Bottle bottle = new Bottle(1, "bottle", 1, 1, "HpBottle");
        assertEquals(1, bottle.getCe());
    }

    @Test
    public void testGetIsCarried() {
        Bottle bottle = new Bottle(1, "bottle", 1, 1, "HpBottle");
        assertEquals(false, bottle.getIsCarried());
    }
}
