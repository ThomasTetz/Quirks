package cmput301f17t12.quirks.Models;

public abstract class Request {
   private User fromUser;

   public Request(User fromUser){
       this.fromUser = fromUser;
   }

   public User getFromUser(){
       return this.fromUser;
   }

   abstract String getDetails();
}
