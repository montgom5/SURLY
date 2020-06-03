//Authors: Robert Mullarky and Michael Montgoemry
//File: FileProcessing.java
//Description: File parser for input file
//Date: 7/2/2018

//import statements
import java.io.*;
import java.util.*;
import java.lang.StringBuffer;

//FileProcessing class declaration
public class FileProcessing{

   //processFile method
   //Takes a fileName string as an argument
   //Parses file ignoring comments and passes data to 
   //getArgs class for handling
   public void processFile(String file){
      //try/catch for IOExceptions
      try{
         GetArgs processArgs = new GetArgs();
         Scanner scan = new Scanner(new File(file));
         String input = "";
         boolean inComment = false;
         ArrayList<String> command = new ArrayList<String>();
         
         //While not at end of file...
         while(scan.hasNextLine()){
            input = scan.nextLine();
            
            //Check for semicolon indicating end of command
            //If found, pass full command to getArgs class for handling
            if(input.contains(";")){
               command.add(input);                  
               processArgs.myFunc(command);
               command.clear();
               
               //Check for comment characters
               //if found, check if whole line is comment
               //If whole line is not comment, store
            }else{
               if(input.length() > 1){
                  if(input.charAt(0) == '/' && input.charAt(1) == '*'){
                     inComment = true;
                  }else if(input.charAt(input.length()-1) == '/' && input.charAt(input.length()-2) == '*' && inComment == true){
                     inComment = false;
                  }
                  else{
                     if(inComment != true){
                        command.add(input);
                     }
                  }
               }
            }
         }
      //Exception Handling
      }catch(IOException i){
         System.out.println(i + " caused by " + i.getCause());
      }
   }
}