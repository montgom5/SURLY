/*----------------------------------------------------- 
 * Authors: Michael Montgomery and Robert Mullarky
 * File: Tuple.java
 * Description: Storage structure for data storage
 * Date: 7/19/2018
 *----------------------------------------------------*/
 
//Import statements
import java.util.ArrayList;

//Class declaration
public class Tuple{

   //Variables
   Attributes attributeHead;
   Tuple next;
         
   //Tuple constructor
   public Tuple(){
      this.next = null;
   }
   
   //addAttribute method
   //Takes an arraylist of strings representing attributes and 
   //creates a new attribute object for each and appending them to a
   //linked list
   public void addAttribute(ArrayList<String> attributes){
      for(int i = 2; i < attributes.size(); i++){ 
         if(attributeHead == null){
            this.attributeHead = new Attributes(attributes.get(i));
         }else{
            Attributes a = this.attributeHead;
            while(a.next != null){
               a = a.next;
            }
            a.next = new Attributes(attributes.get(i));
         }
      }
   }
}
