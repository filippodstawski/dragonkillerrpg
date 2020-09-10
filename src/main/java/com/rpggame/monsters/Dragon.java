package com.rpggame.monsters;

public class Dragon extends Monster {

    @Override
    public int getInitialHp() {
        return 250;
    }

    @Override
    public int getMinPower() {
        return 5;
    }

    @Override
    public int getMaxPower() {
        return 40;
    }

    @Override
    public int getExperience() {
        return 10000;
    }

    @Override
    public String getName() {
        return "Dragon";
    }

    @Override
    public String getDescription() {
        return "You see a fat red dragon in front of you";
    }
}
