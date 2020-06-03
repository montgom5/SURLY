import java.util.*;
public class Command_Relation{

   public Command_Relation(ArrayList<String> args, Catalog log){
      boolean goodSchema = true;
      String[] names = new String[args.size()-2];
      for(int i=2;i<args.size();i++){
         String[] mask = args.get(i).trim().split(" ");
         names[i-2] = mask[0].trim();
         if(mask.length != 3){
            goodSchema = false;
            i = args.size();
         }
         if(goodSchema == true){
            try{
               Integer.parseInt(mask[2]);
            }catch (NumberFormatException e){
               goodSchema = false;
               i = args.size();
            }
         }
         
         
      }
      
      if(goodSchema == true && log.getSchema(args.get(1)) == null){
         Database d = Database.getDatabase();
         d.createRelation(args.get(1), names);
         log.addItem(args);
         System.out.println("Creating "+args.get(1)+" with "+(args.size()-2)+" attibutes"); 
      }else{
         System.out.println("bad input or dubplicate");
      }
      
      
      
      
      
   }



}