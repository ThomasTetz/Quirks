package cmput301f17t12.quirks;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import cmput301f17t12.quirks.Enumerations.Day;
import cmput301f17t12.quirks.Models.Event;
import cmput301f17t12.quirks.Models.EventList;
import cmput301f17t12.quirks.Models.Geolocation;
import cmput301f17t12.quirks.Models.Quirk;

import static org.junit.Assert.*;

public class EventListTest {

    // Test getEventList
    @Test
    public void testGetList() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2);
        Event event = new Event(quirk, "comment", null, new Date(), new Geolocation(1,1));

        EventList events1 = new EventList();

        events1.addEvent(event);

        ArrayList<Event> events2 = new ArrayList<Event>();
        events2.add(event);

        assertEquals(events1.getList(), events2);
    }

    // Test adding Event to EventList
    @Test
    public void testAddEvent() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2);
        Event event = new Event(quirk, "comment", null, new Date(), new Geolocation(1,1));

        EventList events = new EventList();

        assertFalse(events.hasEvent(event));
        events.addEvent(event);
        assertTrue(events.hasEvent(event));

    }

    // Test EventList has Event
    @Test
    public void testHasEvent() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2);
        Event event = new Event(quirk, "comment", null, new Date(), new Geolocation(1,1));

        EventList events = new EventList();

        assertEquals(events.hasEvent(event), events.getList().contains(event));
        events.addEvent(event);
        assertEquals(events.hasEvent(event), events.getList().contains(event));
    }

    // Test getting Event from EventList
    @Test
    public void testGetEvent() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2);
        Event event = new Event(quirk, "comment", null, new Date(), new Geolocation(1,1));

        EventList events = new EventList();

        events.addEvent(event);

        assertEquals(events.getEvent(0), event);
    }

    // Test to deleting Event from Eventlist
    @Test
    public void testDeleteEvent() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2);
        Event event = new Event(quirk, "comment", null, new Date(), new Geolocation(1,1));

        EventList events = new EventList();

        events.addEvent(event);
        assertTrue(events.hasEvent(event));
        events.removeEvent(event);
        assertFalse(events.hasEvent(event));
    }

}


