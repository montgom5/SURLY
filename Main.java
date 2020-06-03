//Authors: Robert Mullarky and Michael Montgoemry
//File: Main.java
//Description: Driver class for HW1
//Date: 7/2/2018

//import statements
import java.util.*;

//Main Class declaration
public class Main{

   //Main method
   //Checks for command line argument representing
   //the file to be parsed and processed.  Errors out if
   //no argument found.
   public static void main(String args[]){
      FileProcessing OpenFile = new FileProcessing();
      if(args.length == 0){
         System.out.println("Error: Must enter file name to be processed");
         System.exit(1);
      }
      //Process the file
      OpenFile.processFile(args[0]);
   }
}
