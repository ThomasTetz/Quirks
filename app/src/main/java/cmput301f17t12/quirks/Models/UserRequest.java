package cmput301f17t12.quirks.Models;

public class UserRequest extends Request {

    private User fromUser;

    public UserRequest(User fromUser){
        super(fromUser);
    }

    @Override
    String getDetails() {
        return "You have a friend request from " + getFromUser().getUsername();
    }
}
