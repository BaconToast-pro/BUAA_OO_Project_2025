import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> inputInfo = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        for (int i = 0; i < n; ++i) {
            String nextLine = scanner.nextLine();
            String[] strings = nextLine.trim().split(" +");
            inputInfo.add(new ArrayList<>(Arrays.asList(strings)));
        }
        HashMap<Integer, Adventurer> adventurers = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int op = Integer.parseInt(inputInfo.get(i).get(0));
            CommandUtil cmdUtil = new CommandUtil(inputInfo.get(i));
            if (op == 1) {
                cmdUtil.addAdventurer(adventurers);
            } else if (op == 2) {
                cmdUtil.addBottle(adventurers);
            } else if (op == 3) {
                cmdUtil.addEquipment(adventurers);
            } else if (op == 4) {
                cmdUtil.addDurability(adventurers);
            } else if (op == 5) {
                cmdUtil.deleteItem(adventurers);
            } else if (op == 6) {
                cmdUtil.carryItem(adventurers);
            } else if (op == 7) {
                cmdUtil.useBottle(adventurers);
            } else if (op == 8) {
                cmdUtil.addFragment(adventurers);
            } else if (op == 9) {
                cmdUtil.useFragment(adventurers);
            } else if (op == 10) {
                cmdUtil.fight(adventurers);
            } else if (op == 11) {
                cmdUtil.hire(adventurers);
            } else {
                cmdUtil.challenge(adventurers);
            }
        }
        scanner.close();
    }
}