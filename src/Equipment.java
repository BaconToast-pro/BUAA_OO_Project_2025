public class Equipment extends Item {
    private int durability; 
    private final String type;

    public Equipment(int id, String name, int durability, String type, int ce) {
        super(id, name, ce);
        this.durability = durability;
        this.type = type;
    }

    public void changeDurability(int value) {
        this.durability += value;
    }

    public void printStatus() {
        System.out.println(super.getName() + ' ' + this.durability);
    }

    public int getDurability() {
        return this.durability;
    }

    @Override
    public boolean getIsCarried() {
        return super.getIsCarried();
    }

    public String getType() {
        return this.type;
    }
}
