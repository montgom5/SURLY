import java.util.*;

public class Catalog{

   private static Catalog catalog;
   private ArrayList<Schema> relations = new ArrayList<Schema>();

   private Catalog(){
   }
   
   public static Catalog getCatalog(){
      if(catalog != null){
         return catalog;
      }else{
         catalog = new Catalog();
         return catalog;
      } 
   }
   
   private boolean isItemInLog(ArrayList<String> test){
      for(int i = 0; i< relations.size(); i++){
         if(relations.get(i).isMatch(test)){
            return true;
         }
      }
      return false;
   }
   
   public void printSchema(){
      for(int i=0; i< relations.size();i++){
         System.out.println(relations.get(i).getRelationSchema());
      }
   
   }
  
   public void removeItem(String name){
      for(int i = 0;i<relations.size();i++){
         if(relations.get(i).getName().equals(name)){
            relations.remove(i);
            return;
         }
      
      }
   }
   
   public String[] getSchema(String name){
      for(int i = 0;i<relations.size();i++){
         if(relations.get(i).getName().equals(name)){
            return relations.get(i).getData();
         }
      
      }
      return null;
   }
   
   public boolean addItem(ArrayList<String> add){
      if(relations.size() == 0){
         relations.add(new Schema(add));
         return true;
      }
      else if(!isItemInLog(add)){
         relations.add(new Schema(add));
         return true;
      }
      else{
         System.out.println("relation is already schema");
         return false;
      }
   }
   
   public void addConstraint(ArrayList<String> constraint, String name){
      for(int i = 0; i< relations.size(); i++){
         if(relations.get(i).getName().toUpperCase().equals(name.toUpperCase())){
            relations.get(i).addConstraint(constraint);
            return;
         }
      }
   }
   
   public ArrayList<ArrayList<String>> getConstraints(String name){
      for(int i = 0; i< relations.size(); i++){
         if(relations.get(i).getName().toUpperCase().equals(name.toUpperCase())){
            return relations.get(i).getConstraints();
         
         
         }
      }
      return null;
   }
}