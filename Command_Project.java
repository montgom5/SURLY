import java.util.*;
public class Command_Project{
   
   //T1 = project COURSE cnum class
   public Command_Project(ArrayList<String> args, Catalog log){
      String relation = args.get(0);
      if(log.getSchema(relation) != null){
         return;
      }
      
      Database d = Database.getDatabase();
      d.destroyRelation(args.get(0));
      Relations targetTable = d.getRelation(args.get(args.size()-1));
      if(targetTable == null){
         return;
      }
      ArrayList<Integer> indexs = new ArrayList<Integer>();
      
      if((args.get(1)).compareToIgnoreCase("=") != 0){
         System.out.println("Error: Command Usage <name> = <command> <table> <attributes...>");
      }
      args.remove(args.size()-1);
      args.remove(args.size()-1);
      ArrayList<String> cypherBuilder = new ArrayList<String>();
      String[] oldCypher = targetTable.cypher;
      for(int i = 3; i < args.size(); i++){
         boolean rela = false;
         for(int j=0;j<oldCypher.length;j++){
            if(oldCypher[j].toUpperCase().equals(args.get(i).toUpperCase())){
               rela = true;
            }
         }
         if(rela == true){
            cypherBuilder.add(args.get(i));
         }
         
      }
      
      String[] mycypher = cypherBuilder.toArray(new String[cypherBuilder.size()]);
      String[] targetCypher = targetTable.cypher;
      
      for(int i = 0; i < mycypher.length; i++){
         if(Arrays.asList(targetCypher).contains(mycypher[i])){
            indexs.add(Arrays.asList(targetCypher).indexOf(mycypher[i]));
         }
      }
      
      Integer[] index = indexs.toArray(new Integer[indexs.size()]);
      
      d.createRelation(args.get(0) ,mycypher);
   
      Dup_Tuple processer = new Dup_Tuple();
      ArrayList<String> temp;
      Tuple current = targetTable.tupleHead;
      Relations r = d.getRelation(args.get(0));
      
      while(current != null){
         temp = processer.duplicate(index, current, args.get(0));
         r.addTuple(temp);
         current = current.next;
      }
   }
}