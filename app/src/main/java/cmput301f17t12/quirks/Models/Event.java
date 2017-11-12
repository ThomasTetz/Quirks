package cmput301f17t12.quirks.Models;

import android.net.Uri;
import android.text.format.DateUtils;

import java.io.Serializable;
import java.util.Date;

import cmput301f17t12.quirks.Interfaces.Mappable;
import cmput301f17t12.quirks.Interfaces.Newsable;
import io.searchbox.annotations.JestId;

/**
 * Created by thomas on 2017-10-21.
 */

public class Event implements Mappable, Newsable, Serializable {

    private Quirk quirk;
    private String comment;
    private Uri photoUri;
    private Date date;
    private Geolocation geolocation;

    @JestId
    private String eid;

    // Event without URI and geolocation
    public Event(Quirk quirk, String comment, Date date){
        this.quirk = quirk;
        this.comment = comment;
        this.date = date;
    }

    public Event(Quirk quirk, String comment, Uri photoUri, Date date, Geolocation geolocation){
        this.quirk = quirk;
        this.comment = comment;
        this.photoUri = photoUri;
        this.date = date;
        this.geolocation = geolocation;
    }


    public String getId(){
        return eid;
    }

    public void setId(String eid){
        this.eid = eid;
    }
    public Quirk getQuirk(){
        return quirk;
    }

    public void setQuirk(Quirk quirk){
        this.quirk = quirk;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public Uri getPhotoUri(){
        return photoUri;
    }

    public void setPhotoUri(Uri photoUri){
        this.photoUri = photoUri;
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
        if (this.quirk==event.quirk && this.comment==event.comment && this.photoUri==event.photoUri
                && this.date==event.date && this.geolocation==event.geolocation) {
            return true;
        }
        return false;
    }

    @Override
    public String buildNewsHeader() {
        return getQuirk().getUser() + " logged an event!";
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
