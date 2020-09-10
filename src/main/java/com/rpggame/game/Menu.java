package com.rpggame.game;

import com.rpggame.service.DataService;

import java.util.Scanner;

public class Menu {

    public static Game renderMenu(){
        System.out.println("+++ Dragon Killer +++");
        System.out.println("");
        System.out.println("1) new game");
        System.out.println("2) load game");
        System.out.println("");
        System.out.println("+++++++++++++++++++++");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        if (command.equals("1")) {
            return DataService.generateNewGame();
        } else if (command.equals("2")) {
            return DataService.loadGameStatus();
        }
        System.out.println("");
        System.out.println("No way");
        System.out.println("");
        renderMenu();
        return null;
    }

}
