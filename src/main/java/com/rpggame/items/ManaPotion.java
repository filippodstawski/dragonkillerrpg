package com.rpggame.items;

import com.rpggame.game.UserStats;

public class ManaPotion extends Potion {

    @Override
    public String getDescription() {
        return "Mana Potion";
    }

    @Override
    public String getName() {
        return "Mana Potion";
    }

    @Override
    public void use(final UserStats userStats, int position) {
        userStats.setMana(userStats.getMana() + 10);
        userStats.removeFromInventory(position);
    }
}
