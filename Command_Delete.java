import java.util.*;
public class Command_Delete{
   
   
   public Command_Delete(ArrayList<String> args, Catalog log){
      String relation = args.get(1);
      Database d = Database.getDatabase();
      
      if(args.size() == 2){
         Relations ToBeDeleted = d.getRelation(relation);
         if(ToBeDeleted == null){
            return;
         }
         ToBeDeleted.tupleHead = null;
         return;
      }
      
      if(args.size() < 6){
         return;
      }
      
      Relations ToBeDeleted = d.getRelation(relation);
      if(ToBeDeleted == null){
         return;
      }
      Where_Processer get = new Where_Processer();
      args.remove(0);
      args.remove(0);
      args.remove(0);
      compute process = new compute();
      ArrayList<String> command = get.process(args);
      if(command == null){
         return;
      }
      String[] mycypher = ToBeDeleted.cypher;
      
      //gets index for attibutes to be tested
      ArrayList<Integer> index = new ArrayList<Integer>();
      for(int i=0;i<command.size();i++){
         String[] commands = command.get(i).split(" ");
         for(int j=0;j<mycypher.length;j++){
            if(mycypher[j].toUpperCase().equals(commands[0])){
               index.add(j);
            }
         }
      }//
      
      Tuple current = ToBeDeleted.tupleHead;
      Tuple previous = null;
      while(current != null){
         boolean found = false;
         int count = 0;
         Attributes curr = current.attributeHead;
         String lastCommand = "";
         while(curr != null){
            if(index.contains(count)){
               for(int i=0;i<command.size();i++){
                  String[] commands = command.get(i).split(" ");
                  if(mycypher[count].toUpperCase().equals(commands[0])){
                     boolean truthValue = process.compare(commands[2], curr.value, commands[1]);
                     if(lastCommand.equals("AND")){
                        if(found == false){
                           truthValue = false;
                        }
                     }
                     else if(lastCommand.equals("OR")){
                        if(found == true){
                           truthValue = true;
                        }
                     } 
                     if(commands.length == 4){ 
                        lastCommand = commands[3].toUpperCase();
                     }
                     found = truthValue;
                  }
               }
               
            }
            curr = curr.next;
            count++;
         }
         
         if(found == true){
         //this is to deltete tuple
            if(previous == null){
               ToBeDeleted.tupleHead = current.next;
               current = ToBeDeleted.tupleHead;
            }
            else if(current.next == null){
               previous.next = null;
               current = null;
            }
            else{
               previous.next = current.next;
               current = previous.next;
            }
          //end of delete tuple  
         }
         
         else{
            previous = current;
            current = current.next;
         }
      
      
      }
   }
   
   









}