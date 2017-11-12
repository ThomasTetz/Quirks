package cmput301f17t12.quirks.Models;

import java.util.ArrayList;

/**
 * Created by thomas on 2017-10-22.
 */

public class Inventory {
    private ArrayList<Drop> inventory = new ArrayList<Drop>();

    public Inventory(){

    }

    public void addDrop(Drop drop){
        inventory.add(drop);
    }

    public boolean hasDrop(Drop drop){

        int idx = -1;
        for (int i = 0; i<inventory.size(); i++){
            Drop d = inventory.get(i);
            if (d.getName().equals(drop.getName()) && d.getRarity()==drop.getRarity()){
                return true;
            }
        }
        return false;

//        return inventory.contains(drop);
    }

    public Drop getDrop(int i){
        return inventory.get(i);
    }

    public void removeDrop(Drop drop){
        inventory.remove(drop);
    }

    public ArrayList<Drop> getList(){
        return inventory;
    }

    public void printItems(){
        for (int i = 0; i<inventory.size(); i++){
            System.out.println(" >"+inventory.get(i).getName());
        }
    }




}
