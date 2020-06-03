import java.util.*;
public class Where_Processer{
   
   public Where_Processer(){
      
   
   }
   public ArrayList<String> process(ArrayList<String> args){
      String[] argList = new String[(args.size()/4)+1];
      int count = 0;
      for(int i=0;i<args.size();i= i+4){
         try{
            argList[count] = args.get(i) + " " + args.get(i+2) + " " + args.get(i+1);
         }
         catch (Exception e){
            return null;
         }
         if(i+3 < args.size()){
            argList[count] += " " + args.get(i+3);
         }
         count++;
      }
      ArrayList<String> result = new ArrayList<String>();
      
      for(int i=0;i<argList.length;i++){
         if(argList[i] != null){
            result.add(argList[i]);
         }
      }
   
   
      return result;
   
   }
   
   









}