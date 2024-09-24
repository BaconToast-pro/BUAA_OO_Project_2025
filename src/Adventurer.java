import java.util.ArrayList;

public class Adventurer extends Unit {
    private int hp = 500;
    private int atk = 1;
    private int def = 0;
    private final ArrayList<Bottle> bottles = new ArrayList<>();
    private final ArrayList<Equipment> equipments = new ArrayList<>();

    public Adventurer(int id, String name) {
        super(id, name);
        super.setCe(this.atk + this.def);
    }

    public void bottle(int id, String name, int ce, int capacity, String type) {
        if ("HpBottle".equals(type)) {
            Bottle bottle = new HpBottle(id, name, ce, capacity, type);
            bottles.add(bottle);
        }
        else if ("AtkBottle".equals(type)) {
            Bottle bottle = new AtkBottle(id, name, ce, capacity, type);
            bottles.add(bottle);
        }
        else if ("DefBottle".equals(type)) {
            Bottle bottle = new DefBottle(id, name, ce, capacity, type);
            bottles.add(bottle);
        }
    }

    public void equipment(int id, String name, int ce, int durability) {
        Equipment equipment = new Equipment(id, name, ce, durability);
        equipments.add(equipment);
    }

    public void addDurability(int equId) {
        for (Equipment item : equipments) {
            if (item.getId() == equId) {
                item.changeDurability(1);
                item.printStatus();
                break;
            }
        }
    }

    public void deleteItem(int id) {
        deleteBottle(id);
        deleteEquipment(id);
    }

    public void deleteBottle(int id) {
        for (Bottle item : bottles) {
            if (item.getId() == id) {
                bottles.remove(item);
                item.printStatus();
                break;
            }
        }
    }

    public void deleteEquipment(int id) {
        for (Equipment item : equipments) {
            if (item.getId() == id) {
                equipments.remove(item);
                item.printStatus();
                break;
            }
        }
    }

    public void carry(int id) {
        for (Bottle item : bottles) {
            if (item.getId() == id) {
                item.carry();
                break;
            }
        }
        for (Equipment item : equipments) {
            if (item.getId() == id) {
                item.carry();
                break;
            }
        }
    }

    public boolean use(int id) {
        for (Bottle item : bottles) {
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
                    bottles.remove(item);
                }
                return true;
            }
        }
        return false;
    }

    public String findBottleName(int id) {
        for (Bottle item : bottles) {
            if (item.getId() == id) {
                return item.getName();
            }
        }
        return null;
    }

    public void printStatus() {
        System.out.println(super.getName() + ' ' + this.hp + ' ' + this.atk + ' ' + this.def);
    }
}
