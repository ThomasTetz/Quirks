package cmput301f17t12.quirks;

import org.junit.Test;

import cmput301f17t12.quirks.Enumerations.Rarity;
import cmput301f17t12.quirks.Models.Drop;

import static org.junit.Assert.*;

public class DropsTest {

    @Test
    public void testSetRarity() {
        Drop drop = new Drop(Rarity.COMMON, "drop_name");
        drop.setRarity(Rarity.RARE);
        assertEquals(drop.getRarity(), Rarity.RARE);
    }

    @Test
    public void testGetRarity(){
        Drop drop = new Drop(Rarity.COMMON, "drop_name");
        Rarity rarity = drop.getRarity();
        assertEquals(rarity, Rarity.COMMON);
    }

    @Test
    public void testSetName() {
        Drop drop = new Drop(Rarity.COMMON, "drop_name");
        drop.setName("item");
        assertEquals(drop.getName(),"item");
    }

    @Test
    public void testGetName(){
        Drop drop = new Drop(Rarity.COMMON, "drop_name");
        String dropName = drop.getName();
        assertEquals(dropName,"drop_name");
    }


}

