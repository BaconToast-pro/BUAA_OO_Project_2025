import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        HashMap<Integer, Adventurer> adventurers = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < n; i++) {
            int op = scanner.nextInt();
            if (op == 1) {
                addAdventurer(scanner, adventurers);
            } else if (op == 2) {
                addBottle(scanner, adventurers);
            } else if (op == 3) {
                addEquipment(scanner, adventurers);
            } else if (op == 4) {
                addDurability(scanner, adventurers);
            } else if (op == 5) {
                deleteItem(scanner, adventurers);
            } else if (op == 6) {
                carryItem(scanner, adventurers);
            } else if (op == 7) {
                useBottle(scanner, adventurers);
            } else if (op == 8) {
                addFragment(scanner, adventurers);
            } else if (op == 9) {
                useFragment(scanner, adventurers);
            } else {
                fight(scanner, adventurers);
            }
        }
    }

    public static void addAdventurer(Scanner scanner, HashMap<Integer, Adventurer> adventurers) {
        int advId = scanner.nextInt();
        String name = scanner.next();
        Adventurer adv = new Adventurer(advId, name);
        adventurers.put(advId, adv);
    }

    public static void addBottle(Scanner scanner, HashMap<Integer, Adventurer> adventurers) {
        int advId = scanner.nextInt();
        int botId = scanner.nextInt();
        String name = scanner.next();
        int capacity = scanner.nextInt();
        String type = scanner.next();
        int ce = scanner.nextInt();
        adventurers.get(advId).bottle(botId, name, capacity, type, ce);
    }

    public static void addEquipment(Scanner scanner, HashMap<Integer, Adventurer> adventurers) {
        int advId = scanner.nextInt();
        int equId = scanner.nextInt();
        String name = scanner.next();
        int durability = scanner.nextInt();
        String type = scanner.next();
        int ce = scanner.nextInt();
        adventurers.get(advId).equipment(equId, name, durability, type, ce);        
    }

    public static void addDurability(Scanner scanner, HashMap<Integer, Adventurer> adventurers) {
        int advId = scanner.nextInt();
        int equId = scanner.nextInt();
        adventurers.get(advId).addDurability(equId);
    }

    public static void deleteItem(Scanner scanner, HashMap<Integer, Adventurer> adventurers) {
        int advId = scanner.nextInt();
        int id = scanner.nextInt();
        adventurers.get(advId).deleteItem(id);
    }

    public static void carryItem(Scanner scanner, HashMap<Integer, Adventurer> adventurers) {
        int advId = scanner.nextInt();
        int id = scanner.nextInt();
        adventurers.get(advId).carry(id);
    }

    public static void useBottle(Scanner scanner, HashMap<Integer, Adventurer> adventurers) {
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

    public static void addFragment(Scanner scanner, HashMap<Integer, Adventurer> adventurers) {
        int advId = scanner.nextInt();
        int fragId = scanner.nextInt();
        String name = scanner.next();
        adventurers.get(advId).fragment(fragId, name);
    }

    public static void useFragment(Scanner scanner, HashMap<Integer, Adventurer> adventurers) {
        int advId = scanner.nextInt();
        String name = scanner.next();
        int welfareId = scanner.nextInt();
        adventurers.get(advId).useFragment(name, welfareId);
    }

    public static void fight(Scanner scanner, HashMap<Integer, Adventurer> adventurers) {
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