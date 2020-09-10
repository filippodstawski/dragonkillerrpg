package com.rpggame.monsters;

public class Rat extends Monster {

    @Override
    public int getInitialHp() {
        return 10;
    }

    @Override
    public int getMinPower() {
        return 5;
    }

    @Override
    public int getMaxPower() {
        return 5;
    }

    @Override
    public int getExperience() {
        return 25;
    }

    @Override
    public String getName() {
        return "Rat";
    }

    @Override
    public String getDescription() {
        return "You see a huge, aggressive rat in front of you";
    }
}
