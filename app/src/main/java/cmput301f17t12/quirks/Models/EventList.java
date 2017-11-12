package cmput301f17t12.quirks.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by thomas on 2017-10-21.
 */

public class EventList implements Serializable {
    private ArrayList<Event> events = new ArrayList<Event>();

    public EventList(){

    }

    public void addEvent(Event event){
        events.add(event);
    }

    public boolean hasEvent(Event event){
        return events.contains(event);
    }

    public Event getEvent(int i){
        return events.get(i);
    }

    public void removeEvent(Event event){
        events.remove(event);
    }

    public ArrayList<Event> getList(){
        return events;
    }
}
