import java.util.*;
public class Schema{
   private String name;
   private ArrayList<String> dataTypes = new ArrayList<String>();
   private ArrayList<ArrayList<String>> constraints = new ArrayList<ArrayList<String>>();
   
   public Schema(ArrayList<String> data){
      this.name = data.get(1);
      for(int i = 2; i<data.size();i++){
         dataTypes.add(data.get(i));
      }
   }
   
   public String getName(){
      return this.name;
   }
   
   public ArrayList<String> getDataMask(){
      return dataTypes;
   }
   
   public String[] getData(){
      String[] get = new String[dataTypes.size()];
      for(int i = 0; i<dataTypes.size();i++){
         get[i] = dataTypes.get(i);
      }
      return get;
   
   }
   
   public void addConstraint(ArrayList<String> constraint){
      constraints.add(constraint);
   }
   
   public ArrayList<ArrayList<String>> getConstraints(){
      return constraints;
   }
   
   public String getRelationSchema(){
      String pattern = name;
      for(int i = 0; i<dataTypes.size();i++){
         pattern += " [" + dataTypes.get(i) + "] ";
      }
      return pattern;
   }
   
   public boolean isMatch(ArrayList<String> test){
      if(name.equals(test.get(1))){
         if(test.size() != dataTypes.size()+2){
            return false;
         }
         for(int i = 0; i<dataTypes.size();i++){
            if(!dataTypes.get(i).equals(test.get(i+2))){
               return false;
            }
         }
         return true;
      }
      else{
         return false;
      }
   }
   
   
   
   
}