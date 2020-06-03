/*----------------------------------------------------- 
 * Authors: Michael Montgomery and Robert Mullarky
 * File: Database.java
 * Description: Storage structures for data storage
 * Date: 7/19/2018
 *----------------------------------------------------*/

//Import statements
import java.util.ArrayList;
import java.util.Arrays;

//Class declaration
public class Database{
   
   //Constants
   public static final int INDEX_NAME = 1;

   //Variables
   private static Database database;
   Relations relationHead;
   
   //Private constructor for singleton
   private Database(){
   
   }
   
   //getInstance method for singleton design pattern
   public static Database getDatabase(){
      if(database != null){
         return database;
      }else{
         database = new Database();
         return database;
      }
   }

   //addTuple method
   //Iterates through linked list of relations to find relaion to add tuple to
   //Iterates through args to create and add attributes to relation
   public void addTuple(ArrayList<String> args){
      Relations r = relationHead;
      //Error handling for improper add
      if(r == null){
         System.out.println("Error: No relations to add tuple to");
         return;
      }
      //Iterate to find correct relation
      while(r.next != null){
         if(r.name.equals(args.get(INDEX_NAME))){
            r.addTuple(args);
            return;
         }
         r = r.next;
      }
      
   }
   
   //CreateRelation method
   //Creates a relation opbect and adds it to linked list
   //Newly created relations are empty untill a tuple is added
   public void createRelation(String relation, String[] cypher){
      //Check for first relation added
      if(relationHead == null){
         this.relationHead = new Relations();
         this.relationHead.name = relation;
         this.relationHead.cypher = cypher;
      }else{
         Relations r = this.relationHead;
         while(r.next != null){
            r = r.next;
         }
         r.next = new Relations();
         r.next.name = relation;
         r.next.cypher = cypher;
      }
   }
   
   //DestroyRelation method
   //Remove a relation and associated data from database 
   //this is done by rearranging pointers in linked list with start of
   //linked list being relationHead
   public void destroyRelation(String relation){
      Relations r = this.relationHead;
      if(r.name.equals(relation)){
         relationHead = r.next;
         return;
      }
      
      while(r.next != null){
         if(r.next.name.equals(relation)){
            Relations temp = r.next;
            Relations parent = r;
            Relations child = r.next.next;
            
            parent.next = child;
            temp.next = null;
            return;
         }else{
            r = r.next;
         }
      }
      return;
   }
   
   
   
   //getRelation method
   //returns relation with name matching argument string
   public Relations getRelation(String name){
      Relations hold = relationHead;
      while(hold != null){ 
         if(hold.name.equals(name)){
            return hold;
         }
         hold = hold.next;
      }
      System.out.println("Error: Relation " + name + " not found in Database.");
      return null;
   
   }
   //PrintRelation method
   //Iterates through relations linked list to find correct relationship to print
   //Then iterate through each tuple and each tuple's attributes printing each
   public void printRelation(String relation){
      Relations r = this.relationHead;
      //Loop through relations
      while(r != null){
      
         //Find correct relation
         if(r.name.equals(relation)){
            System.out.println("PRINTING: " + r.name);
            Tuple t = r.tupleHead;
            String[] c = r.cypher;
            
            for(String s: c){
               System.out.print(String.format("%-30s", s));
            }
            System.out.println("");
            
            if(t == null){
               break;
            }
            //Loop through relation tuples            
            while(t != null){
               Attributes a = t.attributeHead;
               if(a == null){
                  break;
               }
               //Loop through and print each tuple's attributes
               while(a != null){
                  System.out.print(String.format("%-30s", a.value));
                  a = a.next;
               }
               t = t.next; 
               System.out.println(""); 
            } 
            return;      
         }else{
            r = r.next;
         }
      }
   }
}
