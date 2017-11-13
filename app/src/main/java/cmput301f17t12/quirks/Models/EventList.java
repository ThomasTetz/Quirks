package cmput301f17t12.quirks.Models;

import java.io.Serializable;
import java.util.ArrayList;

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

    public void clearAndAddEvents(EventList eventList){
        events.clear();
        for(int i = 0; i<eventList.size(); i++){
            events.add(eventList.getEvent(i));
        }
    }

    public Event getEvent(int i){
        return events.get(i);
    }

    public Integer size() {
        return events.size();
    }

    public void removeEvent(Event event){
        events.remove(event);
    }

    public ArrayList<Event> getList(){
        return events;
    }

    // Testing
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < events.size(); i++) {
            output.append(i).append(": ").append(events.get(i).getComment()).append(" ");
        }
        return output.toString();
    }
}
