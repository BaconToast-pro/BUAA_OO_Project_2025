class ShdTreasure implements Treasure {
    @Override
    public void showInfo() {
        System.out.println("Cloak of Shadows");
    }

    @Override
    public void useBy(Adventurer adv) {
        adv.setDef(adv.getDef() + 40);
        for (Adventurer hire : adv.getHires()) {
            hire.setDef(hire.getDef() + 40);
        }
    }
}