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
        String type = message.get(3);
        int k = Integer.parseInt(message.get(4));
        ArrayList<Adventurer> advs = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int advIdI = Integer.parseInt(message.get(5 + i));
            advs.add(adventurers.get(advIdI));
        }
        if (type.equals("normal")) {
            Equipment equ = adventurers.get(advId).nameFindEquipment(name);
            adventurers.get(advId).normalFight(equ, advs);
        }
        else {
            ArrayList<Adventurer> potantials = new ArrayList<>();
            for (Adventurer adv : advs) {
                adv.chainFind(potantials, 0);
            }
            Equipment equ = adventurers.get(advId).nameFindEquipment(name);
            adventurers.get(advId).chainFight(equ, potantials);
        }
    }

    public void hire(HashMap<Integer, Adventurer> adventures) {
        int advId = Integer.parseInt(message.get(1));
        int hireId = Integer.parseInt(message.get(2));
        adventures.get(advId).hire(adventures.get(hireId));
    }

    public void challenge(HashMap<Integer, Adventurer> adventures) {
        int advId = Integer.parseInt(message.get(1));
        Adventurer adv = adventures.get(advId);
        Guard shd = new Shd();
        if (shd.fight(adv)) {
            Treasure treasure = TreasureFactory.createTreasure(shd);
            treasure.showInfo();
            treasure.useBy(adv);
        }
        else {
            return;
        }
        Guard flm = new Flm();
        if (flm.fight(adv)) {
            Treasure treasure = TreasureFactory.createTreasure(flm);
            treasure.showInfo();
            treasure.useBy(adv);
        }
        else {
            return;
        }
        Guard stn = new Stn();
        if (stn.fight(adv)) {
            Treasure treasure = TreasureFactory.createTreasure(stn);
            treasure.showInfo();
            treasure.useBy(adv);
        }
        else {
            return;
        }
        Guard wnd = new Wnd();
        if (wnd.fight(adv)) {
            Treasure treasure = TreasureFactory.createTreasure(wnd);
            treasure.showInfo();
            treasure.useBy(adv);
        }
        else {
            return;
        }
        Guard frz = new Frz();
        if (frz.fight(adv)) {
            Treasure treasure = TreasureFactory.createTreasure(frz);
            treasure.showInfo();
            treasure.useBy(adv);
        }
    }
}
