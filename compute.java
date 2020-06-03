public class compute{
   
   public compute(){
   }

   public boolean compare(String command, String value1, String value2){
      if(command.equals("=")){
         if(value1.equals(value2)){
            return true;
         }
         return false;
      }
      
      
      else if(command.equals("!=")){
         if(!value1.equals(value2)){
            return true;
         }
         return false;
      }
      
      else if(command.equals(">") || command.equals(">=")){
         int val1;
         int val2;
         try{
            val1 = Integer.parseInt(value1);
            val2 = Integer.parseInt(value2);
         }catch (NumberFormatException e){
            return false;
         }
         if(val1 > val2){
            return true;
         }
         else if(val1 == val2 && command.equals(">=")){
            return true;
         }
         return false;
      }
      
      else if(command.equals("<") || command.equals("<=")){
         int val1;
         int val2;
         try{
            val1 = Integer.parseInt(value1);
            val2 = Integer.parseInt(value2);
         }catch (NumberFormatException e){
            return false;
         }
         if(val1 < val2){
            return true;
         }
         else if(val1 == val2 && command.equals("<=")){
            return true;
         }
         return false;
      }
   
   
      return false;
   }







}