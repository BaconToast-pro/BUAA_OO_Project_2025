public class Item extends Unit {
    private boolean isCarried = false;

    public Item(int id, String name, int ce) {
        super(id, name);
        super.setCe(ce);
    }

    @Override
    public int getCe() {
        return super.getCe();
    }

    public void carry() {
        this.isCarried = true;
    }

    public boolean getIsCarried() {
        return this.isCarried;
    }

    public void setIsCarried(boolean isCarried) {
        this.isCarried = isCarried;
    }

    @Override
    public String getName() {
        return super.getName();
    }

}
