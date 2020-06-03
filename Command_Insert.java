import java.util.*;

public class Command_Insert{

   public Command_Insert(ArrayList<String> args, Catalog log){
      String[] dataMask = log.getSchema(args.get(1));
      if(dataMask == null){
         System.out.println("false");
         return;
      }
      if(args.size()-2 != dataMask.length){
         System.out.println("false");
         return;
      }
      
      boolean goodInput = true;
      for(int i=0;i<dataMask.length;i++){
         goodInput = true;
         String value = args.get(i+2);
         String[] mask = dataMask[i].trim().split(" ");
         if(mask[1].equals("NUM")){
            try{
               Integer.parseInt(value);
            }catch (NumberFormatException e){
               i = dataMask.length;
               goodInput = false;
            }
         
         }
         int inputSize = Integer.parseInt(mask[2]);
         if(inputSize < value.length()){
            goodInput = false;
         }
      
      }
      if(goodInput == true){
         compute get = new compute();
         ArrayList<ArrayList<String>> constants = log.getConstraints(args.get(1));
         if(constants != null){
            for(int i=0;i<constants.size();i++){
               ArrayList<String> current = constants.get(i);
               for(int k=0;k<current.size();k++){
                  String[] statement = current.get(k).split(" ");
                  int index = -1;
                  for(int h=0;h<dataMask.length;h++){
                     if(dataMask[h].toUpperCase().contains(statement[0].toUpperCase())){
                        index = h;
                        h=dataMask.length;
                     }
                  }
                  boolean result = get.compare(statement[2], args.get(index+2), statement[1]);
                  if(result == true){
                     goodInput = false;
                  }
                  
                        
               
               
               
               
               //needs checking for ands and ors
               
               
               
               
               }
            }
         }
      }
      
      
      
      if(goodInput == true){
         Database d = Database.getDatabase();
         if(log.getSchema(args.get(1))!= null){
            d.addTuple(args);
            System.out.println("Inserting "+(args.size()-2)+" attibutes to "+ args.get(1));
         }
      }
   }
}