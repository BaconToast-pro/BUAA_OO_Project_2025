public class Bottle extends Item {
    private final int capacity;
    private final String type;
    private boolean isEmpty = false;

    public Bottle(int id, String name, int capacity, String type, int ce) {
        super(id, name, ce);
        this.capacity = capacity;
        this.type = type;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public boolean getIsEmpty() {
        return this.isEmpty;
    }

    public String getType() {
        return this.type;
    }

    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getCe() {
        return super.getCe();
    }

    public void printStatus() {
        System.out.println(this.type + ' ' + super.getName() + ' ' + this.capacity);
    }

    @Override
    public boolean getIsCarried() {
        return super.getIsCarried();
    }
}
