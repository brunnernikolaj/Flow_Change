/*
 * Country data
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author casper
 */
public class Country {
    
    private ArrayList<Drug> drugs;
    private String name;
    
    public Country(String name, ArrayList<Drug> drugs) {
        this.name = name;
        this.drugs = drugs;
    }
    
    public ArrayList<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(ArrayList<Drug> drugs) {
        this.drugs = drugs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
