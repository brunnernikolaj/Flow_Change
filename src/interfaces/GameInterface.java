/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.Country;
import entity.Drug;
import entity.Game;
import entity.Player;
import java.util.ArrayList;

/**
 *
 * @author casper
 */
public interface GameInterface extends Comparable<Game> {

    /**
     * Buys an amount of drugs, by setting the players amount of credits
     * and adding the amount of drugs to his inventory
     * @param index
     * @param amount
     */
    void buyDrug(int index, int amount);
    
    /**
     * Buys a item, by setting the players amount of credits
     * and adding the item to his inventory
     * @param index
     */
    void buyItem(int index);

    /**
     * Compares scores with other game turns
     * @param other
     * @return
     */
    int compareTo(Game other);
    
    /**
     * Let's the user buy an amount of life units
     * @param amount 
     */
    void buyLife(int amount);
    
    /**
     * Return all countries that the game has
     * @return
     */
    ArrayList<Country> getCountries();

    /**
     * Returns the country the user is currently in
     * @return
     */
    Country getCurrentCountry();

    /**
     * Return a list of all available drugs
     * @return
     */
    ArrayList<Drug> getDrugs();
    
    /**
     * Return a list of all available items
     * @return
     */
    ArrayList<Item> getItems();
    
    /**
     * Return the current playing player
     * @return
     */
    Player getPlayer();
    
    /**
     * Determines if the player gets caught in customs
     * @return 
     */
    boolean gotCaught();
    
    /**
     * Sell an amount of drugs, by setting the players amount of credits
     * and removing the amount of drugs that has been sold
     * @param index
     * @param amount
     */
    void sellDrug(int index, int amount);

    /**
     * Sets the country the user is currently in
     * @param index
     */
    void setCurrentCountry(int index);
    
}
