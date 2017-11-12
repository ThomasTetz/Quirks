package cmput301f17t12.quirks.Models;

import android.text.format.DateUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import cmput301f17t12.quirks.Enumerations.Day;
import cmput301f17t12.quirks.Interfaces.Newsable;
import io.searchbox.annotations.JestId;

public class Quirk implements Newsable, Serializable {
    private EventList events;
    private String user;
    private String title;
    private String type;
    private String reason;
    private Date startDate;
    private ArrayList<Day> occDate;
    private int currValue;
    private int goalValue;

    @JestId
    private String qid;

    // Parameter with only required values
    public Quirk(String title, String type, Date startDate, ArrayList<Day> occDate, int goalValue, String user) {
        this.events = new EventList();
        this.title = title;
        this.type = type;
        if (startDate == null) {
            this.startDate = new Date();
        } else {
            this.startDate = startDate;
        }
        this.occDate = occDate;
        this.goalValue = goalValue;
        this.currValue = 0;
        this.user = user;
    }

    public Quirk(EventList events, String title, String type, String reason,
                 Date startDate, ArrayList<Day> occDate, int currValue, int goalValue){
        this.events = events;
        this.title = title;
        this.type = type;
        this.reason = reason;
        this.startDate = startDate;
        this.occDate = occDate;
        this.currValue = currValue;
        this.goalValue = goalValue;
    }

    public String getId(){
        return qid;
    }

    public void setId(String qid){
        this.qid = qid;
    }

    public void addEvent(Event event){
        events.addEvent(event);
    }

    public Event getEvent(int i){
        return events.getEvent(i);
    }

    public EventList getEventList(){
        return events;
    }

    public void removeEvent(Event event){
        events.removeEvent(event);
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getReason(){
        return reason;
    }

    public void setReason(String reason){
        this.reason = reason;
    }

    public Date getDate(){
        return startDate;
    }

    public void setDate(Date startdate){
        this.startDate = startDate;
    }

    public ArrayList<Day> getOccDate(){
        return occDate;
    }

    public void setOccDate(ArrayList<Day> occDate){
        this.occDate = occDate;
    }

    public int getGoalValue(){
        return goalValue;
    }

    public void setGoalValue(int goalValue){
        this.goalValue = goalValue;
    }

    public int getCurrValue(){
        return currValue;
    }

    public void incCurrValue(){
        if(currValue < goalValue) {
            this.currValue += 1; // @TODO may need to adjust the increment value
        }
    }

    public void resetCurrValue(){
        this.currValue = 0; // @TODO potentially change what this does
    }

    public String getUser(){ return user; }

    public void setUser(String user){
        this.user = user;
    }


    @Override
    public String buildNewsHeader() {
        return getUser() + " added a new Quirk!";
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
        return getTitle();
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
