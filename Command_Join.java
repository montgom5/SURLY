import java.util.*;
public class Command_Join{
   
   public Command_Join(ArrayList<String> args, Catalog log){
      String Newrelation = args.get(0);
      Database d = Database.getDatabase();
      if(log.getSchema(Newrelation) != null){
         return;
      }
      d.destroyRelation(Newrelation);
      if(args.size() < 9){
         return;
      }
      String Lrelation = args.get(3);
      String Rrelation = args.get(4);
      Relations LeftRelation = d.getRelation(Lrelation);
      Relations RightRelation = d.getRelation(Rrelation);
      
      if(LeftRelation == null || RightRelation == null){
         return;
      }
      
      if(LeftRelation == null || RightRelation == null){
         return;
      }
      Where_Processer get = new Where_Processer();
      for(int i=0;i< 6;i++){
         args.remove(0);
      }
      compute process = new compute();
      ArrayList<String> command = get.process(args);
      String[] Leftcypher = LeftRelation.cypher;
      String[] Rightcypher = RightRelation.cypher;
      int LeftIndex = -1;
      int RightIndex = -1;
      
      String[] Newcypher = new String[Leftcypher.length + Rightcypher.length];
      for(int i=0;i<Leftcypher.length;i++){
         Newcypher[i] = Leftcypher[i];
      }
      for(int i=0;i<Rightcypher.length;i++){
         boolean InLeft = false;
         for(int j=0;j<Leftcypher.length;j++){
            if(Leftcypher[j].toUpperCase().equals(Rightcypher[i].toUpperCase())){
               InLeft = true;
            }
         }
         if(InLeft){
            Newcypher[i+Leftcypher.length] = Rrelation + "." + Rightcypher[i];
         }
         else{
            Newcypher[i+Leftcypher.length] = Rightcypher[i];
         }
      }
      String[] cypher = command.get(0).split(" ");
      for(int i=0;i<Leftcypher.length;i++){
         if(cypher[0].toUpperCase().equals(Leftcypher[i].toUpperCase())){
            LeftIndex = i;
         }
      }
      for(int i=0;i<Rightcypher.length;i++){
         if(cypher[1].toUpperCase().contains(Rightcypher[i].toUpperCase())){
            RightIndex = i;
         }
      }
      if(LeftIndex == -1 || RightIndex == -1){
         return;
      }
      Relations newRelation = new Relations();
      newRelation.name = Newrelation;
      newRelation.cypher = Newcypher;
      Relations head = d.relationHead;
      while(head.next != null){
         head = head.next;
      }
      head.next = newRelation;
      Tuple LeftHead = LeftRelation.tupleHead;
      Dup_Tuple dup = new Dup_Tuple();
      while(LeftHead != null){
         int count = 0;
         Attributes LeftAttributes = LeftHead.attributeHead;
         while(count != LeftIndex){
            LeftAttributes = LeftAttributes.next;
            count++;
         }
         Tuple RightHead = RightRelation.tupleHead;
         while(RightHead != null){
            count = 0;
            Attributes RightAttributes = RightHead.attributeHead;
            while(count != RightIndex){
               RightAttributes = RightAttributes.next;
               count++;
            }
            boolean truthvalue = process.compare(cypher[2], LeftAttributes.value, RightAttributes.value);
            if(truthvalue == true){
               ArrayList<String> LeftTuple = dup.duplicate(null, LeftHead, Newrelation);
               ArrayList<String> RightTuple = dup.duplicate(null, RightHead, Newrelation);
               for(int i=2;i<RightTuple.size();i++){
                  LeftTuple.add(RightTuple.get(i));
               }
               newRelation.addTuple(LeftTuple);
            }
            truthvalue = false;
            RightHead = RightHead.next;
         }
         LeftHead = LeftHead.next;
      }
   
   
   }
   
   









}