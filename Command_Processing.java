import java.util.*;
public class Command_Processing{
   
   
   public Command_Processing(){
   
   }
   
   
   
   
   
   
   public void GiveArgument(ArrayList<String> args, Catalog log){
      
      if(args.get(0).toUpperCase().equals("RELATION")){
         
         Command_Relation insert = new Command_Relation(args, log);
      }
      else if(args.get(0).toUpperCase().equals("INSERT")){
         
         Command_Insert insert = new Command_Insert(args, log);
      }
      else if(args.get(0).toUpperCase().equals("DESTROY")){
         
         Command_Destroy insert = new Command_Destroy(args, log);
      }
      else if(args.get(0).toUpperCase().equals("PRINT")){
         
         Command_Print insert = new Command_Print(args, log);
      }
      
      else if(args.get(0).toUpperCase().equals("INTEGRITY_CONSTRAINT")){
         
         Command_Integrity_Constraint insert = new Command_Integrity_Constraint(args, log);
      }
      
      else if(args.get(0).toUpperCase().equals("DELETE")){
         
         Command_Delete delete = new Command_Delete(args, log);
      }
      if(args.size() < 3){
         return;
      }
      
      
      else if(args.get(1).toUpperCase().equals("=") && args.get(2).toUpperCase().equals("PROJECT")){
         
         Command_Project project = new Command_Project(args, log);
      }
      
      else if(args.get(1).toUpperCase().equals("=") && args.get(2).toUpperCase().equals("SELECT")){
         Command_Select select = new Command_Select(args, log);
      }
      
      else if(args.get(1).toUpperCase().equals("=") && args.get(2).toUpperCase().equals("JOIN")){
         Command_Join join = new Command_Join(args, log);
      }
   
   }





}