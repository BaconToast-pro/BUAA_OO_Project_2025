class FrzTreasure implements Treasure {
    @Override
    public void showInfo() {
        System.out.println("Frostbite Staff");
    }

    @Override
    public void useBy(Adventurer adv) {
        adv.setAtk(adv.getAtk() + 50);
        for (Adventurer hire : adv.getHires()) {
            hire.setAtk(hire.getAtk() + 50);
        }
    }
}