package cmput301f17t12.quirks.Models;

import java.io.Serializable;

public class UserRequest extends Request implements Serializable {

    public UserRequest(String fromUser){
        super(fromUser);
    }

    @Override
    public String getHeader() {
        return "Friend request!";
    }

    @Override
    public String getDetails() {
        return "Would you like to add " + getFromUser() + "?";
    }
}
