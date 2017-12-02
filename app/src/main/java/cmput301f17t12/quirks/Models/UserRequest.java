package cmput301f17t12.quirks.Models;

/**
 * Created by root on 12/1/17.
 */

public class UserRequest extends Request {

    User fromUser;
    User toUser;
    public UserRequest(User fromUser , User toUser){
        super(fromUser);
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    @Override
    String getDetails() {
        return null;
    }
}
