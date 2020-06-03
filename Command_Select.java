import java.util.*;
public class Command_Select{
   
   
   public Command_Select(ArrayList<String> args, Catalog log){
      String relation = args.get(3);
      Database d = Database.getDatabase();
      if(log.getSchema(args.get(0)) != null){
         return;
      }
      d.destroyRelation(args.get(0));
      if(args.size() < 8){
         return;
      }
      Relations targetRelation = d.getRelation(relation);
      if(targetRelation == null){
         return;
      }
      Where_Processer get = new Where_Processer();
      
      
      compute process = new compute();
      
      ArrayList<String> getWhere = new ArrayList<String>();
      for(int i=5;i<args.size();i++){
         getWhere.add(args.get(i));
      }
   
      ArrayList<String> command = get.process(getWhere);
      if(command == null){
         return;
      }
      String[] targetCypher = targetRelation.cypher;
         
      
      
      ArrayList<Integer> index = new ArrayList<Integer>();
      for(int i=0;i<command.size();i++){
         String[] commands = command.get(i).split(" ");
         for(int j=0;j<targetCypher.length;j++){
            if(targetCypher[j].toUpperCase().equals(commands[0])){
               index.add(j);
            }
         }
      }
   	
      String[] mycypher = targetCypher;
      Relations r = new Relations();      
      r.name = args.get(0);
      r.cypher = mycypher;
      
      
      Dup_Tuple processer = new Dup_Tuple();
   
      Tuple current = targetRelation.tupleHead;
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
                  if(targetCypher[count].toUpperCase().equals(commands[0])){
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
            ArrayList<String> temp = processer.duplicate(null, current, args.get(0));
            r.addTuple(temp);
            current = current.next;
         
         
           
         }else{
            current = current.next;
         }
      }
      
      Relations head = d.relationHead;
      while(head.next != null){
         head = head.next;
      }
      head.next = r;
   }
}
   
