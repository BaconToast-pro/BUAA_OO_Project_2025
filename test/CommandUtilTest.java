import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CommandUtilTest {
    
    @Test
    public void testConstructor() {
        ArrayList<String> message = new ArrayList<>();
        message.add("test");
        CommandUtil cmdUtil = new CommandUtil(message);
        assertEquals("test", cmdUtil.getMessage().get(0));
    }

    @Test
    public void testCommandUtil() {  
        ArrayList<ArrayList<String>> message = new ArrayList<>();        
        message.add(new ArrayList<>(Arrays.asList(
            "1", "1", "Barbarian")));  
        message.add(new ArrayList<>(Arrays.asList(
            "2", "1", "101", "Rage", "40", "HpBottle", "100")));
        message.add(new ArrayList<>(Arrays.asList(
            "3", "1", "1001", "Arrow", "20", "Sword", "90")));
        message.add(new ArrayList<>(Arrays.asList(
            "4", "1", "1001")));          
        message.add(new ArrayList<>(Arrays.asList(
            "5", "1", "1001")));
        message.add(new ArrayList<>(Arrays.asList(
            "6", "1", "101")));
        message.add(new ArrayList<>(Arrays.asList(
            "7", "1", "101")));
        message.add(new ArrayList<>(Arrays.asList(
            "8", "1", "10001", "Evolution")));
        message.add(new ArrayList<>(Arrays.asList(
            "9", "1", "Evolution", "10")));
        message.add(new ArrayList<>(Arrays.asList(
            "1", "2", "BarbarianKing")));  
        message.add(new ArrayList<>(Arrays.asList(
            "3", "1", "1002", "Cannon", "20", "Sword", "90")));
        message.add(new ArrayList<>(Arrays.asList(
            "10", "1", "Cannon", "normal", "1", "2")));
        message.add(new ArrayList<>(Arrays.asList(
            "11", "1", "2")));
        message.add(new ArrayList<>(Arrays.asList(
            "10", "1", "Cannon", "chain", "1", "2")));
        message.add(new ArrayList<>(Arrays.asList(
            "12", "1")));
        ArrayList<CommandUtil> cmdUtilArray = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            cmdUtilArray.add(new CommandUtil(message.get(i)));  
        }
        HashMap<Integer, Adventurer> advs = new HashMap<>();
        cmdUtilArray.get(0).addAdventurer(advs); 
        cmdUtilArray.get(1).addBottle(advs);        
        cmdUtilArray.get(2).addEquipment(advs);        
        cmdUtilArray.get(3).addDurability(advs);      
        cmdUtilArray.get(4).deleteItem(advs);
        cmdUtilArray.get(5).carryItem(advs);
        cmdUtilArray.get(6).useBottle(advs);
        cmdUtilArray.get(7).addFragment(advs);
        cmdUtilArray.get(8).useFragment(advs);
        cmdUtilArray.get(9).addAdventurer(advs);
        cmdUtilArray.get(10).addEquipment(advs);
        cmdUtilArray.get(11).fight(advs);
        cmdUtilArray.get(12).hire(advs);
        cmdUtilArray.get(13).fight(advs);
        advs.get(1).setAtk(2500);
        advs.get(1).setDef(2500);
        cmdUtilArray.get(14).challenge(advs);
    }
}
