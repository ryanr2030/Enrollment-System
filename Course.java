import java.util.*;
import java.lang.*;
public class Course<Integer>{
   private String name;
   private double credits, year;
   private Integer cID;
   private char gender;
   private String semester;   
   private static int size=0;
   final static int capacity=6;
   private CourseNode<Integer> head, tail=null;   
   
   public Course(){

   }
   
 //create error checking methods
 //Add a student to the end of the list
   public void add(Integer cID, String name, double credits, double year, String semester) {
 
       CourseNode<Integer> newNode = new CourseNode<>(cID, name, credits, year, semester); // Create a new for cID
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
      CourseNode<Integer> current=head;
      while(current!=null){
         if (current.cID.equals(iD)){
            Scanner input= new Scanner(System.in);
            char finished='N';
            
            //ADD EXCEPTION TO CATCH IF THESE ARE OUTSIDE OF THE INPUT PARAMETERS
               System.out.print("Would you like to change Course Name, Number of Credits, Year, or Semester for Course "+iD+"?");
               String value=input.nextLine();
               value=value.toUpperCase();
               
               if(value.equals("NUMBER OF CREDITS")){
                  System.out.print("Enter the new number of Credits: ");
                  current.credits=input.nextDouble();

               }
              else if(value.equals("COURSE NAME")||value.equals("NAME")){
                  System.out.print("Enter new course name: ");
                  current.name=input.nextLine();
               }
              else if(value.equals("YEAR")){
                  System.out.print("Enter new YEAR: ");
                  current.year=input.nextDouble();

               }
              else if(value.equals("SEMESTER")){
                  System.out.print("Enter new semester: ");
                  current.semester=input.nextLine();

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
      else if(head.cID.equals(key)){
         removeFirst();
      }
      else if(tail.cID.equals(key)){
         removeLast();
      }
      else{
         CourseNode<Integer> previous = head;
         while(previous!=null){
            if (previous.next.cID.equals(key)){
                     CourseNode<Integer> current = previous.next;
                     previous.next = current.next;
                     size--;
                     return current.cID;
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
      Integer temp = head.cID;
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
      Integer temp = head.cID;
      head = tail = null;
      size = 0;
      return temp;
    }
    else {
      CourseNode<Integer> current = head;

      for (int i = 0; i < size - 2; i++) {
        current = current.next;
      }

      Integer temp = tail.cID;
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
   CourseNode<Integer> current=head;
   while(current!=null){
      System.out.print("\nCOURSE ID#:"+current.cID+"\nNAME:"+current.name+"\nCREDITS:"+current.credits+"\nSEMESTER:"+current.semester+"\nYEAR:"+current.year+"\n");
      current=current.next;
   }
 }
 
 public void printCourse(Integer iD){
   CourseNode<Integer> current=head;
   while(current!=null){
      if(current.cID.equals(iD)){
         System.out.print("\n\tCOURSE ID#:"+current.cID+"\n\tNAME:"+current.name+"\n\tCREDITS:"+current.credits+"\n\tSEMESTER:"+current.semester+"\n\tYEAR:"+current.year+"\n");
      
      }
      current=current.next;
   }
 }   
  //ITTERATOR OVERIDES
  private java.util.Iterator<Integer> iterator() {
    return new CourseIterator();
  }  
   private class CourseIterator implements java.util.Iterator<Integer> {
    private CourseNode<Integer> current = head; // Current index 
    
    @Override
    public boolean hasNext() {
      return (current != null);
    }

    @Override
    public Integer next() {
      Integer cID = current.cID;
      String name=current.name;
      double year =current.year;
      double credits=current.credits;
      String semester=current.semester;
      current = current.next;
      return cID;
    }


  }  
   private static class CourseNode<Integer>{
      private String name;
      private double year;
      private Integer cID;
      private double credits;
      private String semester;
      
      CourseNode<Integer> next;
      public CourseNode(Integer cID, String name, double credits, double year, String semester){
         this.cID=cID;
         this.name=name;
         this.year=year;
         this.credits=credits;
         this.semester=semester;
      }

   }

   

}