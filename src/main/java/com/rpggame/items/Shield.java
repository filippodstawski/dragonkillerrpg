package com.rpggame.items;

import com.rpggame.game.UserStats;

public abstract class Shield extends Item {

    public abstract int getMinStrength();
    public abstract int getDefensePoints();

    @Override
    public void use(final UserStats userStats, int position){
        userStats.fromInventoryToLeftHand(position);
    }

}
