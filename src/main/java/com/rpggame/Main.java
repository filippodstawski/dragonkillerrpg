package com.rpggame;

import com.rpggame.game.Game;
import com.rpggame.game.Menu;

public class Main {

    public static void main(String[] args) {
        Game game = Menu.renderMenu();
        game.startRendering();
    }

}
