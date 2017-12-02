package cmput301f17t12.quirks.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class TradeRequest extends Request implements Serializable {

    private ArrayList<Drop> fromUserDrop;
    private ArrayList<Drop> myDrop;

    public TradeRequest(String userName, ArrayList<Drop> fromUserDrop, ArrayList<Drop> myDrop) {
        super(userName);
        this.fromUserDrop = fromUserDrop;
        this.myDrop = myDrop;
    }

    @Override
    String getDetails() {
        return "You have a trade request from " + getFromUser();
    }

    public ArrayList<Drop> getFromUserDrop() {
        return fromUserDrop;
    }

    public ArrayList<Drop> getMyDrop() {
        return myDrop;
    }
}
