package cmput301f17t12.quirks.Models;

/**
 * Created by root on 12/1/17.
 */

public abstract class Request {
   User fromUser;

   public Request(User fromUser){
       this.fromUser = fromUser;
   }

   public User getFromUser(){
       return this.fromUser;
   }


   abstract String getDetails();
}
