package cmput301f17t12.quirks.Models;

import java.io.Serializable;

public class Geolocation implements Serializable {
    private double longitude;
    private double latitude;

    public Geolocation(double longitude, double latitude){
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    public double getLatitude(){
        return latitude;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

}
