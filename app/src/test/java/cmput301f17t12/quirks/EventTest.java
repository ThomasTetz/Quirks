package cmput301f17t12.quirks;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

import cmput301f17t12.quirks.Models.Event;
import cmput301f17t12.quirks.Models.Geolocation;

public class EventTest {
    String user = "testuser123";
    private Geolocation geolocation = new Geolocation(1,1);

    // Test user
    @Test
    public void testGetUser() {

        Event event = new Event(user, "ate lettuce", null, new Date(), geolocation);
        assertEquals(event.getUser(), "testuser123");
    }

    @Test
    public void testSetUser() {

        Event event = new Event(user, "ate lettuce", null, new Date(), geolocation);
        event.setUser("zafra");
        assertEquals(event.getUser(), "zafra");
    }

    // Test comment
    @Test
    public void testGetComment() {

        Event event = new Event(user, "ate lettuce", null, new Date(), geolocation);
        assertEquals(event.getComment(), "ate lettuce");
    }

    @Test
    public void testSetComment() {
        Event event = new Event(user, "ate lettuce", null, new Date(), geolocation);
        event.setComment("Some new comment");
        assertEquals(event.getComment(), "Some new comment");
    }

    // Test geolocation
    @Test
    public void testGetGeolocation() {
        Geolocation geolocation = new Geolocation(53.544389, -113.490927);
        Event event = new Event(user, "ate lettuce", null, new Date(), geolocation);
        Geolocation returnedGeolocation = event.getGeolocation();
        assertEquals(geolocation, returnedGeolocation);
    }

    @Test
    public void testSetGeolocation() {
        Event event = new Event(user, "ate lettuce", null, new Date(), geolocation);

        Geolocation geolocation1 = new Geolocation(53.544389, -113.490927);
        event.setGeolocation(geolocation1);
        Geolocation returnedGeolocation = event.getGeolocation();
        assertEquals(geolocation1, returnedGeolocation);

        Geolocation geolocation2 = new Geolocation(23.544389, 109.490927);
        event.setGeolocation(geolocation2);
        returnedGeolocation = event.getGeolocation();
        assertEquals(geolocation2, returnedGeolocation);

        Geolocation geolocation3 = new Geolocation(-44.544389, 109.490927);
        event.setGeolocation(geolocation3);
        returnedGeolocation = event.getGeolocation();
        assertEquals(geolocation3, returnedGeolocation);

        Geolocation geolocation4 = new Geolocation(-23.544389, -109.490927);
        event.setGeolocation(geolocation4);
        returnedGeolocation = event.getGeolocation();
        assertEquals(geolocation4, returnedGeolocation);
    }

    @Test
    public void testDeleteGeolocation() {
        Event event = new Event(user, "ate lettuce", null, new Date(), geolocation);

        Geolocation geolocation1 = new Geolocation(53.544389, -113.490927);
        event.setGeolocation(geolocation1);
        event.deleteGeolocation();
        Geolocation returnedGeolocation = event.getGeolocation();
        assertEquals(null, returnedGeolocation);
    }

    @Test
    public void testSetPhotoURI() {
        Event event = new Event(user, "ate lettuce", null, new Date(), geolocation);
        byte[] someArray = {-1, -2, 40};
        event.setPhotoByte(someArray);
        assertEquals(event.getPhotoByte(), someArray);
    }

    @Test
    public void testGetDate() {
        Date date = new Date();
        Event event = new Event(user, "ate lettuce", null, date, geolocation);
        assertEquals(event.getDate(), date);
    }

    @Test
    public void testSetDate() {
        Event event = new Event(user, "ate lettuce", null, new Date(), geolocation);
        Date date = new Date();
        event.setDate(date);
        assertEquals(event.getDate(), date);
    }

}

