public class Unit {
    private final int id;
    private final String name;
    private int ce;

    public Unit(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setCe(int ce) {
        this.ce = ce;
    }

    public int getCe() {
        return this.ce;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
