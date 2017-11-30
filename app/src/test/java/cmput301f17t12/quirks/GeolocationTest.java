package cmput301f17t12.quirks;

import org.junit.Test;

import cmput301f17t12.quirks.Models.Geolocation;

import static org.junit.Assert.*;

public class GeolocationTest {

    // Test set latitude (lat,lon)
    @Test
    public void testSetLatitude() {
        Geolocation geoLoc = new Geolocation(123.321,1234.4321);
        geoLoc.setLatitude(2234.23);
        Geolocation geoLoc1 = new Geolocation(2234.23,1234.4321);
        assertEquals(geoLoc, geoLoc1);
    }

    // Test get longitude (lat,lon)
    @Test
    public void testGetLongitude() {
        Geolocation geoLoc = new Geolocation(123.321,1234.4321);
        double lat = geoLoc.getLongitude();
        assertEquals(lat, 1234.4321);
    }
}

