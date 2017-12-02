package cmput301f17t12.quirks.Models;

import java.util.ArrayList;

public class TradeRequest extends Request {

    private ArrayList<Drop> fromUserDrop;
    private ArrayList<Drop> myDrop;

    public TradeRequest(User fromUser, ArrayList<Drop> fromUserDrop, ArrayList<Drop> myDrop) {
        super(fromUser);
        this.fromUserDrop = fromUserDrop;
        this.myDrop = myDrop;
    }

    @Override
    String getDetails() {
        return "You have a trade request from " + getFromUser().getUsername();
    }
}
