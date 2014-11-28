/*
 * This interface contains all the options the user has during a game of drugwars
 */
package interfaces;

import entity.Country;
import entity.Drug;
import entity.Game;
import entity.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author casper
 */
public interface GameAbilities {
    
    /**
     * Starts up the game by letting the user type in his name 
     * and giving a description of what he can do
     * @param player 
     */
    public void startNewGame(String playername);
    
    /**
     * Lets the user buy a specific drug, by adding the drug to the users 
     * inventory and redrawing the amount of credits
     * @param drug
     * @param amount 
     */
    public void buy(int index, int amount);
    
    /**
     * Lets the user sell a specific drug, by removing it from his inventory 
     * and adding the amount of credits it's sold for to the users credits
     * @param drug
     * @param amount 
     */
    public void sell(int index, int amount);
    
    /**
     * Lets the user travel to a given destination
     * @param country 
     */
    public void travelTo(int index);
    
    /**
     * Retursn the drugs the user can buy
     * @return 
     */
    public ArrayList<Drug> getAvailableDrugs();
    
    /**
     * Retursn a list of all the countries
     * @return 
     */
    public ArrayList<Country> getCountries();
    
    /**
     * Returns a specific country
     * @param name
     * @return 
     */
    public String getCurrentCountry();
    
    /**
     * Returns a list of all available drugs
     * @return 
     */
    public List getInventory();
    
    /**
     * Returns the given amount of credits the player 
     * has at the given time
     * @return 
     */
    public int getPlayerCredits();
    
    /**
     * Returns the number of drugs that is currently in the users inventory
     * @return 
     */
    public int getDrugAmount(int index);
    
    /**
     * Ends the games and saves the games played in an xml file
     */
    public void endGame();
    
    /**
     * 
     * @return 
     */
    public int getPlayerHealth();
    
    /**
     * Determines if the user gets caught
     * @return 
     */
    boolean caught();
   
    /**
     * Lets the user buy life
     */
    public void buyLife(int amount);
    
    /**
     * 
     * @return 
     */
    public String getPlayerName();
    
    /**
     * Returns the total highscore of the game
     * @return 
     */
    public HashMap<Integer, String> highscore();
}
