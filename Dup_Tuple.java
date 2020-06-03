import java.util.*;
public class Dup_Tuple{
   
   public Dup_Tuple(){
   }

   public ArrayList<String> duplicate(Integer[] index, Tuple ToBeCopied, String relation){
      ArrayList<String> result = new ArrayList<String>();
      result.add("I dont matter");
      result.add(relation);
      Attributes current = ToBeCopied.attributeHead;
      if(index == null){
         while(current != null){
            result.add(current.value);
            current = current.next;
         }
      }
      else{
         int count = 0;
         while(current != null){
            boolean inArray = false;
            for(int i=0;i<index.length;i++){
               if(index[i] == count){
                  inArray = true;
                  i=index.length;
               }
            }
            if(inArray == true){
               result.add(current.value);
            }
            count++;
            current = current.next;
         }
      }
      return result;
   }
}