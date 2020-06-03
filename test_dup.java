import java.util.*;
public class test_dup{

   public static void main(String args[]){
      Tuple ToCopy = new Tuple();
      ArrayList<String> command = new ArrayList<String>();
      command.add("Would be a command fill this with whatever");
      command.add("same as the first fill this with whatever");
      command.add("7");
      command.add("8");
      command.add("hello");
      command.add("im the fourth");
      command.add("2345");
      String[] cypher = {"number 1", "number 2", "number 3", "number 4", "number 5"};
      ToCopy.addAttribute(command);
      // All of the above is just in order to create a tuble to be tested.
      
      Dup_Tuple processer = new Dup_Tuple();
      System.out.println("---GET WHOLE TUPLE---");
      ArrayList<String> product = processer.duplicate(null, ToCopy, "T1");
      for(int i=0;i<product.size();i++){
         System.out.println(product.get(i));
      }
      
      //used to get specific attibutes used for project
      //also if it would be easier i can change it from a int[] array to
      //an arraylist wither will work
      int[] index = {0, 2};
      
      System.out.println("---ONLY GET THE FIRST AND THRID---");
      product = processer.duplicate(index, ToCopy, "T1");
      for(int i=0;i<product.size();i++){
         System.out.println(product.get(i));
      }
      
      //the I dont matter at the begining is there so database can process it correctly "usaly there is a command there
   }




}