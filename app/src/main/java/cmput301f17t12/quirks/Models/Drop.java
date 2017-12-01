package cmput301f17t12.quirks.Models;

import java.io.Serializable;

<<<<<<< HEAD
import cmput301f17t12.quirks.Enumerations.DropType;
import cmput301f17t12.quirks.Interfaces.Tradable;

=======
//public class Drop implements Tradable {
>>>>>>> 35bec57a9da5c7310a52dcb4ef22bb4cdcae2fd2
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
