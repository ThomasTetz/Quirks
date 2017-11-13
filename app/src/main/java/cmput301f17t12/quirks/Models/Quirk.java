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


    /**
     * Quirk constructor with required values only
     * @param title Title string of the Quirk
     * @param type Type string of the Quirk
     * @param startDate Starting date of the quirk
     * @param occDate Weekdays that Quirk occurs
     * @param goalValue Progress goal value of the Quirk
     * @param user User string
     * @param reason Reason string of the quirk
     */
    public Quirk(String title, String type, Date startDate, ArrayList<Day> occDate, int goalValue, String user, String reason) {
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
        this.reason = reason;
    }

    /**
     *
     * @param events
     * @param title
     * @param type Type string of the Quirk
     * @param reason Reason string of the quirk
     * @param startDate Starting date of the quirk
     * @param occDate Weekdays that Quirk occurs
     * @param currValue Current progress value of the Quirk
     * @param goalValue Progress goal value of the Quirk
     * @param user
     */
    public Quirk(EventList events, String title, String type, String reason,
                 Date startDate, ArrayList<Day> occDate, int currValue, int goalValue, String user){
        this.events = events;
        this.title = title;
        this.type = type;
        this.reason = reason;
        this.startDate = startDate;
        this.occDate = occDate;
        this.currValue = currValue;
        this.goalValue = goalValue;
        this.user = user;
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
            this.currValue += 1;
        }
    }

    public void decCurrValue(){
        if(currValue > 0) {
            this.currValue -= 1;
        }
    }

    public String getUser(){ return user; }

    public void setUser(String user){
        this.user = user;
    }

    public boolean isEquals(Quirk quirk){
        return this.user.equals(quirk.user) && this.title.equals(quirk.title) && this.type.equals(quirk.type) && this.reason.equals(quirk.reason)
                && this.startDate.equals(quirk.startDate) && this.occDate.equals(quirk.occDate) && this.currValue == quirk.currValue
                && this.goalValue == quirk.goalValue && this.events.equals(quirk.events);
    }

    @Override
    public String buildNewsHeader(String extra) {
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
