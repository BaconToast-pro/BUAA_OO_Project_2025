class FlmTreasure implements Treasure {
    @Override
    public void showInfo() {
        System.out.println("Flamebrand Sword");
    }

    @Override
    public void useBy(Adventurer adv) {
        adv.setAtk(adv.getAtk() + 40);
        for (Adventurer hire : adv.getHires()) {
            hire.setAtk(hire.getAtk() + 40);
        }
    }
}