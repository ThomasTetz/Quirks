package cmput301f17t12.quirks.Models;

import java.io.Serializable;

import cmput301f17t12.quirks.Enumerations.DropType;

public class Drop implements Serializable {

    private DropType dropType;

    /**
     * Constructor for the drop object
     * @param dropType DropType enumeration of the object's dropType
     */
    public Drop(DropType dropType){
        this.dropType = dropType;
    }

    /**
     * Get drop dropType level
     * @return DropType enum of the drop
     */
    public DropType getDropType(){
        return dropType;
    }
}
