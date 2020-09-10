package com.rpggame.game;

import com.rpggame.items.Item;
import com.rpggame.items.Shield;
import com.rpggame.items.Weapon;

import java.util.ArrayList;

public class UserStats {

    private int mana;
    private int health;

    private int level;
    private int experience;
    private int toNextLevel;

    private Shield leftHand;
    private Weapon rightHand;

    private ArrayList<Item> inventory;

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Shield getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(Shield leftHand) {
        this.leftHand = leftHand;
    }

    public Weapon getRightHand() {
        return rightHand;
    }

    public void setRightHand(Weapon rightHand) {
        this.rightHand = rightHand;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public void removeFromInventory(int position){
        this.inventory.remove(position);
    }

    public void fromInventoryToRightHand(int position){
        Weapon weapon = (Weapon) this.inventory.get(position);
        Weapon oldWeapon = this.rightHand;
        this.rightHand = weapon;
        this.inventory.remove(position);
        this.inventory.add(oldWeapon);
    }

    public void fromInventoryToLeftHand(int position){
        Shield shield = (Shield) this.inventory.get(position);
        Shield oldShield = this.leftHand;
        this.leftHand = shield;
        this.inventory.remove(position);
        this.inventory.add(oldShield);
    }

    public void addToInventory(Item item){
        this.inventory.add(item);
    }

    public Item getInventoryByNumber(int position){
        return this.inventory.get(position);
    }

    public void substractFromHeatlh(int hitPower){
        this.health = this.health - hitPower;
    }

    public void updateExperience(int experience){
        this.experience = this.experience + experience;
    }

    public boolean updateLevel() {
        if (this.experience > this.toNextLevel) {
            while (this.experience > this.toNextLevel) {
                this.level++;
                this.toNextLevel = this.toNextLevel * 2;
            }
            return true;
        }
        return false;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getToNextLevel() {
        return toNextLevel;
    }

    public void setToNextLevel(int toNextLevel) {
        this.toNextLevel = toNextLevel;
    }

}
