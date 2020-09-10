package com.rpggame.items;

import com.rpggame.game.UserStats;

public abstract class Weapon extends Item {

    public abstract int getMinStrength();
    public abstract int getPower();

    @Override
    public void use(final UserStats userStats, int position){
        userStats.fromInventoryToRightHand(position);
    }

}
