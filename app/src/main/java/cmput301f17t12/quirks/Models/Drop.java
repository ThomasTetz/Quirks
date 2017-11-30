package cmput301f17t12.quirks.Models;

import java.io.Serializable;

import cmput301f17t12.quirks.Enumerations.Rarity;

//public class Drop implements Tradable {
public class Drop implements Serializable {

    private Rarity rarity;
    private String name;

    /**
     * Constructor for the drop object
     * @param rarity Rarity enumeration of the object's rarity
     * @param name Drop name string
     */
    public Drop(Rarity rarity, String name){
        this.rarity = rarity;
        this.name = name;
    }

    public String getDropDetails(){
        return "";
    }

    /**
     * Get drop rarity level
     * @return Rarity enum of the drop
     */
    public Rarity getRarity(){
        return rarity;
    }

    /**
     * Set drop rarity level
     * @param rarity Rarity enum of the drop
     */
    public void setRarity(Rarity rarity){
        this.rarity = rarity;
    }

    /**
     * Get drop name
     * @return Drop name
     */
    public String getName(){
        return name;
    }

    /**
     * Set drop name
     * @param name Drop name
     */
    public void setName(String name){
        this.name = name;
    }

    //public boolean trade(User user, Drop drop){
    //      return false;
    //}
}
