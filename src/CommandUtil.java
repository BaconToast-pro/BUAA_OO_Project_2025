import java.util.ArrayList;
import java.util.HashMap;

public class CommandUtil {
    private final ArrayList<String> message;

    public CommandUtil(ArrayList<String> message) {
        this.message = message;
    }

    public ArrayList<String> getMessage() {
        return message;
    }

    public void addAdventurer(HashMap<Integer, Adventurer> adventurers) {
        int advId = Integer.parseInt(message.get(1));
        String name = message.get(2);
        Adventurer adv = new Adventurer(advId, name);
        adventurers.put(advId, adv);
    }

    public void addBottle(HashMap<Integer, Adventurer> adventurers) {
        int advId = Integer.parseInt(message.get(1));
        int botId = Integer.parseInt(message.get(2));
        String name = message.get(3);
        int capacity = Integer.parseInt(message.get(4));
        String type = message.get(5);
        int ce = Integer.parseInt(message.get(6));
        adventurers.get(advId).bottle(botId, name, capacity, type, ce);
    }

    public void addEquipment(HashMap<Integer, Adventurer> adventurers) {
        int advId = Integer.parseInt(message.get(1));
        int equId = Integer.parseInt(message.get(2));
        String name = message.get(3);
        int durability = Integer.parseInt(message.get(4));
        String type = message.get(5);
        int ce = Integer.parseInt(message.get(6));
        adventurers.get(advId).equipment(equId, name, durability, type, ce);        
    }

    public void addDurability(HashMap<Integer, Adventurer> adventurers) {
        int advId = Integer.parseInt(message.get(1));
        int equId = Integer.parseInt(message.get(2));
        adventurers.get(advId).addDurability(equId);
    }

    public void deleteItem(HashMap<Integer, Adventurer> adventurers) {
        int advId = Integer.parseInt(message.get(1));
        int id = Integer.parseInt(message.get(2));
        adventurers.get(advId).deleteItem(id);
    }

    public void carryItem(HashMap<Integer, Adventurer> adventurers) {
        int advId = Integer.parseInt(message.get(1));
        int id = Integer.parseInt(message.get(2));
        adventurers.get(advId).carry(id);
    }

    public void useBottle(HashMap<Integer, Adventurer> adventurers) {
        int advId = Integer.parseInt(message.get(1));
        int botId = Integer.parseInt(message.get(2));
        if (adventurers.get(advId).use(botId)) {
            adventurers.get(advId).printStatus();
        }
        else {
            System.out.printf("%s fail to use %s\n", 
                adventurers.get(advId).getName(), adventurers.get(advId).findBottleName(botId));
        }
    }

    public void addFragment(HashMap<Integer, Adventurer> adventurers) {
        int advId = Integer.parseInt(message.get(1));
        int fragId = Integer.parseInt(message.get(2));
        String name = message.get(3);
        adventurers.get(advId).fragment(fragId, name);
    }

    public void useFragment(HashMap<Integer, Adventurer> adventurers) {
        int advId = Integer.parseInt(message.get(1));
        String name = message.get(2);
        int welfareId = Integer.parseInt(message.get(3));
        adventurers.get(advId).useFragment(name, welfareId);
    }

    public void fight(HashMap<Integer, Adventurer> adventurers) {
        int advId = Integer.parseInt(message.get(1));
        String name = message.get(2);
        int k = Integer.parseInt(message.get(3));
        ArrayList<Adventurer> advs = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int advIdI = Integer.parseInt(message.get(4 + i));
            advs.add(adventurers.get(advIdI));
        }
        adventurers.get(advId).fight(adventurers.get(advId).nameFindEquipment(name), advs);
    }
}
