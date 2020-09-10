package com.rpggame.service;

import com.rpggame.game.UserStats;
import com.rpggame.map.Place;
import com.rpggame.monsters.Monster;

public class ReactionService {

    public static boolean playerHit(final Place place, final Monster monster, final UserStats userStats) {
        int hitPower = userStats.getRightHand().getPower() * userStats.getLevel();
        System.out.println("");
        if (monster.getHp() - hitPower <= 0) {
            userStats.updateExperience(monster.getExperience());
            System.out.println(monster.getMonsterIsDead());
            System.out.println("You have earned " + monster.getExperience() + " experience points");
            place.setMonster(null);
            return true;
        } else {
            monster.substractFromHp(hitPower);
            System.out.println("You hit your enemy!");
            System.out.println(monster.getName() + " loss " + hitPower + " live points");
        }
        return false;
    }

    public static boolean monsterHit(final Monster monster, final UserStats userStats) {
        int hitPower = monster.getMaxPower();
        System.out.println("");
        System.out.println(monster.getAccuratelyHitDescription());
        System.out.println("You loss " + hitPower + " live points");
        if (userStats.getHealth() - hitPower <= 0) {
            System.out.println("");
            System.out.println("+++");
            System.out.println("");
            System.out.println("You died!");
            return true;
        }
        userStats.substractFromHeatlh(hitPower);
        return false;
    }

}
