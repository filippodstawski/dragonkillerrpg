package com.rpggame.map;

public class Brick extends Place {

    @Override
    public String getLookDescription() {
        return "You see the wall";
    }

    @Override
    public String getCurrentDescription() {
        return null;
    }

    @Override
    public boolean canBeOccupied() {
        return false;
    }

}
