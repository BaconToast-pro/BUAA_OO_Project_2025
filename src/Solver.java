import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Solver {
    private final Scanner scanner;
    private final HashMap<Integer, Adventurer> adventurers = new HashMap<>();

    public Solver(Scanner scanner) {
        this.scanner = scanner;
    }

    public void solve() {
        int n = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < n; i++) {
            int op = scanner.nextInt();
            if (op == 1) {
                addAdventurer();
            } else if (op == 2) {
                addBottle();
            } else if (op == 3) {
                addEquipment();
            } else if (op == 4) {
                addDurability();
            } else if (op == 5) {
                deleteItem();
            } else if (op == 6) {
                carryItem();
            } else if (op == 7) {
                useBottle();
            } else if (op == 8) {
                addFragment();
            } else if (op == 9) {
                useFragment();
            } else {
                fight();
            }
        }
    }

    public void addAdventurer() {
        int advId = scanner.nextInt();
        String name = scanner.next();
        Adventurer adv = new Adventurer(advId, name);
        adventurers.put(advId, adv);
    }

    public void addBottle() {
        int advId = scanner.nextInt();
        int botId = scanner.nextInt();
        String name = scanner.next();
        int capacity = scanner.nextInt();
        String type = scanner.next();
        int ce = scanner.nextInt();
        adventurers.get(advId).bottle(botId, name, capacity, type, ce);
    }

    public void addEquipment() {
        int advId = scanner.nextInt();
        int equId = scanner.nextInt();
        String name = scanner.next();
        int durability = scanner.nextInt();
        String type = scanner.next();
        int ce = scanner.nextInt();
        adventurers.get(advId).equipment(equId, name, durability, type, ce);        
    }

    public void addDurability() {
        int advId = scanner.nextInt();
        int equId = scanner.nextInt();
        adventurers.get(advId).addDurability(equId);
    }

    public void deleteItem() {
        int advId = scanner.nextInt();
        int id = scanner.nextInt();
        adventurers.get(advId).deleteItem(id);
    }

    public void carryItem() {
        int advId = scanner.nextInt();
        int id = scanner.nextInt();
        adventurers.get(advId).carry(id);
    }

    public void useBottle() {
        int advId = scanner.nextInt();
        int botId = scanner.nextInt();
        if (adventurers.get(advId).use(botId)) {
            adventurers.get(advId).printStatus();
        }
        else {
            System.out.printf("%s fail to use %s\n", 
                adventurers.get(advId).getName(), adventurers.get(advId).findBottleName(botId));
        }
    }

    public void addFragment() {
        int advId = scanner.nextInt();
        int fragId = scanner.nextInt();
        String name = scanner.next();
        adventurers.get(advId).fragment(fragId, name);
    }

    public void useFragment() {
        int advId = scanner.nextInt();
        String name = scanner.next();
        int welfareId = scanner.nextInt();
        adventurers.get(advId).useFragment(name, welfareId);
    }

    public void fight() {
        int advId = scanner.nextInt();
        String name = scanner.next();
        int k = scanner.nextInt();
        ArrayList<Adventurer> advs = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int advIdI = scanner.nextInt();
            advs.add(adventurers.get(advIdI));
            adventurers.get(advId).fight(adventurers.get(advId).nameFindEquipment(name), advs);
        }
    }
}
