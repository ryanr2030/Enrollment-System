import java.util.*;
import java.lang.*;
public class Student<Integer>{
   private String name;
   private double age;
   private Integer sID;
   private char gender;
   private String address;   
   private static int size=0;
   private StudentNode<Integer> head, tail=null;   
   
   public Student(){

   }
   
 //create error checking methods
 //Add a student to the end of the list
   public void add(Integer sID, String name, double age, char gender, String address) {
    StudentNode<Integer> newNode = new StudentNode<>(sID, name, age, gender, address); // Create a new for sID
    if (tail == null) {
      head = tail = newNode; //first node added
    }
    else {
      tail.next = newNode; // Link the new with the last node
      tail = newNode; // tail points to the last node
    }

    size++; // Increase size
  }
  
  //Locates the Student the user would like to change. Prompts the user for what information they would like to change
  public boolean update(Integer iD){
      StudentNode<Integer> current=head;
      while(current!=null){
         if (current.sID.equals(iD)){
            Scanner input= new Scanner(System.in);
            char finished='N';
            
            //ADD EXCEPTION TO CATCH IF THESE ARE OUTSIDE OF THE INPUT PARAMETERS
               System.out.print("Would you like to change Name, Age, Gender, or Address for Student "+iD+"?");
               String value=input.nextLine();
               value=value.toUpperCase();
               
               if(value.equals("AGE")){
                  System.out.print("Enter the new age: ");
                  current.age=input.nextInt();

               }

              else if(value.equals("NAME") ||value.equals("STUDENT NAME")){
                  System.out.print("Enter new name: ");
                  current.name=input.nextLine();
               }
               
              else if(value.equals("GENDER")){
                  System.out.print("Enter new gender: ");
                  current.gender=input.next().charAt(0);

               }
              else if(value.equals("ADDRESS")){
                  System.out.print("Enter new address: ");
                  current.address=input.nextLine();

               }
              else{
               System.out.println("Invalid input.");

              }
             return true;
           }
           else{
            current=current.next;
           }
      }
      return false;         
  
  }
  public Integer remove(Integer key) {   
      if(size<0){
         return null;
      }
      else if(head.sID.equals(key)){
         removeFirst();
      }
      else if(tail.sID.equals(key)){
         removeLast();
      }
      else{
         StudentNode<Integer> previous = head;
         while(previous!=null){
            if (previous.next.sID.equals(key)){
                     StudentNode<Integer> current = previous.next;
                     previous.next = current.next;
                     size--;
                     return current.sID;
            }
         }
      }
      return null;
    }

  
    public Integer removeFirst() {
    if (size == 0) {
      return null;
    }
    else {
      Integer temp = head.sID;
      head = head.next;
      size--;
      if (head == null) {
        tail = null;
      }
      return temp;
    }
  }
  
  public Integer removeLast() {
    if (size == 0) {
      return null;
    }
    else if (size == 1) {
      Integer temp = head.sID;
      head = tail = null;
      size = 0;
      return temp;
    }
    else {
      StudentNode<Integer> current = head;

      for (int i = 0; i < size - 2; i++) {
        current = current.next;
      }

      Integer temp = tail.sID;
      tail = current;
      tail.next = null;
      size--;
      return temp;
    }
  }  
 //Method to clear the list
  public void clear() {
    size = 0;
    head = tail = null;
  }
   
 public void print(){
   StudentNode<Integer> current=head;
   while(current!=null){
      System.out.print("\nSTUDENT ID#:"+current.sID+"\nNAME:"+current.name+"\nAGE:"+current.age+"\nGENDER:"+current.gender+"\nADDRESS:"+current.address+"\n");
      current=current.next;
   }
 }
 
 public void printStudent(Integer iD){
   StudentNode<Integer> current=head;
   while(current!=null){
      if(current.sID.equals(iD)){
         System.out.print("\nSTUDENT ID#:"+current.sID+"\nNAME:"+current.name+"\nAGE:"+current.age+"\nGENDER:"+current.gender+"\nADDRESS:"+current.address+"\n");
      }
      current=current.next;
   }
 }  
   
  //ITTERATOR OVERIDES
  public java.util.Iterator<Integer> iterator() {
    return new StudentIterator();
  }  
   private class StudentIterator implements java.util.Iterator<Integer> {
    private StudentNode<Integer> current = head; // Current index 
    
    @Override
    public boolean hasNext() {
      return (current != null);
    }

    @Override
    public Integer next() {
      Integer sID = current.sID;
      String name=current.name;
      double age=current.age;
      char gender=current.gender;
      String address=current.address;
      current = current.next;
      return sID;
    }

  }  
   private static class StudentNode<Integer>{
      String name;
      double age;
      Integer sID;
      char gender;
      String address;
      StudentNode<Integer> next;

      public StudentNode(Integer sID, String name, double age, char gender, String address){
         this.sID=sID;
         this.name=name;
         this.age=age;
         this.gender=gender;
         this.address=address;
      }

   }

   

}