package cmput301f17t12.quirks.Models;

import java.io.Serializable;

public class UserRequest extends Request implements Serializable {

    public UserRequest(String fromUser){
        super(fromUser);
    }

    @Override
    String getDetails() {
        return "You have a friend request from " + getFromUser();
    }
}
