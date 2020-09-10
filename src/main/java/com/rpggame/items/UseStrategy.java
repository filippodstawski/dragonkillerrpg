package com.rpggame.items;

import com.rpggame.game.UserStats;

public interface UseStrategy {
    void use(final UserStats userStats, int position);
}
