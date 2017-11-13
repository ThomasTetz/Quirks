package cmput301f17t12.quirks.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class QuirkList implements Serializable {
    private ArrayList<Quirk> quirks = new ArrayList<Quirk>();

    public QuirkList(){

    }

    public void addQuirk(Quirk quirk){
        quirks.add(quirk);
    }

    public void clearAndAddQuirks(QuirkList quirkList){
        quirks.clear();
        for(int i = 0; i<quirkList.size(); i++){
            quirks.add(quirkList.getQuirk(i));
        }
    }

    public boolean hasQuirk(Quirk quirk){
        return quirks.contains(quirk);
    }

    public Quirk getQuirk(int i){
        return quirks.get(i);
    }

    public void removeQuirk(Quirk quirk){
        quirks.remove(quirk);
    }

    public ArrayList<Quirk> getList(){
        return quirks;
    }

    public void printQuirks(){
        for (int i=0; i<quirks.size(); i++){
            System.out.println(quirks.get(i).getType());
        }
    }

    public int size(){ return quirks.size(); }

    // Testing
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < quirks.size(); i++) {
            output.append(i).append(": ").append(quirks.get(i).getTitle()).append(" ");
        }
        return output.toString();
    }

}
