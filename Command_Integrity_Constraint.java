import java.util.*;
public class Command_Integrity_Constraint{

   public Command_Integrity_Constraint(ArrayList<String> args, Catalog log){
      
      if(args.size() < 4){
         return;
      }
      Where_Processer get = new Where_Processer();
      String[] cond = args.get(3).split(" ");
      ArrayList<String> command = new ArrayList<String>();
      for(int i=0;i<cond.length;i++){
         command.add(cond[i]);
      }
      command = get.process(command);
      if(command == null){
         return;
      }
      log.addConstraint(command, args.get(1));
      
   }



}