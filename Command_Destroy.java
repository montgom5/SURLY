/*----------------------------------------------------- 
 * Authors: Michael Montgomery and Robert Mullarky
 * File: Command_Destroy.java
 * Description: Trigger for database to destroy a relation
 * Date: 7/19/2018
 *----------------------------------------------------*/
 
//Import statements
import java.util.ArrayList;

//Class declaration
public class Command_Destroy{
   
   //Constants
   public static final int INDEX_NAME = 1;

   //Command_Destroy constructor
   //Passes the name of the relation to be destroyed to database for destruction
   public Command_Destroy(ArrayList<String> args, Catalog log){
      String relation = args.get(INDEX_NAME);
      Database d = Database.getDatabase();
      if(log.getSchema(args.get(1))!= null){
         log.removeItem(relation);
         d.destroyRelation(relation);
         System.out.println(relation + " Destroyed");
      }
      else{
         System.out.println(relation + " is not in the database");
      }
   }
}