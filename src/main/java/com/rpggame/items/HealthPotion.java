package com.rpggame.items;

import com.rpggame.game.UserStats;

public class HealthPotion extends Item {

    @Override
    public String getDescription() {
        return "Health Potion";
    }

    @Override
    public String getName() {
        return "Health Potion";
    }

    @Override
    public void use(final UserStats userStats, int position) {
        userStats.setHealth(userStats.getHealth() + 10);
        userStats.removeFromInventory(position);
    }
}
