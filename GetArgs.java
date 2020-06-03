//Authors: Robert Mullarky and Michael Montgoemry
//File: GetArgs.java
//Description: Input file command handler
//Date: 7/2/2018

//import statements
import java.util.*;

//GetArgs class declaration
public class GetArgs{

   //instance variables
   ArrayList<String> args;
   ArrayList<String> command;

   //myFunc method.
   //takes string arraylist as argument
   //formats and trims input commands from file processing
   //passes input commands to methods for correct formatting type
   public void myFunc(ArrayList<String> get){
      Catalog log = Catalog.getCatalog();
      Command_Processing process = new Command_Processing();
      this.command = get;
      int index = 0;
      args = new ArrayList<String>();
      String currArg = "";
      String current = command.get(0);      //assumes it will never be handed an empty array
      
      //Checks input commands for possible furthur processing and handles special characters: 
      // '/*' or '*/'
      // '(' or ')'
      // '"'
      // ','
      // '='
      // '<' or '>'
      while(index != current.length()){
         if(current.charAt(index) == '/' && current.charAt(index+1) == '*'){
            index = index +2;
            while(current.charAt(index) != '*'){
               index++;
            }
            index = index + 2;
         }
         else if(current.charAt(index) == '('){
            index++;
            index = parenthesis(index, current);
            index = current.length();
         }
         
         else if(current.charAt(index) == '"' || current.charAt(index) == '\''){
            index++;
            index = quote(index, current);
         }
         
         else if(current.charAt(index) == ','){
            index++;
         }
         else if(current.charAt(index) == '<' || current.charAt(index) == '>' || current.charAt(index) == '!'){
            if(currArg != ""){
               args.add(currArg);
            }
            currArg = "";
            if(index != current.length()-1 && current.charAt(index+1) == '='){
               args.add(currArg + current.charAt(index)+ "=");
               index++;
            }else{
               args.add(currArg + current.charAt(index));
            }
            currArg= "";
            index++;
         }
         else if(current.charAt(index) == ' '){
            if (current.charAt(index-1) == ' ' || current.charAt(index-1) == '/'){
               index++;
            }
            else{
               if(currArg != ""){
                  args.add(currArg);
               }
               currArg = "";
               index++;
            }
         }
         else if(current.charAt(index) == ';'){
            if(current.charAt(index-1) != ' '){
               args.add(currArg);
            }
            index = current.length();
         }
         else{
            currArg = currArg + current.charAt(index);
            index++;
         }
         
      }
      
      process.GiveArgument(args, log);
      
      
      
      
      /*current = command.get(0);
      if(!args.get(0).toUpperCase().equals("RELATION") && !args.get(0).toUpperCase().equals("INSERT") && !args.get(0).toUpperCase().equals("PRINT") && !args.get(0).toUpperCase().equals("DESTROY")){
         System.out.println("No command given");
         return;
      } */
      
     
   }
   
   private int quote(int index, String current){
      String currArg = "";
      while(index != current.length()){
         if(current.charAt(index) == '"' || current.charAt(index) == '\''){
            args.add(currArg);
            index = index + 2;
            return index;
         }
         else{
            currArg = currArg + current.charAt(index);
            index++;
         }
      
      }
      System.out.println("no ending quote");
      System.exit(1);
      return 0;
   }
   
   
   
   
   private int parenthesis(int index, String current){
      String currArg = "";
      int line = 0;
      while(index != current.length()){
         if(current.charAt(index) == ','){
            args.add(currArg);
            currArg = "";
            index++;
         }
         else if(current.charAt(index) == ')'){
            if(currArg != ""){
               args.add(currArg);
            }
            index++;
            return index;
         }
         else if(current.charAt(index) == '*' && current.charAt(index - 1) == '/'){
            index++;
            while(current.charAt(index) != '*'){
               index++;
            }
            index += 2;
         }
         else{
            if(index == 0){
               if(current.charAt(index) == ' '){
                  index++;
               }
               else{
                  currArg = currArg + current.charAt(index);
                  index++;
               }
            }
            
            else if(current.charAt(index) != ' ' || current.charAt(index-1) != ' '){
               if(current.charAt(index) != '/'){
                  currArg = currArg + current.charAt(index);
               }
               index++;
            }
            else{
               index++;
            }
            
            
            
         }
         
         
         if(index == current.length()){
            if(line == command.size()-1){
               System.out.println("no ending parenthesis");
               System.exit(1);
            }
            else{
               if(current.charAt(index-1) != '/' && current.charAt(index-1) != ','){
                  args.add(currArg);
               }
               currArg = "";
               line++;
               index = 0;
               current = command.get(line);
            }
         }
      }
      System.out.println("no ending parenthesis");
      System.exit(1);
      return 0;
   }
}