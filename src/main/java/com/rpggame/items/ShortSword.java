package com.rpggame.items;

public class ShortSword extends Weapon {

    @Override
    public int getMinStrength() {
        return 0;
    }

    @Override
    public int getPower() {
        return 5;
    }

    @Override
    public String getDescription() {
        return "Short Sword";
    }

    @Override
    public String getName() {
        return "ShortSword";
    }
}
