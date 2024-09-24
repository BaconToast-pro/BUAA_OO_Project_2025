public class Equipment extends Item {
    private int durability; 

    public Equipment(int id, String name, int ce, int durability) {
        super(id, name, ce);
        this.durability = durability;
    }

    public void changeDurability(int value) {
        this.durability += value;
    }

    public void printStatus() {
        System.out.println(super.getName() + ' ' + this.durability);
    }
}
