package cmput301f17t12.quirks;

import org.junit.Test;
import static org.junit.Assert.*;

public class DropsTest {

    @Test
    public void testSetDropUser() {
        Drop drop = new drop("drop");
        User user1 = new User("Alex");
        user1.setDropUser(drop);
        assertEquals(user1.getDropUser(1),"drop");
    }

    @Test
    public void testgetDropUser() {
        Drop drop = new Drop("Drop1");
        User user1 = new User("Enzo");
        user1.setDropUser(drop);
        String returned_drop = user1.getDropUser(1);
        assertEquals(returned_drop,"Drop1");
    }

    @Test
    public void testSetRarity() {
        Drop drop = new Drop("Drop2");
        drop.setRarity("Rare drop");
        assertEquals(drop.getRarity(), "Rare drop");
    }

    @Test
    public void testGetRarity(){
        Drop drop = new Drop("Drop3");
        drop.setRarity("Rare drop");
        String rarity = drop.getRarity();
        assertEquals(rarity, "Rare drop");
    }

    @Test
    public void testSetName() {
        Drop drop = new Drop("Drop4");
        drop.setName("item");
        assertEquals(drop.getName(),"item");
    }

    @Test
    public void testGetName(){
        Drop drop = new Drop("Drop5");
        drop.setName("item");
        String dropName = drop.getName();
        assertEquals(dropName,"Drop5");
    }

    @Test
    public void testTrade(){
        User user1 = new User("enzo");
        User user2 = new User("alex");

        Drop drop1 = new Drop("Drop1");
        Drop drop2 = new Drop("Drop2");

        user1.setDropUser(drop1);
        user2.setDropUser(drop2);

        drop1.trade(user2,drop1);
        Drop drop_returned = user2.getDropUser(2);
        assertEquals(drop_returned.getName(), "Drop1");
    }
}
