package cmput301f17t12.quirks.Models;

import java.io.Serializable;

public abstract class Request implements Serializable {
   private String fromUser;

   public Request(String fromUser){
       this.fromUser = fromUser;
   }

   public String getFromUser(){
       return this.fromUser;
   }

   public abstract String getHeader();

   public abstract String getDetails();
}
