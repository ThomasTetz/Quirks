package cmput301f17t12.quirks.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable {
    private ArrayList<Drop> inventory = new ArrayList<Drop>();

    /**
     * Constructor for Inventory Object
     */
    public Inventory(){

    }

    /**
     * Add drop to the Inventory
     * @param drop Drop to add
     */
    public void addDrop(Drop drop){
        inventory.add(drop);
        //if (!hasDrop(drop)){
        //  inventory.add(drop);
        //}
        //else{
        //  System.out.println("already in inventory");
        //}

    }

    /**
     * Check if the inventory has the specified drop
     * @param drop Drop to check
     * @return Boolean value if Inventory has the specified drop
     */
    public boolean hasDrop(Drop drop){

        for (int i = 0; i<inventory.size(); i++){
            Drop d = inventory.get(i);
            if (d.getDropType().equals(drop.getDropType()) && d.getDropType()==drop.getDropType()){
                return true;
            }
        }
        return false;
    }

    /**
     * Get the drop at the specified index
     * @param i Index
     * @return Return drop at specified index
     */
    public Drop getDrop(int i){
        return inventory.get(i);
    }

    /**
     * Remmove drop from Inventory
     * @param drop Drop to remove
     */
    public void removeDrop(Drop drop){
        inventory.remove(drop);
    }

    /**
     * Return full Inventory
     * @return Inventory ArrayList
     */
    public ArrayList<Drop> getList(){
        return inventory;
    }

    /**
     * Print contents of Inventory. Used for testing
     */
    public void printItems(){
        for (int i = 0; i<inventory.size(); i++){
            System.out.println(" >"+inventory.get(i).getDropType().getName());
        }
    }




}
