/*
 * Drug data
 */
package entity;

/**
 *
 * @author casper
 */
public class Drug {
    
    private String name;
    private double basePrice;
    private double actualPrice;
    private double baseAmount;
    private double amount;
    
    public Drug(String name, double price, double availability) {
        this.name = name;
        this.basePrice = price;
        this.amount = availability;
        this.actualPrice = this.basePrice;
        this.baseAmount = this.amount;
    }
    
    /**
     * Adds an amount of drugs
     * @param amount 
     */
    public void removeDrugs(int amount) {
        this.amount -= amount;
    } 
    
    /**
     * Removes an amount of drugs
     * @param amount 
     */
    public void addDrugs(int amount) {
        this.amount -= amount;
    } 

    public double getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(double baseAmount) {
        this.baseAmount = baseAmount;
    }
    
    

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double availability) {
        this.amount = availability;
    }
}
