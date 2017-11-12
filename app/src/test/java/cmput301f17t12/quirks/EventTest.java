//package cmput301f17t12.quirks;
//
//import android.net.Uri;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//import java.util.ArrayList;
//import java.util.Date;
//
//import cmput301f17t12.quirks.Enumerations.Day;
//import cmput301f17t12.quirks.Models.Event;
//import cmput301f17t12.quirks.Models.EventList;
//import cmput301f17t12.quirks.Models.Geolocation;
//import cmput301f17t12.quirks.Models.Quirk;
//
//public class EventTest {
//    private float currFloat = 5.6f;
//    private float goalFloat = 13.1f;
//    private Quirk quirk = new Quirk (new EventList(), "title", "type", "reason",
//                                                  new Date(), new ArrayList<Day>(), currFloat, goalFloat);
//    private Geolocation geolocation = new Geolocation(1,1);
//    // Test comment
//    @Test
//    public void testGetComment() {
//        Event event = new Event(quirk, "ate lettuce", null, new Date(), geolocation);
//        assertEquals(event.getComment(), "ate lettuce");
//    }
//
//    @Test
//    public void testSetComment() {
//        Event event = new Event(quirk, "ate lettuce", null, new Date(), geolocation);
//        event.setComment("Some new comment");
//        assertEquals(event.getComment(), "Some new comment");
//    }
//
//    // Test geolocation
//    @Test
//    public void testGetGeolocation() {
//        Geolocation geolocation = new Geolocation(53.544389, -113.490927);
//        Event event = new Event(quirk, "ate lettuce", null, new Date(), geolocation);
//        Geolocation returnedGeolocation = event.getGeolocation();
//        assertEquals(geolocation, returnedGeolocation);
//    }
//
//    @Test
//    public void testSetGeolocation() {
//        Event event = new Event(quirk, "ate lettuce", null, new Date(), geolocation);
//
//        Geolocation geolocation1 = new Geolocation(53.544389, -113.490927);
//        event.setGeolocation(geolocation1);
//        Geolocation returnedGeolocation = event.getGeolocation();
//        assertEquals(geolocation1, returnedGeolocation);
//
//        Geolocation geolocation2 = new Geolocation(23.544389, 109.490927);
//        event.setGeolocation(geolocation2);
//        returnedGeolocation = event.getGeolocation();
//        assertEquals(geolocation2, returnedGeolocation);
//
//        Geolocation geolocation3 = new Geolocation(-44.544389, 109.490927);
//        event.setGeolocation(geolocation3);
//        returnedGeolocation = event.getGeolocation();
//        assertEquals(geolocation3, returnedGeolocation);
//
//        Geolocation geolocation4 = new Geolocation(-23.544389, -109.490927);
//        event.setGeolocation(geolocation4);
//        returnedGeolocation = event.getGeolocation();
//        assertEquals(geolocation4, returnedGeolocation);
//    }
//
//    @Test
//    public void testDeleteGeolocation() {
//        Event event = new Event(quirk, "ate lettuce", null, new Date(), geolocation);
//
//        Geolocation geolocation1 = new Geolocation(53.544389, -113.490927);
//        event.setGeolocation(geolocation1);
//        event.deleteGeolocation();
//        Geolocation returnedGeolocation = event.getGeolocation();
//        assertEquals(null, returnedGeolocation);
//    }
//
//    // Some tests for removing and adding photos
//    @Test
//    public void testgetPhotoURI() {
////        Uri someUri = Uri.parse("android.resource://cmput301f17t12.quirks/" + R.drawable.);
//        Uri someUri = Uri.parse("android.R.drawable.btn_plus");
//        Event event = new Event(quirk, "ate lettuce", someUri, new Date(), geolocation);
//        assertEquals(event.getPhotoUri(), someUri);
//    }
//
//    @Test
//    public void testsetPhotoURI() {
//        Event event = new Event(quirk, "ate lettuce", null, new Date(), geolocation);
////        Uri someuri = new Uri("SOME PHOTO URI HERE");
//        Uri someUri = Uri.parse("android.R.drawable.btn_plus");
//        event.setPhotoUri(someUri);
//        assertEquals(event.getPhotoUri(), someUri);
//    }
//
//    @Test
//    public void testGetDate() {
//        Date date = new Date();
//        Event event = new Event(quirk, "ate lettuce", null, date, geolocation);
//        assertEquals(event.getDate(), date);
//    }
//
//    @Test
//    public void testSetDate() {
//        Event event = new Event(quirk, "ate lettuce", null, new Date(), geolocation);
//        Date date = new Date();
//        event.setDate(date);
//        assertEquals(event.getDate(), date);
//    }
//
//}
//
