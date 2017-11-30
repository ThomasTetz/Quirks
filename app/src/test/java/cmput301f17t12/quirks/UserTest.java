package cmput301f17t12.quirks;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;

import cmput301f17t12.quirks.Enumerations.Day;
import cmput301f17t12.quirks.Enumerations.Rarity;
import cmput301f17t12.quirks.Models.Drop;
import cmput301f17t12.quirks.Models.EventList;
import cmput301f17t12.quirks.Models.Inventory;
import cmput301f17t12.quirks.Models.Quirk;
import cmput301f17t12.quirks.Models.QuirkList;
import cmput301f17t12.quirks.Models.User;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testGetFriendsList() {
        User user1 = new User("username1", new Inventory(), new ArrayList<User>(), new QuirkList());
        User user2 = new User("username2", new Inventory(), new ArrayList<User>(), new QuirkList());

        user1.addFriend(user2);

        ArrayList<User> list = new ArrayList<User>();
        list.add(user2);

        assertEquals(user1.getFriends(), list);
    }

    @Test
    public void testAddFriend() {
        User user1 = new User("username1", new Inventory(), new ArrayList<User>(), new QuirkList());
        User user2 = new User("username2", new Inventory(), new ArrayList<User>(), new QuirkList());
        assertFalse(user1.hasFriend(user2));
        user1.addFriend(user2);
        assertTrue(user1.hasFriend(user2));
    }

    @Test
    public void testDeleteFriend() {
        User user1 = new User("username1", new Inventory(), new ArrayList<User>(), new QuirkList());
        User user2 = new User("username2", new Inventory(), new ArrayList<User>(), new QuirkList());

        user1.addFriend(user2);
        assertTrue(user1.hasFriend(user2));
        user1.deleteFriend(user2);
        assertFalse(user1.hasFriend(user2));
    }

    @Test
    public void testGetQuirkList() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1f, 2f);
        QuirkList quirks = new QuirkList();
        quirks.addQuirk(quirk);

        User user1 = new User("username1", new Inventory(), new ArrayList<User>(), quirks);

        QuirkList returnedQuirkList = user1.getQuirks();
        assertEquals(returnedQuirkList, quirks);
    }

    @Test
    public void testAddQuirkToUser() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1f, 2f);
        QuirkList quirks = new QuirkList();

        User user1 = new User("username1", new Inventory(), new ArrayList<User>(), quirks);

        assertFalse(user1.getQuirks().hasQuirk(quirk));
        user1.addQuirk(quirk);
        assertTrue(user1.getQuirks().hasQuirk(quirk));

    }

    @Test
    public void testDeleteQuirkFromUser() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1f, 2f);
        QuirkList quirks = new QuirkList();

        User user1 = new User("username1", new Inventory(), new ArrayList<User>(), quirks);

        user1.addQuirk(quirk);
        assertTrue(user1.getQuirks().hasQuirk(quirk));
        user1.deleteQuirk(quirk);
        assertFalse(user1.getQuirks().hasQuirk(quirk));
    }


    @Test
    public void testTrade(){
        User user1 = new User("username1", new Inventory(), new ArrayList<User>(), new QuirkList());
        User user2 = new User("username2", new Inventory(), new ArrayList<User>(), new QuirkList());

        Drop drop1 = new Drop(Rarity.COMMON, "drop_1");
        Drop drop2 = new Drop(Rarity.COMMON, "drop_2");

        user1.getInventory().addDrop(drop1);
        user2.getInventory().addDrop(drop2);
        assertTrue(user1.getInventory().hasDrop(drop1));
        assertTrue(user2.getInventory().hasDrop(drop2));
        assertFalse(user1.getInventory().hasDrop(drop2));
        assertFalse(user2.getInventory().hasDrop(drop1));

        user1.trade(drop2, drop1);
        user2.trade(drop1, drop2);

        assertTrue(user1.getInventory().hasDrop(drop2));
        assertTrue(user2.getInventory().hasDrop(drop1));
        assertTrue(user1.getInventory().hasDrop(drop1));
        assertTrue(user2.getInventory().hasDrop(drop2));

    }
}

