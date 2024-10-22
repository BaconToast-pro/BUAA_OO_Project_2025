public class Stn implements Guard {
    @Override
    public boolean fight(Adventurer adv) {
        return adv.getComprehensiveCE() > 3000;
    }

    @Override
    public String getType() {
        return "Stn";
    }
}

