package cmput301f17t12.quirks.Models;

import java.io.Serializable;

import cmput301f17t12.quirks.Enumerations.Rarity;

//public class Drop implements Tradable {
public class Drop implements Serializable {

    public Rarity rarity;
    public String name;

    public Drop(Rarity rarity, String name){
        this.rarity = rarity;
        this.name = name;
    }

    public String dropDetails(){
        return "";
    }

    public Rarity getRarity(){
        return rarity;
    }

    public void setRarity(Rarity rarity){
        this.rarity = rarity;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

//    public boolean trade(User user, Drop drop){
//        return false;
//    }
}
