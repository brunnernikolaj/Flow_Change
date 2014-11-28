/*
 * This class implements the game abilities interface
 * 
 * The function of this class is to work like a Router, for the entire application
 * Therefore this class contains no logic, and only takes incomming calls
 * and redirects them to the correct classes
 *
 * This way we add an extra layer, to seperate the gui form the logic and entity
 *
 * OBS OBS OBS @TODO ---> The way we refer to the current game by finding it in 
 * the array all the time is a waste, should be changed
 */
package control;

import entity.Country;
import entity.Drug;
import entity.Game;
import entity.Player;
import interfaces.GameAbilities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import utilities.XMLHandler;

/**
 *
 * @author casper
 */
public class Controller implements GameAbilities {
    
    private static GameAbilities instance;
    private final int STARTING_CREDITS = 5000;
    private ArrayList<Game> games;
    private int currentGame;
    private XMLHandler xmlHandler;
    
    private final String XML_FILE = "games.xml";
    
    private Controller() {
        games = new ArrayList();
        xmlHandler = new XMLHandler(XML_FILE);
        games = xmlHandler.load();
        currentGame = -1;
    }
    
    /**
     * If mainCtrl hasnt been instantiated, do it and return the isntance of it
     * else just return the already created instance
     * @return 
     */
    public static GameAbilities getInstance() {
        if (instance == null) {
            instance = new Controller();
        } 
        return instance;
    }
    
    /**
     * Starts up a new game and adds it to the games array
     * @param playername 
     */
    @Override
    public void startNewGame(String playername) {
        // Start up the new game
        Player player = new Player(playername, STARTING_CREDITS);
        Game game = new Game(player);
        games.add(game);
        currentGame = games.size() - 1;
    }
    
    /**
     * Travel to a given destionation, based on an index
     * @param index 
     */
    @Override
    public void travelTo(int index) {
        games.get(currentGame).setCurrentCountry(index);
    }
    
    @Override
    public boolean caught() {
        return games.get(currentGame).gotCaught();
    }
    
    /**
     * Retursn the country that the player is currently in 
     * @return 
     */
    @Override
    public String getCurrentCountry() {
        return games.get(currentGame).getCurrentCountry().getName();
    }
    
    /**
     * Gets a list o countries
     * @return 
     */
    @Override
    public ArrayList<Country> getCountries() {
        return games.get(currentGame).getCountries();
    }
    
    /**
     * Buys an amount of drugs
     * @param index
     * @param amount 
     */
    @Override
    public void buy(int index, int amount) {
        games.get(currentGame).buyDrug(index, amount);
    }
    
    /**
     * Sells an amount of drugs
     * @param index
     * @param amount 
     */
    @Override
    public void sell(int index, int amount) {
        games.get(currentGame).sellDrug(index, amount);
    }

    /**
     * Gets a list of drugs
     * @return 
     */
    @Override
    public ArrayList<Drug> getAvailableDrugs() {
        return games.get(currentGame).getCurrentCountry().getDrugs();
    }

    /**
     * Gets the amount of drugs, that the user currently have in stock
     * by refering to the inventory index
     * @param index
     * @return 
     */
    @Override
    public int getDrugAmount(int index) {
        return (int) games.get(currentGame).getPlayer().getInventory().get(index).getAmount();
    }
    
    /**
     * Returns the entire inventory
     * @return 
     */
    @Override
    public ArrayList<Drug> getInventory() {
        return games.get(currentGame).getPlayer().getInventory();
    }

    /**
     * Returns the number of credits the user currently has
     * @return 
     */
    @Override
    public int getPlayerCredits() {
        return games.get(currentGame).getPlayer().getCredits();
    }
    
    /**
     * Returns the players name
     * @return 
     */
    @Override
    public String getPlayerName() {
        return games.get(currentGame).getPlayer().getPlayername();
    }
    
    /**
     * Returns the players health
     * @return 
     */
    @Override
    public int getPlayerHealth() {
        return games.get(currentGame).getPlayer().getHealth();
    }
    
    @Override
    public void buyLife(int amount) {
        games.get(currentGame).buyLife(amount);
    }
    
    /**
     * Sorts the current number of games, with game('s) own sort method and
     * returns the top 10 result as a hashmap with scores as key and playername
     * as value
     *
     * @param games
     * @return hashmap
     */
    @Override
    public HashMap<Integer, String> highscore() {
        
        HashMap<Integer, String> highscores = new HashMap();
        Collections.sort(games);

        for (int i = 0, gameSize = games.size(); i < gameSize && i < 10; i++) {
            Integer credits = games.get(i).getPlayer().getCredits();
            String playername = games.get(i).getPlayer().getPlayername(); 

            highscores.put(credits, playername);
        }
        
        return highscores;
    }
    
    /**
     * Ends a game and adds it to the xml file
     */
    @Override() 
    public void endGame() {
        xmlHandler.save(games);
    }
    
    /**
     * Methods purely for testing purposes
     * @return 
     */
    public ArrayList<Game> getGames() {
        return games;
    } 
    
    /**
     * Methods purely for testing purposes
     */
    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }
}   
