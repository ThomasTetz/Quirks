package cmput301f17t12.quirks.Models;

import java.io.Serializable;
import java.util.ArrayList;

import io.searchbox.annotations.JestId;

public class User implements Serializable {
    private String username;
    private Inventory inventory;
    private ArrayList<User> friends;
    private QuirkList quirks;

    @JestId
    private String uid;

    public User(String username, Inventory inventory, ArrayList<User> friends, QuirkList quirks){
        this.username = username;
        this.inventory = inventory;
        this.friends = friends;
        this.quirks = quirks;
    }


    public void setId(String uid){
        this.uid = uid;
    }

    public String getId(){
        return uid;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public ArrayList<User> getFriends(){
        return friends;
    }

    public void addFriend(User friend){
        friends.add(friend);
    }

    public boolean hasFriend(User friend){
        return friends.contains(friend);
    }

    public void deleteFriend(User friend){
        friends.remove(friend);
    }

    public QuirkList getQuirks() {
        return quirks;
    }

    public void addQuirk(Quirk quirk){
        quirks.addQuirk(quirk);
    }

    public void deleteQuirk(Quirk quirk){
        quirks.removeQuirk(quirk);
    }

    public boolean trade(Drop otherDrop, Drop myDrop){
        // @TODO needs work
        if (inventory.hasDrop(myDrop)){
            inventory.removeDrop(myDrop);
            inventory.addDrop(otherDrop);
            return true;
        }
        return false;
    }
}
