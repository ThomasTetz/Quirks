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

public class QuirkTest {
//    private Quirk quirk = new Quirk (new ArrayList<Event>(), "title", "type", "reason",
//            new Date(), new ArrayList<Day>(), 1, 2, "user;
//    private ArrayList<Event> events = new ArrayList<Event>();
//    private Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));

    // Test get All events
    @Test
    public void testGetEventList() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2, "user");
        EventList events = new EventList();
        Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0, 123, "user");
        EventList returnedEvents = myQuirk.getEventList();
        assertEquals(returnedEvents, events);
    }

    // Test adding an Event
    @Test
    public void testAddEvent() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2, "user");
        EventList events = new EventList();
        Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

//        ArrayList<Event> events = myQuirk.getEventList();
        Event event2 = new Event("test", "other event", null, new Date(), new Geolocation(3,4));
        events.addEvent(event2);
        assertTrue(events.hasEvent(event2));
    }

    // Test get an Event
    @Test
    public void testGetEvent() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2, "user");
        EventList events = new EventList();
        Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0, 123, "user");
        Event event2 = new Event("test", "other event", null, new Date(), new Geolocation(3,4));
        myQuirk.addEvent(event2);
        Event returnedEvent = myQuirk.getEvent(0);
        assertTrue(returnedEvent.isEquals(event2));
    }

    // Test remove an Event
    @Test
    public void testRemoveEvent() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2, "user");
        EventList events = new EventList();
        Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0, 123, "user");
        Event event2 = new Event("test", "other event", null, new Date(), new Geolocation(3,4));
        myQuirk.addEvent(event2);
        myQuirk.removeEvent(event2);
        EventList returnedEvents = myQuirk.getEventList();
        assertFalse(returnedEvents.hasEvent(event2));
    }

    // Test title
    @Test
    public void testGetTitle() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2, "user");
        EventList events = new EventList();
        Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0, 123, "user");
        assertEquals("title", myQuirk.getTitle());
    }

    @Test
    public void testSetTitle() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2, "user");
        EventList events = new EventList();
        Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0, 123, "user");
        myQuirk.setTitle("new title");
        assertEquals("new title", myQuirk.getTitle());
    }

    // Test type
    @Test
    public void testGetType() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2, "user");
        EventList events = new EventList();
        Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0, 123, "user");
        assertEquals("type", myQuirk.getType());
    }

    @Test
    public void testSetType() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2, "user");
        EventList events = new EventList();
        Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0, 123, "user");
        myQuirk.setType("new type");
        assertEquals("new type", myQuirk.getTitle());
    }

    // Testing quirk reason
    @Test
    public void testGetQuirkReason() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2, "user");
        EventList events = new EventList();
        Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0, 123, "user");
        assertEquals("reason", myQuirk.getReason());
    }

    @Test
    public void testSetQuirkReason() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2, "user");
        EventList events = new EventList();
        Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0, 123, "user");
        myQuirk.setReason("for the gains");
        String reason = myQuirk.getReason();
        assertEquals("for the gains", reason);
    }

    // Test start date
    @Test
    public void testGetDate() {
        Date now = new Date();
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                now, new ArrayList<Day>(), 1, 2, "user");
        EventList events = new EventList();
        Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", now, new ArrayList<Day>(), 0, 123, "user");
        assertEquals(now, myQuirk.getDate());
    }

    @Test
    public void testSetDate() {
        Date now = new Date();
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2, "user");
        EventList events = new EventList();
        Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", now, new ArrayList<Day>(), 0, 123, "user");
        // Some date here
        Date newDate = new Date();
        myQuirk.setDate(newDate);
        assertEquals(newDate, myQuirk.getDate());
    }

    // Testing quirk occurences
    @Test
    public void testGetOccDate() {
        ArrayList<Day> occurence = new ArrayList<>();
        occurence.add(Day.MONDAY);
        occurence.add(Day.TUESDAY);


        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2, "user");
        EventList events = new EventList();
        Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0, 123, "user");

        ArrayList<Day> returnedOccurence = myQuirk.getOccDate();
        assertEquals(occurence, returnedOccurence);
    }

    @Test
    public void testSetOccDate() {
        ArrayList<Day> occurence = new ArrayList<>();
        occurence.add(Day.MONDAY);
        occurence.add(Day.TUESDAY);

        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2, "user");
        EventList events = new EventList();
        Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0, 123, "user");


        myQuirk.setOccDate(occurence);
        assertEquals(occurence, myQuirk.getOccDate());
    }

    // Test goal
    @Test
    public void testGetGoalValue() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2, "user");
        EventList events = new EventList();
        Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0, 123, "user");
        assertEquals(myQuirk.getGoalValue(), 123);
    }

    @Test
    public void testSetGoalValue() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2, "user");
        EventList events = new EventList();
        Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0, 123, "user");

        myQuirk.setGoalValue(170);
        assertEquals(myQuirk.getGoalValue(), 170);
    }

    // Test progressing quirk
    @Test
    public void testGetCurrValue() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2, "user");
        EventList events = new EventList();
        Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0, 123, "user");

        // Should have no progress new quirk, 123 is goal
        assertEquals(myQuirk.getCurrValue(), 0);
    }

    @Test
    public void testIncCurrValue() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2, "user");
        EventList events = new EventList();
        Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0, 123, "user");
        int previous = myQuirk.getCurrValue();
        myQuirk.incCurrValue();
        assertEquals(myQuirk.getCurrValue(), previous + 1);
    }

    @Test
    public void testdecCurrValue() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2, "user");
        EventList events = new EventList();
        Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0, 123, "user");
        myQuirk.incCurrValue();
        myQuirk.incCurrValue();
        myQuirk.decCurrValue();
        assertEquals(myQuirk.getCurrValue(), 1);
    }

    @Test
    public void testSetUser() {
        Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
                new Date(), new ArrayList<Day>(), 1, 2, "user");
        EventList events = new EventList();
        Event event = new Event("test", "comment", null, new Date(), new Geolocation(1,1));
        events.addEvent(event);

        Quirk myQuirk = new Quirk(events, "title", "type", "reason", new Date(), new ArrayList<Day>(), 0, 123, "user");
        myQuirk.setUser("zafra");
        assertEquals("zafra", myQuirk.getUser());
    }
}

