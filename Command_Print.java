/*----------------------------------------------------- 
 * Authors: Michael Montgomery and Robert Mullarky
 * File: Command_Print.java
 * Description: Trigger for database to print relation
 * Date: 7/19/2018
 *----------------------------------------------------*/
 
//Import statements
import java.util.ArrayList;

//Class declaration
public class Command_Print{

   //Command_Print constructor
   //Loops trhough string arguments representing
   //relations to print and sends each one to database for printing
   public Command_Print(ArrayList<String> args, Catalog log){
      int i = 1;
      Database d = Database.getDatabase();
      while(i < args.size()){
         if(log.getSchema(args.get(i))!= null){
            d.printRelation(args.get(i));
         }else if(args.get(i).toUpperCase().equals("CATALOG")){
            log.printSchema();
         }
         else{
            d.printRelation(args.get(i));
         }
         i++;
      }
   }
}
