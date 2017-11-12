package cmput301f17t12.quirks.Models;

import android.net.Uri;
import android.text.format.DateUtils;

import java.io.Serializable;
import java.util.Date;

import cmput301f17t12.quirks.Interfaces.Mappable;
import cmput301f17t12.quirks.Interfaces.Newsable;
import io.searchbox.annotations.JestId;

public class Event implements Mappable, Newsable, Serializable {

    private String user;
    private String comment;
    private String photoPath;
    private Date date;
    private Geolocation geolocation;

    @JestId
    private String eid;

    // Event without Path and geolocation
    public Event(String user, String comment, Date date){
        this.user = user;
        this.comment = comment;
        this.date = date;
    }

    // Event without geolocation
    public Event(String user, String comment, String photoUri, Date date) {
        this.user = user;
        this.comment = comment;
        if (comment == null) {
            this.comment = "";
        }
        this.photoPath = photoUri;
        if (photoUri == null) {
            this.photoPath = "";
        }
        this.date = date;
    }

    public Event(String user, String comment, String photoUri, Date date, Geolocation geolocation) {
        this.user = user;
        this.comment = comment;
        this.photoPath = photoUri;
        this.date = date;
        this.geolocation = geolocation;
    }


    public String getId(){
        return eid;
    }

    public void setId(String eid){
        this.eid = eid;
    }

    public String getUser(){
        return user;
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public String getPhotoPath(){
        return photoPath;
    }

    public void setPhotoPath(String photoUri){
        this.photoPath = photoUri;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Geolocation getGeolocation(){
        return geolocation;
    }

    public void setGeolocation(Geolocation geolocation){
        this.geolocation = geolocation;
    }

    public void deleteGeolocation(){
        geolocation = null;
    }

    public boolean isEquals(Event event){
        if (this.user == event.user && this.comment==event.comment && this.photoPath==event.photoPath
                && this.date==event.date && this.geolocation==event.geolocation) {
            return true;
        }
        return false;
    }

    @Override
    public String buildNewsHeader() {
        return getUser() + " logged an event!";
    }

    @Override
    public String buildDate() {
        CharSequence relativeTimeSpan = DateUtils.getRelativeTimeSpanString(
                getDate().getTime(),
                System.currentTimeMillis(),
                DateUtils.SECOND_IN_MILLIS,
                DateUtils.FORMAT_ABBREV_RELATIVE);
        return relativeTimeSpan.toString();
    }

    @Override
    public String buildNewsDescription() {
        return '"' + getComment() + '"';
    }
}
