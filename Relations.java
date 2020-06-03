/*----------------------------------------------------- 
 * Authors: Michael Montgomery and Robert Mullarky
 * File: Relations.java
 * Description: Storage structure for data storage
 * Date: 7/19/2018
 *----------------------------------------------------*/
 
 //Import statements
import java.util.ArrayList;

//Class declaration
public class Relations{

   //Variables
   Tuple tupleHead;
   Relations next;
   String name;
   String[] cypher;
   
   //Relations constructor
   public Relations(){
      this.next = null;
   }
   
   //removeTuple method
   //reroutes pointers and drops designated tuple from database
   public void removeTuple(Tuple tuple){
      Tuple t = tupleHead;
      Tuple prev = null;
      while(t != null){
         if(t.attributeHead.value.equals(tuple.attributeHead.value)){
            Attributes a1 = t.attributeHead;
            Attributes a2 = tuple.attributeHead;
            while(a1.value.equals(a2.value)){
               a1 = a1.next;
               a2 = a2.next;
               //tuple found, parsed to end of tuples
               //reroute pointers to destroy tuple
               if(a1 == null && a2 == null){
                  if(prev != null && t.next != null){       //prev != null, t.next != null
                     Tuple temp = t;
                     Tuple child = t.next;
                     Tuple parent = prev;
                     
                     prev.next = child;
                     temp.next = null;
                  }else if(prev != null && t.next == null){ //prev != null, t.next == null
                     Tuple temp = t;
                     Tuple parent = prev;
                     
                     parent.next = null;
                     temp.next = null;
                  }else if(prev == null && t.next != null){ //prev == null, t.next != null
                     Tuple temp = t;
                     Tuple child = t.next;
                     
                     tupleHead = child;
                     temp.next = null;
                  }else{                                    //prev == null, t.next == null
                     t = null;
                  }
               }
            }
         }
         prev = t;
         t = t.next;
      }
      return;
   }
   
   //addTuple method
   //finds the end of the linked list of tuples related to this relation
   //and appends a new tuple to the end of the linked list of tuples
   public void addTuple(ArrayList<String> tuple){
      if(tupleHead == null){
         this.tupleHead = new Tuple();
         this.tupleHead.addAttribute(tuple);
      }else{
         Tuple t = tupleHead;
         Tuple prev = null;
         while(t != null){
            Attributes checkDuplicate = t.attributeHead;
            int j = 2;
            while(checkDuplicate.value.equals(tuple.get(j))){
               checkDuplicate = checkDuplicate.next;
               j++;
               if(checkDuplicate == null){
                  System.out.println("Cannot INSERT, Duplicate detected!");
                  return;
                  }
               }
            prev = t;
            t = t.next;
         }
         
         t = new Tuple();
         prev.next = t;
         t.addAttribute(tuple);
      }
      return;
   }
}
