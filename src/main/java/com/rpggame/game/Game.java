package com.rpggame.game;

import com.rpggame.items.*;
import com.rpggame.map.Place;
import com.rpggame.monsters.Monster;
import com.rpggame.service.DataService;
import com.rpggame.service.ReactionService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Game {

    private UserStats userStats;

    private int positionX;
    private int positionY;

    private List<Dungeon> dungeons;
    private int currentDungeon;

    public void startRendering() {
        renderPlace();
    }

    private void renderPlace() {
        final Dungeon dungeon = this.dungeons.get(currentDungeon);
        final Place place = dungeon.getPlace(positionX, positionY);
        final Place placeOnFront = dungeon.getPlace(positionX, positionY - 1);
        final Place placeOnLeft = dungeon.getPlace(positionX - 1, positionY);
        final Place placeOnRight = dungeon.getPlace(positionX + 1, positionY);
        final Place placeBehind = dungeon.getPlace(positionX, positionY + 1);
        if (place.getMonster() == null) {
            System.out.println("");
            System.out.println("+++");
            System.out.println("");
            System.out.println(place.getCurrentDescription());
            System.out.println("(go) You look and see");
            System.out.println("");
            System.out.println("(w) North: ");
            System.out.println(placeOnFront.getLookDescription());
            System.out.println("(a) West: ");
            System.out.println(placeOnLeft.getLookDescription());
            System.out.println("(d) East: ");
            System.out.println(placeOnRight.getLookDescription());
            System.out.println("(s) South: ");
            System.out.println(placeBehind.getLookDescription());
            System.out.println("");
            System.out.println("===");
            System.out.println("");
            if (place.getItems() != null && !place.getItems().isEmpty()) {
                renderPlacePossibilities(place);
            }
            System.out.println("(save) game status");
            System.out.println("");
            renderStats();
            renderInventory();
        } else {
            battle(place, place.getMonster());
        }
        playerAction(place);
    }

    private void renderPlacePossibilities(Place place) {
        System.out.println("(get) You see on floor: ");
        int i = 1;
        for (Item item : place.getItems()) {
            System.out.println(i + ") " + item.getName());
            i++;
        }
        System.out.println("");
        System.out.println("===");
        System.out.println("");
    }

    private void renderInventory() {
        System.out.println("Your inventory: ");
        System.out.println("");
        System.out.println("Right hand: " + userStats.getRightHand().getName());
        System.out.println("Left hand: " + userStats.getLeftHand().getName());
        if (!this.userStats.getInventory().isEmpty()) {
            System.out.println("");
            System.out.println("(use) in backpack you have:");
            int i = 1;
            for (Item item : userStats.getInventory()) {
                System.out.println(i + ") " + item.getName());
                i++;
            }
        }
        System.out.println("");
        System.out.println("===");
        System.out.println("");
    }

    private void renderStats() {
        renderLevel();
        System.out.println("Energy: " + this.userStats.getHealth());
        System.out.println("Mana: " + this.userStats.getMana());
        System.out.println("");
        System.out.println("===");
        System.out.println("");
    }

    private void playerAction(final Place place) {
        System.out.println("Action:");
        Scanner scanner = new Scanner(System.in);
        String appliedCommand = scanner.nextLine();
        String[] commands = appliedCommand.split(" ");
        if (commands.length == 2) {
            String command = commands[0];
            String option = commands[1];
            if (command.equals("go") && place.getMonster() == null) {
                move(place, option);
            }
            if (command.equals("get") && place.getMonster() == null) {
                get(place, option);
            }
            if (command.equals("use")) {
                use(option);
            }
        } else if (commands.length == 1) {
            String command = commands[0];
            if (command.equals("attack")) {
                playerAttack(place, place.getMonster());
            }
            if (command.equals("save")) {
                try {
                    DataService.saveGameStatus(this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                rerenderContext();
            }
        }
    }

    private void move(final Place place, final String option) {
        final Dungeon dungeon = this.dungeons.get(currentDungeon);
        final Place placeOnFront = dungeon.getPlace(positionX, positionY - 1);
        final Place placeOnLeft = dungeon.getPlace(positionX - 1, positionY);
        final Place placeOnRight = dungeon.getPlace(positionX + 1, positionY);
        final Place placeBehind = dungeon.getPlace(positionX, positionY + 1);
        if (option.equals("w") && placeOnFront.canBeOccupied()) {
            positionY = positionY - 1;
        } else if (option.equals("a") && placeOnLeft.canBeOccupied()) {
            positionX = positionX - 1;
        } else if (option.equals("d") && placeOnRight.canBeOccupied()) {
            positionX = positionX + 1;
        } else if (option.equals("s") && placeBehind.canBeOccupied()) {
            positionY = positionY + 1;
        } else {
            System.out.println("");
            System.out.println("You can not move to the desired place");
            System.out.println("");
            playerAction(place);
        }
        rerenderContext();
    }

    private void get(final Place place, final String option) {
        int numberOption = Integer.parseInt(option) - 1;
        this.userStats.addToInventory(place.getItems().get(numberOption));
        this.dungeons.get(currentDungeon).removeItemFromPlace(positionX, positionY, numberOption);
        rerenderContext();
    }

    private void use(String option) {
        int numberOption = Integer.parseInt(option) - 1;
        Item item = this.userStats.getInventoryByNumber(numberOption);
        item.use(this.userStats, numberOption);
        rerenderContext();
    }

    private void battle(final Place place, final Monster monster) {
        System.out.println("");
        System.out.println("+++");
        System.out.println("");
        System.out.println("Battle with: " + monster.getName());
        System.out.println(monster.getDescription());
        System.out.println("(attack) enemy");
        System.out.println("");
        System.out.println("===");
        System.out.println("");
        renderStats();
        renderInventory();
        playerAction(place);
    }

    private void playerAttack(final Place place, final Monster monster) {
        if (ReactionService.playerHit(place, monster, this.userStats)) {
            rerenderContext();
        }
        if (ReactionService.monsterHit(monster, this.userStats)) {
            System.exit(0);
        }
        rerenderContext();
    }

    private boolean playerHit(final Place place, final Monster monster) {
        int hitPower = this.userStats.getRightHand().getPower() * this.userStats.getLevel();
        System.out.println("");
        if (monster.getHp() - hitPower <= 0) {
            this.userStats.updateExperience(monster.getExperience());
            System.out.println(monster.getMonsterIsDead());
            System.out.println("You get " + monster.getExperience() + " experiences point");
            place.setMonster(null);
            return true;
        } else {
            monster.substractFromHp(hitPower);
            System.out.println("You hit an opponent!");
            System.out.println(monster.getName() + " lose " + hitPower + " HP");
        }
        return false;
    }

    private boolean monsterHit(final Monster monster) {
        int hitPower = monster.getMaxPower();
        System.out.println("");
        System.out.println(monster.getAccuratelyHitDescription());
        System.out.println("You lost " + hitPower + " HP");
        if (this.userStats.getHealth() - hitPower <= 0) {
            System.out.println("");
            System.out.println("+++");
            System.out.println("");
            System.out.println("You are dead");
            return true;
        }
        this.userStats.substractFromHeatlh(hitPower);
        return false;
    }

    private void updateUserLevel() {
        if (this.userStats.updateLevel()) {
            System.out.println("+++ You reached new level +++");
        }
    }

    private void renderLevel() {
        System.out.println("|----------|");
        updateUserLevel();
        System.out.println("- Level: " + this.userStats.getLevel());
        System.out.println("- Experience: " + this.userStats.getExperience());
        System.out.println("- To next level: " + this.userStats.getToNextLevel());
        System.out.println("|----------|");
        System.out.println("");
    }

    private void rerenderContext() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        renderPlace();
    }

    public UserStats getUserStats() {
        return userStats;
    }

    public void setUserStats(UserStats userStats) {
        this.userStats = userStats;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public List<Dungeon> getDungeons() {
        return dungeons;
    }

    public void setDungeons(List<Dungeon> dungeons) {
        this.dungeons = dungeons;
    }

    public int getCurrentDungeon() {
        return currentDungeon;
    }

    public void setCurrentDungeon(int currentDungeon) {
        this.currentDungeon = currentDungeon;
    }
}
