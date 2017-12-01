package cmput301f17t12.quirks.Models;

/**
 * Created by root on 12/1/17.
 */

public abstract class Request {
   User fromUser;
   User toUser;

   public Request(User fromUser, User toUser){
       this.fromUser = fromUser;
       this.toUser = toUser;
   }

   public User getFromUser(){
       return this.fromUser;
   }
   public  User getToUser(){
       return this.toUser;
   }

   abstract String getDetails();
}
