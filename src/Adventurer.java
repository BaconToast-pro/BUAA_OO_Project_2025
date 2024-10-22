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
    private final ArrayList<Adventurer> hires = new ArrayList<>();
    private final HashMap<Integer, Integer> helpTime = new HashMap<>();

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

    public int getAtk() {
        return this.atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
        super.setCe(this.atk + this.def);
    }

    public int getDef() {
        return this.def;
    }

    public void setDef(int def) {
        this.def = def;
        super.setCe(this.atk + this.def);
    }

    public ArrayList<Adventurer> getHires() {
        return this.hires;
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
        if (bottles.containsKey(id)) {
            Bottle item = bottles.get(id);
            System.out.printf("%s ", item.getType());
            item.printStatus();
            bottles.remove(id);
        }
    }

    public void deleteEquipment(int id) {
        if (equipments.containsKey(id)) {
            Equipment item = equipments.get(id);
            System.out.printf("%s ", item.getType());
            item.printStatus();
            equipments.remove(id);
        }
    }

    public void carry(int id) {
        if (bottles.containsKey(id) && !bottles.get(id).getIsCarried()) {
            int maxBottles = super.getCe() / 5 + 1;
            int sameBottles = 0;
            for (Bottle obj : bottles.values()) {
                if (obj.getIsCarried() && obj.getName().equals(bottles.get(id).getName())) {
                    sameBottles++;
                    if (sameBottles >= maxBottles) {
                        return;
                    }
                }
            }
            bottles.get(id).carry();
        }
        if (equipments.containsKey(id) && !equipments.get(id).getIsCarried()) {
            for (Equipment obj : equipments.values()) {
                if (obj.getIsCarried() && obj.getName().equals(equipments.get(id).getName())) {
                    obj.setIsCarried(false);
                    equipments.get(id).carry();
                    return;
                }
            }
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
                        super.setCe(this.atk + this.def);
                        break;
                    case "DefBottle":
                        this.def += item.getCe() + item.getCapacity() / 100;
                        super.setCe(this.atk + this.def);
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
            if (value.getName().equals(name) && value.getIsCarried()) {
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

    public void normalFight(Equipment equ, ArrayList<Adventurer> advs) {
        if (equ != null && equ.getIsCarried() && this.atk + equ.getCe() > findMaxDef(advs)) {
            HashMap<Adventurer, Integer> needHelp = new HashMap<>();
            for (Adventurer obj : advs) {
                int currentHp = obj.getHp();
                switch (equ.getType()) {
                    case "Axe":
                        obj.setHp(obj.getHp() / 10);
                        break;
                    case "Sword":
                        obj.setHp(obj.getHp() - (equ.getCe() + this.atk - obj.getDef()));
                        break;
                    case "Blade":
                        obj.setHp(obj.getHp() - (equ.getCe() + this.atk));
                        break;
                    default:
                        break;
                }
                if (obj.getHp() <= currentHp / 2) {
                    needHelp.put(obj, 1);
                }
            }
            equ.changeDurability(-1);
            if (equ.getDurability() == 0) {
                equipments.remove(equ.getId());
            }
            for (Adventurer obj : advs) {
                if (needHelp.containsKey(obj)) {
                    obj.searchHelp();
                }
            }
            for (Adventurer obj : advs) {
                System.out.println(obj.getName() + ' ' + obj.getHp());
            }
        }
        else {
            System.out.printf("Adventurer %d defeated\n", this.getId());
        }
    }

    public void hire(Adventurer adv) {
        hires.add(adv);
        helpTime.put(adv.getId(), 0);
    }

    public void searchHelp() {
        hires.forEach(hire -> renderHelp(this));
        ArrayList<Adventurer> removes = new ArrayList<>();
        for (Adventurer hire : hires) {
            helpTime.merge(hire.getId(), 1, Integer::sum);
            if (helpTime.get(hire.getId()) > 3) {
                removes.add(hire);
            }
        }
        hires.removeAll(removes);
        removes.forEach(adv -> helpTime.remove(adv.getId()));
    }

    public void renderHelp(Adventurer adv) {
        ArrayList<Integer> removes = new ArrayList<>();
        for (Equipment equ : equipments.values()) {
            if (equ.getIsCarried()) {
                adv.equipment(equ.getId(), equ.getName(), equ.getDurability(), 
                    equ.getType(), equ.getCe());
                removes.add(equ.getId());
            }
        }
        for (Integer id : removes) {
            equipments.remove(id);
        }
    }

    public void chainFind(ArrayList<Adventurer> potantials, int n) {
        if (n != 5) {
            if (!potantials.contains(this)) {
                potantials.add(this);
            }
            for (Adventurer hire : hires) {
                hire.chainFind(potantials, n + 1);
            }
        }
    }

    public void chainFight(Equipment equ, ArrayList<Adventurer> potantials) {
        if (equ != null && equ.getIsCarried() && this.atk + equ.getCe() > findMaxDef(potantials)) {
            int loseHp = 0;
            for (Adventurer obj : potantials) {
                int currentHp = obj.getHp();
                switch (equ.getType()) {
                    case "Axe":
                        obj.setHp(obj.getHp() / 10);
                        break;
                    case "Sword":
                        obj.setHp(obj.getHp() - (equ.getCe() + this.atk - obj.getDef()));
                        break;
                    case "Blade":
                        obj.setHp(obj.getHp() - (equ.getCe() + this.atk));
                        break;
                    default:
                        break;
                }
                loseHp += (currentHp - obj.getHp());
            }
            equ.changeDurability(-1);
            if (equ.getDurability() == 0) {
                equipments.remove(equ.getId());
            }
            System.out.println(loseHp);
        }
        else {
            System.out.printf("Adventurer %d defeated\n", this.getId());
        }
    }

    public int getComprehensiveCE() {
        int comprehensiveCe = getCe();
        for (Equipment equ : equipments.values()) {
            if (equ.getIsCarried()) {
                comprehensiveCe += equ.getCe();
            }
        }
        for (Bottle bot : bottles.values()) {
            if (bot.getIsCarried()) {
                comprehensiveCe += bot.getCe();
            }
        }
        for (Adventurer hire : hires) {
            comprehensiveCe += hire.getCe();
        }
        return comprehensiveCe;
    }

    public void printStatus() {
        System.out.println(super.getName() + ' ' + this.hp + ' ' + this.atk + ' ' + this.def);
    }
}
