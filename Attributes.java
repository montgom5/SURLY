/*----------------------------------------------------- 
 * Authors: Michael Montgomery and Robert Mullarky
 * File: Attributes.java
 * Description: Storage structure for data storage
 * Date: 7/19/2018
 *----------------------------------------------------*/

//Class declaration
public class Attributes{
   
   //Variables
   Attributes next;
   String value;
   //Attributes constructor
   //Each object saves 1 value for later use
   public Attributes(String attribute){
      this.next = null;
      this.value = attribute;
   } 
}