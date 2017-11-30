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

    /**
     * Set the userID of the User
     * @param uid UserID string
     */
    public void setId(String uid){
        this.uid = uid;
    }

    /**
     * Get the userID of the User
     * @return UserID string
     */
    public String getId(){
        return uid;
    }

    /**
     * Set the username of the User
     * @param username Username string
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Get the username of the User
     * @return Username string
     */
    public String getUsername(){
        return username;
    }

    /**
     * Get the inventory of the User
     * @return Inventory object
     */
    public Inventory getInventory(){
        return inventory;
    }

    /**
     * Get the friends of the User
     * @return
     */
    public ArrayList<User> getFriends(){
        return friends;
    }

    /**
     * Add a friend to the User's friend-list
     * @param friend
     */
    public void addFriend(User friend){
        friends.add(friend);
    }

    /**
     * Return true if the User's friend-list contains the specified friend
     * @param friend Friend to check
     * @return Boolean value based on result
     */
    public boolean hasFriend(User friend){
        return friends.contains(friend);
    }

    /**
     * Delete friend from User's friend-list
     * @param friend
     */
    public void deleteFriend(User friend){
        friends.remove(friend);
    }

    /**
     * Get Quirks associated with the User
     * @return QuirkList of all the User's Quirks
     */
    public QuirkList getQuirks() {
        return quirks;
    }

    /**
     * Add a quirk to the Users QuirkList
     * @param quirk Quirk to add
     */
    public void addQuirk(Quirk quirk){
        quirks.addQuirk(quirk);
    }

    /**
     * Remove a quirk from the Users QuirkList
     * @param quirk Quirk to remove
     */
    public void deleteQuirk(Quirk quirk){
        quirks.removeQuirk(quirk);
    }

    /**
     * Trade drops between two users
     * @param otherDrop Drop of the other user
     * @param myDrop The User's drop
     * @return Boolean value based on if the trade was successful
     */
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
