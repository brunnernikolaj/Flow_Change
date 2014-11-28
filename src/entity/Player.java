/*
 * This class contains player data
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author casper
 */
public class Player {
    
    private String playername;
    private int credits, health, caputureChance = 5;
    private ArrayList<Drug> inventory;
    private ArrayList<Item> items;
    
    public Player(String playername, int startingCredits) {
        this.inventory = new ArrayList();
        this.items = new ArrayList();
        this.playername = playername;
        this.credits = startingCredits;
        this.health = 100;
    }
    
    /**
     * Adds a item to the inventory
     * @param item 
     */
    public void addItemToInventory(Item item) {
        if (item instanceof CaptureReducer) {
            CaptureReducer reducer = (CaptureReducer)item;
            caputureChance -= reducer.getReduceValue();
        } 
        items.add(item);
    }
    
    /**
     * Adds a drug to the inventory
     * @param drug 
     */
    public void addDrugsToInventory(Drug drug, int amount) {
        
        if (!inventory.contains(drug)) {
            inventory.add(drug);
        } 
        inventory.get(inventory.indexOf(drug)).addDrugs(amount);
    }
    
    /**
     * Removes a drug from the inventory
     * @param drug 
     */
    public void removeDrugsFromInventory(Drug drug, int amount) {
        inventory.get(inventory.indexOf(drug)).removeDrugs(amount);
    }
    
    /**
     * Redraws credits from the players credits
     * @param amount 
     */
    public void redrawCredits(int amount) {
        this.credits -= amount; 
    }
    
    /**
     * Adds credits to the players credits
     * @param amount 
     */
    public void addCredits(int amount) {
        this.credits += amount;
    }
    
    public String getPlayername() {
        return playername;
    }

    public ArrayList<Drug> getInventory() {
        return inventory;
    }

    public int getCredits() {
        return credits;
    }
    
    public int getCaputureChance() {
        return caputureChance;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
