import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Adventurer extends Unit {
    private int hp = 500;
    private int atk = 1;
    private int def = 0;
    private final HashMap<Integer, Bottle> bottles = new HashMap<>();
    private final HashMap<Integer, Equipment> equipments = new HashMap<>();
    private final HashSet<Fragment> fragments = new HashSet<>();

    public Adventurer(int id, String name) {
        super(id, name);
        super.setCe(this.atk + this.def);
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDef() {
        return this.def;
    }

    public void bottle(int id, String name, int capacity, String type, int ce) {
        Bottle bottle;
        switch (type) {
            case "HpBottle":
                bottle = new Bottle(id, name, capacity, type, ce);
                bottles.put(id, bottle);
                break;
            case "AtkBottle":
                bottle = new Bottle(id, name, capacity, type, ce);
                bottles.put(id, bottle);
                break;
            case "DefBottle":
                bottle = new Bottle(id, name, capacity, type, ce);
                bottles.put(id, bottle);
                break;
            default:
                break;
        }
    }

    public void equipment(int id, String name, int durability, String type, int ce) {
        Equipment equipment = new Equipment(id, name, durability, type, ce);
        equipments.put(id, equipment);
    }

    public void fragment(int id, String name) {
        Fragment fragment = new Fragment(id, name);
        fragments.add(fragment);
    }

    public void addDurability(int id) {
        Equipment item = equipments.get(id);
        item.changeDurability(1);
        item.printStatus();
    }

    public void deleteItem(int id) {
        deleteBottle(id);
        deleteEquipment(id);
    }

    public void deleteBottle(int id) {
        Bottle item = bottles.get(id);
        item.printStatus();
        bottles.remove(id);
    }

    public void deleteEquipment(int id) {
        Equipment item = equipments.get(id);
        item.printStatus();
        equipments.remove(id);
    }

    public void carry(int id) {
        if (bottles.containsKey(id)) {
            bottles.get(id).carry();
        }
        if (equipments.containsKey(id)) {
            equipments.get(id).carry();
        }
    }

    public boolean use(int id) {
        Bottle item = bottles.get(id);
        if (item.getId() == id && item.getIsCarried()) {
            if (!item.getIsEmpty()) {
                switch (item.getType()) {
                    case "HpBottle":
                        this.hp += item.getCapacity();
                        break;
                    case "AtkBottle":
                        this.atk += item.getCe() + item.getCapacity() / 100;
                        break;
                    case "DefBottle":
                        this.def += item.getCe() + item.getCapacity() / 100;
                        break;
                    default:
                        break;
                }
                item.setIsEmpty(true);
            }
            else {
                bottles.remove(id);
            }
            return true;
        }
        return false;
    }

    public String findBottleName(int id) {
        Bottle item = bottles.get(id);
        return item.getName();
    }

    public int findMaxDef(ArrayList<Adventurer> advs) {
        int maxDef = -1;
        for (Adventurer obj : advs) {
            if (obj.getDef() > maxDef) {
                maxDef = obj.getDef();
            }
        }
        return maxDef;
    }

    public Equipment nameFindEquipment(String name) {
        for (Equipment value : equipments.values()) {
            if (value.getName().equals(name)) {
                return value;
            }
        }
        return null;
    }

    public void useFragment(String name, int welfareId) {
        Fragment[] fragmentArray = new Fragment[5];
        int fragmentCnt = 0;
        for (Fragment obj : fragments) {
            if (obj.getName().equals(name)) {
                fragmentArray[fragmentCnt++] = obj;
            }
            if (fragmentCnt == 5) {
                break;
            }
        }
        if (fragmentCnt == 5) {
            for (int i = 0; i < 5; i++) {
                fragments.remove(fragmentArray[i]);
            }
            if (bottles.containsKey(welfareId)) {
                bottles.get(welfareId).setIsEmpty(false);
                System.out.println(bottles.get(welfareId).getName() 
                    + ' ' + bottles.get(welfareId).getCapacity());    
            }
            else if (equipments.containsKey(welfareId)) {
                equipments.get(welfareId).changeDurability(1);
                System.out.println(equipments.get(welfareId).getName() 
                    + ' ' + equipments.get(welfareId).getDurability());
            }
            else {
                bottle(welfareId, name, 100, "HpBottle", 0);
                System.out.printf("Congratulations! HpBottle %s acquired\n", name);
            }
        }
        else {
            System.out.println(fragmentCnt + ": Not enough fragments collected yet");
        }
    }

    public void fight(Equipment equ, ArrayList<Adventurer> advs) {
        if (equ.getIsCarried() && this.atk + equ.getCe() > findMaxDef(advs)) {
            switch (equ.getType()) {
                case "Axe":
                    for (Adventurer obj : advs) {
                        obj.setHp(obj.getHp() / 10);
                    }
                    break;
                case "Sword":
                    for (Adventurer obj : advs) {
                        obj.setHp(obj.getHp() - (equ.getCe() + this.atk - obj.getDef()));
                    }
                    break;
                case "Blade":
                    for (Adventurer obj : advs) {
                        obj.setHp(obj.getHp() - (equ.getCe() + this.atk));
                    }
                    break;
                default:
                    System.out.println("Error: function \"fight()\"\n");
                    break;
            }
            equ.changeDurability(-1);
            if (equ.getDurability() == 0) {
                equipments.remove(equ.getId());
            }
            for (Adventurer obj : advs) {
                System.out.println(obj.getName() + ' ' + obj.getHp());
            }
        }
        else {
            System.out.printf("Adventurer %d defeated\n", this.getId());
        }
    }

    public void printStatus() {
        System.out.println(super.getName() + ' ' + this.hp + ' ' + this.atk + ' ' + this.def);
    }
}
