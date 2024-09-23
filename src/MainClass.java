import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        ArrayList<Adventurer> adventurers = new ArrayList<>();
        ArrayList<ArrayList<String>> inputInfo = new ArrayList<>(); // 解析后的输入将会存进该容器中, 类似于c语言的二维数组
        Scanner scanner = new Scanner(System.in); 
        int n = Integer.parseInt(scanner.nextLine().trim()); // 读取行数
        for (int i = 0; i < n; ++i) {
            String nextLine = scanner.nextLine(); // 读取本行指令
            String[] strings = nextLine.trim().split(" +"); // 按空格对行进行分割
            inputInfo.add(new ArrayList<>(Arrays.asList(strings))); // 将指令分割后的各个部分存进容器中
        }
        for (int i = 0; i < n; i++) {
            ArrayList<String> info = inputInfo.get(i);
            Adventurer adv;
            switch (s2I(info.get(0))) {
                case 1:
                    adv = new Adventurer(s2I(info.get(1)), info.get(2));
                    adventurers.add(adv);
                    break;
                case 2:
                    adv = adventurers.get(findAdventurer(adventurers, s2I(info.get(1))));
                    adv.bottle(s2I(info.get(2)), info.get(3), 
                        s2I(info.get(6)), s2I(info.get(4)), info.get(5));
                    break;
                case 3:
                    adv = adventurers.get(findAdventurer(adventurers, s2I(info.get(1))));
                    adv.equipment(s2I(info.get(2)), info.get(3), 
                        s2I(info.get(5)), s2I(info.get(4)));
                    break;
                case 4:
                    adv = adventurers.get(findAdventurer(adventurers, s2I(info.get(1))));
                    adv.addDurability(s2I(info.get(2)));
                    break;
                case 5:
                    adv = adventurers.get(findAdventurer(adventurers, s2I(info.get(1))));
                    adv.deleteItem(s2I(info.get(2)));
                    break;
                case 6:
                    adv = adventurers.get(findAdventurer(adventurers, s2I(info.get(1))));
                    adv.carry(s2I(info.get(2)));
                    break;
                case 7:
                    adv = adventurers.get(findAdventurer(adventurers, s2I(info.get(1))));
                    if (adv.use(s2I(info.get(2)))) {
                        adv.printStatus();
                    }
                    else {
                        System.out.println(adv.getName() + ' ' + 
                            "fail to use" + ' ' + adv.findBottleName(s2I(info.get(2))));
                    }
                    break;
                default:
                    break;
            }
        }
        scanner.close();
    }

    public static int s2I(String string) {
        return Integer.parseInt(string);
    }

    public static int findAdventurer(ArrayList<Adventurer> adventurers, int advId) {
        for (int i = 0; i < adventurers.size(); i++) {
            if (adventurers.get(i).getId() == advId) {
                return i;
            }
        }
        return -1;
    }
}