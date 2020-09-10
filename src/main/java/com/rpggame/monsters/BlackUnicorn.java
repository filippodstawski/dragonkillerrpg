package com.rpggame.monsters;

public class BlackUnicorn extends Monster {

    @Override
    public int getInitialHp() {
        return 50;
    }

    @Override
    public int getMinPower() {
        return 5;
    }

    @Override
    public int getMaxPower() {
        return 20;
    }

    @Override
    public int getExperience() {
        return 500;
    }

    @Override
    public String getName() {
        return "Black unicorn";
    }

    @Override
    public String getDescription() {
        return "In front of you, you see a black unicorn with fiery red eyes";
    }
}
