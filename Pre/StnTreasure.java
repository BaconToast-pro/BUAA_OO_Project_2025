class StnTreasure implements Treasure {
    @Override
    public void showInfo() {
        System.out.println("Stoneheart Amulet");
    }

    @Override
    public void useBy(Adventurer adv) {
        adv.setDef(adv.getDef() + 40);
        for (Adventurer hire : adv.getHires()) {
            hire.setDef(hire.getDef() + 40);
        }
    }
}