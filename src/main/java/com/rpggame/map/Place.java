package com.rpggame.map;

import com.rpggame.items.Item;
import com.rpggame.monsters.Monster;

import java.util.List;

public abstract class Place {

    public abstract String getLookDescription();
    public abstract String getCurrentDescription();
    public abstract boolean canBeOccupied();

    private Monster monster;
    private List<Item> items;

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void removeItem(int option){
        items.remove(option);
    }

    public void addItem(Item item){
        items.add(item);
    }

}
