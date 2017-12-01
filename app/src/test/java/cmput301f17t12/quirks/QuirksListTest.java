package cmput301f17t12.quirks;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import cmput301f17t12.quirks.Enumerations.Day;
import cmput301f17t12.quirks.Models.Event;
import cmput301f17t12.quirks.Models.EventList;
import cmput301f17t12.quirks.Models.Geolocation;
import cmput301f17t12.quirks.Models.Quirk;
import cmput301f17t12.quirks.Models.QuirkList;

import static org.junit.Assert.*;

public class QuirksListTest {

    // Test to getList from QuirkList
    @Test
    public void testGetList() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1f, 2f);
        EventList events = new EventList();
        Event event = new Event(quirk, "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0f, 123f);


        QuirkList quirks1 = new QuirkList();

        quirks1.addQuirk(myQuirk);

        ArrayList<Quirk> quirks2 = new ArrayList<Quirk>();
        quirks2.add(quirk);

        assertEquals(quirks1.getList(), quirks2);
    }

    // Test adding Quirk to QuirkList
    @Test
    public void testAddQuirk() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1f, 2f);
        EventList events = new EventList();
        Event event = new Event(quirk, "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0f, 123f);


        QuirkList quirks = new QuirkList();

        assertFalse(quirks.hasQuirk(myQuirk));
        quirks.addQuirk(myQuirk);
        assertTrue(quirks.hasQuirk(myQuirk));

    }

    // Test if Quirk exists in QuirkList
    @Test
    public void testHasQuirk() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1f, 2f);
        EventList events = new EventList();
        Event event = new Event(quirk, "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0f, 123f);
        QuirkList quirks = new QuirkList();

        assertEquals(quirks.hasQuirk(myQuirk), quirks.getList().contains(myQuirk));
        quirks.addQuirk(myQuirk);
        assertEquals(quirks.hasQuirk(myQuirk), quirks.getList().contains(myQuirk));
    }

    // Test to return the Quirk at location from QuirkList
    @Test
    public void testGetQuirk() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1f, 2f);
        EventList events = new EventList();
        Event event = new Event(quirk, "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0f, 123f);
        QuirkList quirks = new QuirkList();

        quirks.addQuirk(myQuirk);
        assertEquals(quirks.getQuirk(0), myQuirk);
    }

    // Test to remove Quirk from QuirkList
    @Test
    public void testDeleteQuirk() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1f, 2f);
        EventList events = new EventList();
        Event event = new Event(quirk, "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0f, 123f);
        QuirkList quirks = new QuirkList();

        quirks.addQuirk(myQuirk);
        assertTrue(quirks.hasQuirk(myQuirk));
        quirks.removeQuirk(myQuirk);
        assertFalse(quirks.hasQuirk(myQuirk));
    }

}
