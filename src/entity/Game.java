/*
 * This class holds logic for a specific game
 * 
 * The idea is that each time a new game starts, a new game object is 
 * instantiated
 *
 * This way, we can easely save more data later 
 */
package entity;

import interfaces.GameInterface;
import java.util.ArrayList;
import utilities.*;
import java.util.Random;

/**
 *
 * @author casper
 */
public class Game implements GameInterface {
    
    private Player player;
    private Country currentCountry;
    private ArrayList<Country> countries;
    private ArrayList<Item> purchasableItems;
    private DrugCalculator drugCalculator;
    private int pricePerLifeUnit = 100;
    private Random random;
    
    public Game(Player player) {
        this.player = player;
        countries = new ArrayList();
        purchasableItems = new ArrayList();
        random = new Random();
        DrugCalculator drugCalculator = new DrugCalculator();
        initCountries();
        initItems();
        currentCountry = countries.get(0);
    }
    
    
    /**
     * Sell an amount of drugs, by setting the players amount of credits
     * and removing the amount of drugs that has been sold
     * @param index
     * @param amount 
     */
    @Override
    public void sellDrug(int index, int amount) {
        ArrayList<Drug> drugs = currentCountry.getDrugs();
        int creaditsEarned = drugs.get(index).getActualPrice() * amount;
        
        player.removeDrugsFromInventory(drugs.get(index), amount);
        player.addCredits(creaditsEarned);
    }
    
    /**
     * Buys an amount of drugs, by setting the players amount of credits
     * and adding the amount of drugs to his inventory
     * @param index
     * @param amount 
     */
    @Override
    public void buyDrug(int index, int amount) {
        ArrayList<Drug> drugs = currentCountry.getDrugs();
        int creaditsUsed = drugs.get(index).getActualPrice() * amount;

        player.addDrugsToInventory(drugs.get(index), amount);
        player.redrawCredits(creaditsUsed);
    }
    
    @Override
    public void buyItem(int index) {
        int price = items.get(index).getPrice();
        
        player.addItemToInventory(purchasableItems.get(index));
        player.redrawCredits(price);
        
        purchasableItems.remove(index);
    }
    
     @Override
    public void buyLife(int amount) {
        
        player.redrawCredits(amount * pricePerLifeUnit);
        
        if (player.getHealth() + amount > 100) {
            player.setHealth(100);
        } else {
            player.setHealth(player.getHealth() + amount);
        }
    }
    
    /**
     * Returns the country the user is currently in
     * @return 
     */
    @Override
    public Country getCurrentCountry() {
        return currentCountry;
    }

    /**
     * Sets the country the user is currently in / travles to
     * and randomizes the base price and availability
     * @param index 
     */
    @Override
    public void setCurrentCountry(int index) {
        this.currentCountry = countries.get(index);
  
        for (Drug drug : currentCountry.getDrugs()) {
            int price = (int)drugCalculator.randomizeBasePrice(drug.getBasePrice());
            drug.setActualPrice(price);
            
            int availability = (int)drugCalculator.randomizeBaseAvailability(drug.getBaseAmount());
            drug.setAmount(availability);
        }
    }
    
    @Override
    public boolean gotCaught() {
        
        if (random.nextInt(100) < player.getCaptureChance()) {
            player.setHealth(player.getHealth() - 10);
            
            for (Drug drug : player.getInventory()) {
                drug.setAmount(drug.getAmount() / 2);
            }
            
            return true;
        }
        return false;
    }
    
    /**
     * Return all countries that the game has 
     * @return 
     */
    @Override
    public ArrayList<Country> getCountries() {
        return countries;
    }
    
    /**
     * Return the current playing player 
     * @return 
     */
    @Override
    public Player getPlayer() {
        return this.player;
    }
    
    /**
     * Return a list of all available drugs
     * @return 
     */
    @Override
    public ArrayList<Drug> getDrugs() {
        return this.currentCountry.getDrugs();
    }
    
    @Override
    public ArrayList<Item> getItems() {
        return this.purchasableItems;
    }
    
    /**
     * Boot up the game with these countries
     */
    private void initCountries() {
        List<Drug> drugs = new ArrayList();
        drugs.add(new Drug("Cocaine", 1200, 30));
        drugs.add(new Drug("Heroin", 1600, 15));
        drugs.add(new Drug("Amphetamine", 200, 50));
        drugs.add(new Drug("Acid", 550, 33));
        drugs.add(new Drug("Angel dust", 400, 60));
        drugs.add(new Drug("Crystal meth", 800, 38));
        drugs.add(new Drug("Hash", 180, 100));
        drugs.add(new Drug("Weed", 150, 115));
        drugs.add(new Drug("Mushrooms", 120, 95));
        
        countries.add(new Country("Denmark", drugs));
        countries.add(new Country("Columbia", drugs));
        countries.add(new Country("Germany", drugs));
        countries.add(new Country("USA", drugs));
        countries.add(new Country("France", drugs));
        countries.add(new Country("Afghanistan", drugs));
    }

    private void initItems() {
        purchasableItems.add(new CaptureReducer("Gun",5000,1));
        purchasableItems.add(new CaptureReducer("High friends",10000,2));
    }

    /**
     * Compares scores with other game turns
     * @param other
     * @return 
     */
    @Override
    public int compareTo(Game other) {
        
        Integer credits = this.player.getCredits();
        Integer otherCredits = other.getPlayer().getCredits();
        
        if (credits.compareTo(otherCredits) > 0) {
            return 1;
        } else if (credits.compareTo(otherCredits) < 0) {
            return -1;
        }
        return 0;
    }

   
}
