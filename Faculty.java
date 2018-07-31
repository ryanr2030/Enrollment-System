import java.util.*;
import java.lang.*;
public class Faculty<Integer>{
   String name;
   double age;
   Integer fID;
   char gender;
   String address;
   String major;
   String degree;   
   static int size=0;
   final static int capacity=8;
   private FacultyNode<Integer> head, tail=null;   
   
   public Faculty(){

   }
   
 //create error checking methods
 //Add a student to the end of the list
   public void add(Integer fID, String name, double age, char gender, String address, String major, String degree) {
    FacultyNode<Integer> newNode = new FacultyNode<>(fID, name, age, gender, address, major, degree); // Create a new for fID
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
      FacultyNode<Integer> current=head;
      while(current!=null){
         if (current.fID.equals(iD)){
            Scanner input= new Scanner(System.in);
            char finished='N';
            
            //ADD EXCEPTION TO CATCH IF THESE ARE OUTSIDE OF THE INPUT PARAMETERS
               System.out.print("Would you like to change Faculty Name, age, gender, address, major, or degree for faculty "+iD+"?");
               String value=input.nextLine();
               value=value.toUpperCase();
               
               if(value.equals("AGE")){
                  System.out.print("Enter the new Age: ");
                  current.age=input.nextDouble();

               }
              else if(value.equals("NAME")||value.equals("FACULTY NAME")){
                  System.out.print("Enter new faculty name: ");
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
              else if(value.equals("MAJOR")){
                  System.out.print("Enter new Major: ");
                  current.major=input.nextLine();
              }
              else if(value.equals("DEGREE")){
                  System.out.print("Enter new Degree: ");
                  current.degree=input.nextLine();
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
      else if(head.fID.equals(key)){
         removeFirst();
      }
      else if(tail.fID.equals(key)){
         removeLast();
      }
      else{
         FacultyNode<Integer> previous = head;
         while(previous!=null){
            if (previous.next.fID.equals(key)){
                     FacultyNode<Integer> current = previous.next;
                     previous.next = current.next;
                     size--;
                     return current.fID;
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
      Integer temp = head.fID;
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
      Integer temp = head.fID;
      head = tail = null;
      size = 0;
      return temp;
    }
    else {
      FacultyNode<Integer> current = head;

      for (int i = 0; i < size - 2; i++) {
        current = current.next;
      }

      Integer temp = tail.fID;
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
   FacultyNode<Integer> current=head;
   while(current!=null){
      System.out.print("\nFACULTY ID#:"+current.fID+"\nNAME:"+current.name+"\nGENDER:"+current.gender+"\nAGE:"+current.age+"\nADDRESS:"+current.address+
      "\nDEGREE HELD:"+current.degree+"\nMAJOR:"+current.major+"\n");
      current=current.next;
   }
 }
 public void printFaculty(Integer iD){
   FacultyNode<Integer> current=head;
   while(current!=null){
      if(current.fID.equals(iD)){
         System.out.print("\n\tFACULTY ID#:"+current.fID+"\n\tNAME:"+current.name+"\n\tGENDER:"+current.gender+"\n\tAGE:"+current.age+"\n\tADDRESS:"+current.address+
         "\n\tDEGREE HELD:"+current.degree+"\n\tMAJOR:"+current.major+"\n");
      }
      current=current.next;
   }
 }
   
  //ITTERATOR OVERIDES
  public java.util.Iterator<Integer> iterator() {
    return new FacultyIterator();
  }  
   private class FacultyIterator implements java.util.Iterator<Integer> {
    private FacultyNode<Integer> current = head; // Current index 
    
    @Override
    public boolean hasNext() {
      return (current != null);
    }

    @Override
    public Integer next() {
         String name=current.name;
         double age=current.age;
         Integer fID=current.fID;
         char gender=current.gender;
         String address=current.address;
         String major=current.major;
         String degree=current.degree;
      return fID;
    }


  }  
   private static class FacultyNode<Integer>{
       private String name;
       private double age;
       private Integer fID;
       private char gender;
       private String address;
       private String major;
       private String degree;
      
      FacultyNode<Integer> next;

      public FacultyNode(Integer fID, String name, double age, char gender, String address, String major, String degree){
         this.name=name;
         this.age=age;
         this.fID=fID;
         this.gender=gender;
         this.address=address;
         this.major=major;
         this.degree=degree;
      }

   }

   

}