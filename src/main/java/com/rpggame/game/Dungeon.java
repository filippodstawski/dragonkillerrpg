package com.rpggame.game;

import com.rpggame.items.Item;
import com.rpggame.map.Place;

public class Dungeon {

    private Place[][] places;
    private Integer width;
    private Integer height;

    public Place getPlace(int x, int y){
        return places[x][y];
    }

    public void setPlaces(Place[][] places) {
        this.places = places;
    }

    public void removeItemFromPlace(int x, int y, int option){
        places[x][y].removeItem(option);
    }

    public void addItemToPlace(int x, int y, Item item){
        places[x][y].addItem(item);
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}
