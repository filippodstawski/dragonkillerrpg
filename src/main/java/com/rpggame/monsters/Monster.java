package com.rpggame.monsters;

public abstract class Monster {

    private int hp;

    public abstract int getInitialHp();
    public abstract int getMinPower();
    public abstract int getMaxPower();
    public abstract int getExperience();
    public abstract String getName();
    public abstract String getDescription();

    Monster(){
        this.hp = getInitialHp();
    }

    public String getAccuratelyHitDescription(){
        return "The enemy hit you!";
    }

    public String getNotAccuratelyHitDescription(){
        return "The enemy missed!";
    }

    public String getMonsterIsDead(){
        return "The enemy is dead!";
    }

    public void substractFromHp(int hitPower){
        this.hp = this.hp - hitPower;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
