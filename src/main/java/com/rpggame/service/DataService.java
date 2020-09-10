package com.rpggame.service;

import com.rpggame.game.Dungeon;
import com.rpggame.game.Game;
import com.rpggame.game.UserStats;
import com.rpggame.items.*;
import com.rpggame.map.*;
import com.rpggame.monsters.*;
import com.rpggame.map.Place;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataService {

    private final static String CLEAR_MAP_FILE_PATH = "map.rpg";
    private final static String SAVE_FILE_PATH = "save.rpg";
    private final static String MAZE_FILE_PATH = "maze.rpg";

    public static void saveGameStatus(Game game) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource(SAVE_FILE_PATH).getFile());
        if (file.isFile()) {
            file.delete();
        }
        file.createNewFile();
        FileWriter fw = new FileWriter(file, true);
        fw.write("[Mana]" + game.getUserStats().getMana() + "\n");
        fw.write("[Health]" + game.getUserStats().getHealth() + "\n");
        fw.write("[Level]" + game.getUserStats().getLevel() + "\n");
        fw.write("[ToNextLevel]" + game.getUserStats().getToNextLevel() + "\n");
        fw.write("[LeftHand]" + game.getUserStats().getLeftHand().getClass().getName() + "\n");
        fw.write("[RightHand]" + game.getUserStats().getRightHand().getClass().getName() + "\n");
        for (Item item : game.getUserStats().getInventory()) {
            fw.write("[InventoryElement]" + item.getClass().getName() + "\n");
        }
        fw.write("[PositionX]" + game.getPositionX() + "\n");
        fw.write("[PositionY]" + game.getPositionY() + "\n");
        fw.write("[Width]" + game.getDungeons().get(0).getWidth() + "\n");
        fw.write("[Height]" + game.getDungeons().get(0).getHeight() + "\n");
        int width = game.getDungeons().get(0).getWidth();
        int height = game.getDungeons().get(0).getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Place place = game.getDungeons().get(0).getPlace(x, y);
                System.out.println(x + " - " + y);
                fw.write("[Place][" + x + "][" + y + "][Type]" + place.getClass().getName() + "\n");
                if (place.getItems() != null) {
                    for (Item item : place.getItems()) {
                        fw.write("[Place][" + x + "][" + y + "][Item]" + item.getClass().getName() + "\n");
                    }
                }
                if (place.getMonster() != null) {
                    fw.write("[Place][" + x + "][" + y + "][Monster]" + place.getMonster().getClass().getName() + "\n");
                }
            }
        }
        fw.close();
    }

    public static Game loadGameStatus() {
        try {
            return loadDataFromFile(SAVE_FILE_PATH);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException ex) {
            Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Game generateNewGame() {
        try {
            return loadDataFromFile(CLEAR_MAP_FILE_PATH);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException ex) {
            Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static Game loadDataFromFile(String filePath) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        BufferedReader reader;
        Game game = new Game();
        UserStats userStats = new UserStats();
        ArrayList<Item> inventory = new ArrayList<>();
        Dungeon dungeon = new Dungeon();
        Place[][] places = null;
        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            reader = new BufferedReader(new FileReader(classLoader.getResource(filePath).getFile()));
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                if (line.contains("[Mana]")) {
                    int mana = Integer.parseInt(line.replace("[Mana]", ""));
                    userStats.setMana(mana);
                }
                if (line.contains("[Health]")) {
                    int health = Integer.parseInt(line.replace("[Health]", ""));
                    userStats.setHealth(health);
                }
                if (line.contains("[Level]")) {
                    int level = Integer.parseInt(line.replace("[Level]", ""));
                    userStats.setLevel(level);
                }
                if (line.contains("[ToNextLevel]")) {
                    int toNextLevel = Integer.parseInt(line.replace("[ToNextLevel]", ""));
                    userStats.setToNextLevel(toNextLevel);
                }
                if (line.contains("[LeftHand]")) {
                    String itemClass = line.replace("[LeftHand]", "");
                    Shield shield = (Shield) Class.forName(itemClass).newInstance();
                    userStats.setLeftHand(shield);
                }
                if (line.contains("[RightHand]")) {
                    String itemClass = line.replace("[RightHand]", "");
                    Weapon weapon = (Weapon) Class.forName(itemClass).newInstance();
                    userStats.setRightHand(weapon);
                }
                if (line.contains("[InventoryElement]")) {
                    String itemClass = line.replace("[InventoryElement]", "");
                    Item item = (Item) Class.forName(itemClass).newInstance();
                    inventory.add(item);
                }
                if (line.contains("[PositionX]")) {
                    int positionX = Integer.parseInt(line.replace("[PositionX]", ""));
                    game.setPositionX(positionX);
                }
                if (line.contains("[PositionY]")) {
                    int positionY = Integer.parseInt(line.replace("[PositionY]", ""));
                    game.setPositionY(positionY);
                }
                if (line.contains("[Width]")) {
                    int width = Integer.parseInt(line.replace("[Width]", ""));
                    dungeon.setWidth(width);
                }
                if (line.contains("[Height]")) {
                    int heigth = Integer.parseInt(line.replace("[Height]", ""));
                    dungeon.setHeight(heigth);
                }
                if (dungeon.getHeight() != null && dungeon.getWidth() != null) {
                    if (places == null) {
                        places = new Place[dungeon.getWidth()][dungeon.getHeight()];
                    }
                    if (line.contains("[Place]")) {
                        int x = Character.getNumericValue(line.charAt(8));
                        int y = Character.getNumericValue(line.charAt(11));
                        if (line.contains("[Type]")) {
                            String itemClass = line.replace("[Place][" + x + "][" + y + "][Type]", "");
                            Place place = (Place) Class.forName(itemClass).newInstance();
                            place.setItems(new ArrayList<Item>());
                            places[x][y] = place;
                        }
                        if (line.contains("[Item]")) {
                            String itemClass = line.replace("[Place][" + x + "][" + y + "][Item]", "");
                            Item item = (Item) Class.forName(itemClass).newInstance();
                            Place place = places[x][y];
                            place.addItem(item);
                            places[x][y] = place;
                        }
                        if (line.contains("[Monster]")) {
                            String itemClass = line.replace("[Place][" + x + "][" + y + "][Monster]", "");
                            Monster monster = (Monster) Class.forName(itemClass).newInstance();
                            Place place = places[x][y];
                            place.setMonster(monster);
                            places[x][y] = place;
                        }
                    }
                }

            }
            reader.close();
            userStats.setInventory(inventory);
            game.setUserStats(userStats);
            dungeon.setPlaces(places);
            game.setDungeons(Arrays.asList(dungeon));
        } catch (IOException e) {
            Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, e);
        }
        return game;
    }

    private static Place[][] loadPlacesFromMaze() throws IOException {

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource(MAZE_FILE_PATH).getFile());

        BufferedReader read = new BufferedReader(new FileReader(file));
        String rea = read.readLine();
        String[] split = rea.split(" ");
        int width =  Integer.valueOf(split[0]);
        int height = Integer.valueOf(split[1]);

        String readline;
        int num = 0;
        char[][] maze = new char[width][height];
        while((readline = read.readLine()) != null){
            char[] ch = readline.toCharArray();
            for(int i = 0;i < ch.length;i++){
                maze[i][num] = ch[i];
            }
            num++;
        }

        Place[][] places = new Place[width][height];

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                String s = String.valueOf(maze[i][j]);
                if(s.equals("A")){
                    Place place = new Brick();
                    places[i][j] = place;
                }
                if(s.equals("P")){
                    Place place = new Flooring();
                    places[i][j] = place;
                }
                if(s.equals("S")){
                    Place place = new Flooring();
                    places[i][j] = place;
                }
                if(s.equals("1")){
                    Place place = new Flooring();
                    Rat rat = new Rat();
                    place.setMonster(rat);
                    places[i][j] = place;
                }
                if(s.equals("2")){
                    Place place = new Flooring();
                    Orc orc = new Orc();
                    place.setMonster(orc);
                    places[i][j] = place;
                }
                if(s.equals("3")){
                    Place place = new Flooring();
                    BlackUnicorn blackUnicorn = new BlackUnicorn();
                    place.setMonster(blackUnicorn);
                    places[i][j] = place;
                }
                if(s.equals("4")){
                    Place place = new Flooring();
                    Dragon dragon = new Dragon();
                    place.setMonster(dragon);
                    places[i][j] = place;
                }
                if(s.equals("!")){
                    Place place = new Flooring();
                    HealthPotion healthPotion = new HealthPotion();
                    place.setItems(new ArrayList<Item>());
                    place.addItem(healthPotion);
                    places[i][j] = place;
                }
                if(s.equals("%")){
                    Place place = new Flooring();
                    HealthPotion healthPotion = new HealthPotion();
                    place.setItems(new ArrayList<Item>());
                    place.addItem(healthPotion);
                    places[i][j] = place;
                }
                if(s.equals("@")){
                    Place place = new Flooring();
                    ManaPotion manaPotion = new ManaPotion();
                    place.setItems(new ArrayList<Item>());
                    place.addItem(manaPotion);
                    places[i][j] = place;
                }
                if(s.equals("#")){
                    Place place = new Flooring();
                    ShortSword shortSword = new ShortSword();
                    place.setItems(new ArrayList<Item>());
                    place.addItem(shortSword);
                    places[i][j] = place;
                }
                if(s.equals("$")){
                    Place place = new Flooring();
                    LongSword longSword = new LongSword();
                    place.setItems(new ArrayList<Item>());
                    place.addItem(longSword);
                    places[i][j] = place;
                }
                if(s.equals("^")){
                    Place place = new Flooring();
                    GuardianShield guardianShield = new GuardianShield();
                    place.setItems(new ArrayList<Item>());
                    place.addItem(guardianShield);
                    places[i][j] = place;
                }
                if(s.equals("&")){
                    Place place = new Flooring();
                    CrystalSword crystalSword = new CrystalSword();
                    place.setItems(new ArrayList<Item>());
                    place.addItem(crystalSword);
                    places[i][j] = place;
                }
                if(s.equals("*")){
                    Place place = new Flooring();
                    HammerOfGods hammerOfGods = new HammerOfGods();
                    place.setItems(new ArrayList<Item>());
                    place.addItem(hammerOfGods);
                    places[i][j] = place;
                }
            }
        }
        return places;
    }

}