package com.rpggame.items;

public class GuardianShield extends Shield {
    @Override
    public int getMinStrength() {
        return 20;
    }

    @Override
    public int getDefensePoints() {
        return 25;
    }

    @Override
    public String getDescription() {
        return "Guardian Shield";
    }

    @Override
    public String getName() {
        return "Guardian Shield";
    }
}
