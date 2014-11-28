package entity;

/**
 *
 * @author Nikolaj
 */
public class Item {
    private String itemName;
    public String getItemName()
    {
        return itemName;
    }
    
    private int itemValue;
    public String getItemValue()
    {
        return itemName;
    }

    public Item(String itemName, int itemValue) {
        this.itemName = itemName;
        this.itemValue = itemValue;
    }
}
