package com.rpggame.map;

public class Flooring extends Place {

    @Override
    public String getLookDescription() {
        return "You see empty space";
    }

    @Override
    public String getCurrentDescription() {
        return "You are standing on an unfortunate floor";
    }

    @Override
    public boolean canBeOccupied() {
        return true;
    }

}
