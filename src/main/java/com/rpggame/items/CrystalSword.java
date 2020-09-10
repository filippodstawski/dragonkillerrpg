package com.rpggame.items;

public class CrystalSword extends Weapon {

    @Override
    public int getMinStrength() {
        return 0;
    }

    @Override
    public int getPower() {
        return 35;
    }

    @Override
    public String getDescription() {
        return "Crystal sword";
    }

    @Override
    public String getName() {
        return "Crystal sword";
    }
}
