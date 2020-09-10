package com.rpggame.monsters;

public class Orc extends Monster {

    @Override
    public int getInitialHp() {
        return 20;
    }

    @Override
    public int getMinPower() {
        return 5;
    }

    @Override
    public int getMaxPower() {
        return 10;
    }

    @Override
    public int getExperience() {
        return 100;
    }

    @Override
    public String getName() {
        return "Orc";
    }

    @Override
    public String getDescription() {
        return "You see an unpleasant green orc in front of you";
    }
}
