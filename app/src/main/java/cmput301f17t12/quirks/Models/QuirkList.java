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

    public void clearAndAddQuirks(QuirkList newQuirks){
        System.out.println("the passed size inside clearAndAdd: " + newQuirks.size());
        System.out.println("the quirks to add inside clearAndAdd before clear:");
        for (int i = 0; i < newQuirks.size(); i++){
            System.out.println("\t" + newQuirks.getQuirk(i).getType());
        }
        this.quirks.clear();
        System.out.println("the quirks to add inside clearAndAdd after clear:");
        for (int i = 0; i < newQuirks.size(); i++){
            System.out.println("\t" + newQuirks.getQuirk(i).getType());
        }
        for(int i = 0; i<newQuirks.size(); i++){
            System.out.println("\t\t> clearAndAdd adding: " + newQuirks.getQuirk(i).getType());
            this.quirks.add(newQuirks.getQuirk(i));
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
