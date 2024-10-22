class WndTreasure implements Treasure {
    @Override
    public void showInfo() {
        System.out.println("Windrunner Boots");
    }

    @Override
    public void useBy(Adventurer adv) {
        adv.setDef(adv.getDef() + 30);
        for (Adventurer hire : adv.getHires()) {
            hire.setDef(hire.getDef() + 30);
        }
    }
}