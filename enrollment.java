import java.util.*;
import java.io.*;
public class Enrollment{
       
       //data fields for the main class
       private static Integer iD=null;
       private static String name=" ";
       private static double age=0, credits=0, year=0;
       private static String address=" ", semester=" ", degree="", major="";
       private static char gender='N';
       private static int count=0;
   
   //Create an enrollment list using an array stored in an arrayList
   private static List<Integer[]> EnrollmentList = new ArrayList<Integer[]>();
   
   //create the Student, Course, and Faculty LinkedLists
   private static Student<Integer> StudentList=new Student<Integer>();
   private static Course<Integer> CourseList=new Course<Integer>();
   private static Faculty<Integer> FacultyList=new Faculty<Integer>();
  
  //Checks the credit capacity set this as a default of 6 for all classes. Could change it to have each valued associated with each individual class if its needed.
   private static boolean checkCapacity(Integer cID){
       int enrolled=0;
       for(Integer[] search: EnrollmentList){
         if(search[2].equals(cID)){
            enrolled++;
         }
       }
       if(enrolled<CourseList.capacity){
         return true;
       }
       return false;
   
   }
   
   private static void EnrollStudentCourse(Integer sID, Integer fID, Integer cID){
      boolean cap=checkCapacity(cID);
      
      if(cap){
         Integer[] enrolled = new Integer[3];
         enrolled[0]=sID;
         enrolled[1]=fID;
         enrolled[2]=cID;
         EnrollmentList.add(enrolled);
         }
      else{
         System.out.println("Course #"+cID+" is full.");
      }
   }
   
   //Drops a student from the selected course
   private static void DropStudentCourse(Integer sID,Integer cID){
      for(Integer[] search: EnrollmentList){
         if(search[0].equals(sID) && search[2].equals(cID)){
            search[0]=0;
            search[1]=0;
            search[2]=0;
         }
       }
   }
   
   //Assigns a Faculty member to a given course
   private static void AssignFacultyCourse(Integer fID, Integer cID){
      for(Integer[] search: EnrollmentList){
         if(search[2].equals(cID)){
            search[1]=fID;
         }
       }
   }
   
   //Removes the faculty from the given course
   private static void DropFacultyCourse(Integer fID, Integer cID){
     System.out.print("Would you like to assign a new faculty member to class "+cID+"? YES/NO: ");
     Scanner input=new Scanner(System.in);
     String a=input.next();
     a=a.toUpperCase();
     Integer n=0;
     boolean enter=false;
     if(a.equals("YES")){
        System.out.print("Enter new Faculty ID #: ");
        n = Integer.valueOf(input.nextInt());
        enter=true;
     }
     for(Integer[] search: EnrollmentList){
         if(search[1].equals(fID) && search[2].equals(cID)){
            if(enter){
               search[1]=n;
            }
            else{
               search[0]=0;
               search[1]=0;
               search[2]=0;
            }
            
         }
       }      
   
   }
   
   //ensure that the user changing the enrollment is the admin
   private static String adminPW="password";
   private static void SecurityCheck(){
      int attempts=0;
      Scanner j= new Scanner(System.in);
      System.out.print("Enter admin password: ");
      String pw=j.nextLine();
      while(!pw.equals(adminPW)){
         attempts++;
         if(attempts>8){
            System.out.println("Too many incorrect attempts. Goodbye.");
            System.exit(0);
         }
         System.out.println("Invalid Password.");
         System.out.print("Enter admin password: ");
         pw=j.nextLine();
        
      }
      
      
   }
   public static void main(String[] args){
    	 
       //makes sure the admin is running the program
       SecurityCheck();


      
      //POPULATE THE STUDENT LIST FROM THE FILE STUDENT.TXT
       try {
             Scanner s = new Scanner(new File("/Users/ryanreynolds/Documents/CSU Class Folders/Fall 2017/CIS 265 Data structure and algorithms/Project/Part 1/Student.txt"));

                 while(s.hasNextLine()) {
                  String u=s.nextLine();
                    if(u.contains("STUDENT ID#:")){
                     iD=Integer.valueOf(u.substring(12, u.length()));
                     count++;
                    }
                    if(u.contains("NAME:")){
                     name=u.substring(5, u.length());
                     count++;
                    }
                    if(u.contains("AGE:")){
                     age=Double.parseDouble(u.substring(4,u.length()));
                     count++;
                    }
                    if(u.contains("ADDRESS:")){
                      address=u.substring(8, u.length());
                      count++;
                    }
                    if(u.contains("GENDER:")){
                      gender=u.charAt(7);
                      count++;
                    }
                    if(count==5){
                     //checks to make sure each input meets the format requirments
                     studentLengthCheck();
                     StudentList.add(iD, name, age, gender, address);
                     count=0;
                    }
                 }
            
         } catch (IOException i) {
             System.out.println("Path does not exist check the file location of Student.txt");
         }

       //POPULATE THE COURSE LIST FROM THE FILE COURSE.TXT
       
       try {
             Scanner s = new Scanner(new File("/Users/ryanreynolds/Documents/CSU Class Folders/Fall 2017/CIS 265 Data structure and algorithms/Project/Part 1/Course.txt"));

                 while(s.hasNextLine()) {
                  String u=s.nextLine();
                  u=u.toUpperCase();
                    if(u.contains("COURSE ID#:")){
                     iD=Integer.valueOf(u.substring(11, u.length()));
                     count++;
                    }
                    if(u.contains("NAME:")){
                     name=u.substring(5, u.length());
                     count++;
                    }
                    if(u.contains("CREDITS:")){
                     credits=Double.parseDouble(u.substring(8,u.length()));
                     count++;
                    }
                    if(u.contains("YEAR:")){
                     year=Double.parseDouble(u.substring(5,u.length()));
                     count++;
                    }
                    if(u.contains("SEMESTER:")){
                     semester=u.substring(9, u.length());
                     count++;
                     }
                    if(count==5){
                     //checks to make sure each input meets the format requirments
                     courseLengthCheck();
                     CourseList.add(iD, name, credits, year, semester);
                     count=0;
                    }
                 }
            
         } catch (IOException i) {
             System.out.println("Path does not exist check the file location of Course.txt");
         } 
        //SAVE THE COURSE LIST
 
        //POPULATE THE FACULTY LIST. CHECKS FOR LENGTH ERRORS PROMPTS THE USER TO ENTER CORRECTED VALUES. THEN OVERWRITES THE FILE WITH CORRECTED VALUES.
       try {
             Scanner s = new Scanner(new File("/Users/ryanreynolds/Documents/CSU Class Folders/Fall 2017/CIS 265 Data structure and algorithms/Project/Part 1/Faculty.txt"));

                 while(s.hasNextLine()) {
                  String u=s.nextLine();
                    if(u.contains("FACULTY ID#:")){
                     iD=Integer.valueOf(u.substring(12, u.length()));
                     count++;
                    }
                    if(u.contains("NAME:")){
                     name=u.substring(5, u.length());
                     count++;
                    }
                    if(u.contains("AGE:")){
                     age=Double.parseDouble(u.substring(4,u.length()));
                     count++;
                    }
                    if(u.contains("ADDRESS:")){
                      address=u.substring(8, u.length());
                      count++;
                    }
                    if(u.contains("DEGREE HELD:")){
                      degree=u.substring(12, u.length());
                      count++;
                    }                    
                    if(u.contains("MAJOR:")){
                      major=u.substring(6, u.length());
                      count++;
                    }                    
                    if(u.contains("GENDER:")){
                      gender=u.charAt(7);
                      count++;
                    }
                    if(count==7){
                     //checks to make sure each input meets the format requirments
                     facultyLengthCheck();
                     FacultyList.add(iD, name, age, gender, address, major, degree);
                     count=0;
                    }
                 }
            
         } catch (IOException i) {
             System.out.println("Path does not exist check the file location of Faculty.txt");
         }
       //READS IN THE ENROLLMENT LIST
       try {
            Integer sID=0, fID=0, cID=0;
             Scanner s = new Scanner(new File("/Users/ryanreynolds/Documents/CSU Class Folders/Fall 2017/CIS 265 Data structure and algorithms/Project/Part 1/Enrollment.txt"));

                 while(s.hasNextLine()) {
                  String u=s.nextLine();
                  String[] parts=u.split(" ");
                  if(parts[0].equals("0")){
                     continue;
                  }
                  else{
                     sID=Integer.valueOf(parts[0]);
                     fID=Integer.valueOf(parts[1]);
                     cID=Integer.valueOf(parts[2]);
                     EnrollStudentCourse(sID, fID, cID);
                  }
                 }
            
         } catch (IOException i) {
             System.out.println("Path does not exist check the file location of Enrollment.txt");
         }


         //INSERT LIST MANIPULATION HERE
   
         printStudentsInCourse(2345884);
        
         DropStudentCourse(6969696, 2345884);

         printStudentsInCourse(2345884);
         
         
         StudentList.update(3245695);
         CourseList.update(2345884);
         FacultyList.update(3141592);
         
         DropFacultyCourse(1234567, 2345884);
         printStudentsInCourse(2345884);
       
       
       
       
       
       
       //SAVE THE FILES UPON EXIT  
       try{
           File file = new File("/Users/ryanreynolds/Documents/CSU Class Folders/Fall 2017/CIS 265 Data structure and algorithms/Project/Part 1/Faculty.txt");
           FileOutputStream fos = new FileOutputStream(file);
             // Create new print stream for file.
           PrintStream output = new PrintStream(fos);
             // Set file print stream.
           System.setOut(output);
           System.out.println("\nUniversity Faculty List:");        
           FacultyList.print();
        }catch (IOException i) {
             System.out.println("Path does not exist check the file location of Faculty.txt");
        }
       try{
           File file = new File("/Users/ryanreynolds/Documents/CSU Class Folders/Fall 2017/CIS 265 Data structure and algorithms/Project/Part 1/Course.txt");
           FileOutputStream fos = new FileOutputStream(file);
             // Create new print stream for file.
           PrintStream output = new PrintStream(fos);
             // Set file print stream.
           System.setOut(output);
           System.out.println("\nUniversity Course List:");        
           CourseList.print();
        }catch (IOException i) {
             System.out.println("Path does not exist check the file location of Course.txt");
         }
       try{
           File file = new File("/Users/ryanreynolds/Documents/CSU Class Folders/Fall 2017/CIS 265 Data structure and algorithms/Project/Part 1/Student.txt");
           FileOutputStream fos = new FileOutputStream(file);
             // Create new print stream for file.
           PrintStream output = new PrintStream(fos);
             // Set file print stream.
           System.setOut(output);
           System.out.println("\nUniversity Student List:");        
           StudentList.print();
        }catch (IOException i) {
             System.out.println("Path does not exist check the file location of Student.txt");
        }
       try{
           File file = new File("/Users/ryanreynolds/Documents/CSU Class Folders/Fall 2017/CIS 265 Data structure and algorithms/Project/Part 1/Enrollment.txt");
           FileOutputStream fos = new FileOutputStream(file);
             // Create new print stream for file.
           PrintStream output = new PrintStream(fos);
             // Set file print stream.
           System.setOut(output);
           printEnrollmentList();
        }catch (IOException i) {
             System.out.println("Path does not exist check the file location of Enrollment.txt");
        }        
                  
   }
//PRINT THE ENROLLMENT LIST
public static void printEnrollmentList(){
    for(Integer[] search: EnrollmentList){
      if(search[0]!=0 || search[1]!=0 || search[2]!=0){
         System.out.println(search[0]+" "+search[1]+" "+search[2]);
      }
    }
}
   
//PRINT ALL STUDENTS IN A COURSE
public static void printStudentsInCourse(Integer iD){
  System.out.println("_________________________________________________");
  CourseList.printCourse(iD);
  int count=0;
   for(Integer[] search: EnrollmentList){
       if(search[2].equals(iD)){
         if(count==0){
            FacultyList.printFaculty(search[1]);
            count++;
         }
         StudentList.printStudent(search[0]);
       }
    }   
 System.out.println("__________________________________________________");
}
//PRINT A STUDENT'S SCHEDULE
public static void printStudentSchedule(Integer iD){
   System.out.println("__________________________________________________");
   StudentList.printStudent(iD);
   for(Integer[] search: EnrollmentList){
      if(search[0].equals(iD)){
         CourseList.printCourse(search[2]);
      }
   }
   System.out.println("__________________________________________________");


}
//PRINT ALL CLASSES A PROFESSOR TEACHES
public static void printClassesToTeach(Integer iD){
   System.out.println("__________________________________________________");
   FacultyList.printFaculty(iD);
   for(Integer[] search: EnrollmentList){
       if(search[1].equals(iD)){
          CourseList.printCourse(search[2]);
        }
    }
   System.out.println("__________________________________________________");

}

//METHODS TO CHECK THE RESTRICTIONS ON THE INPUTS
   private static void facultyLengthCheck(){
      boolean valid=false;
      int length=0;
      Scanner input=new Scanner(System.in);
      while(!valid){
         if(String.valueOf(iD).length()>7){
            System.out.print("ID# is too long. Enter a new ID# for "+name+":");
            iD=Integer.valueOf(input.nextLine());
         }
         else if(String.valueOf(iD).length()<7){
            System.out.print("ID# is too short. Enter a new ID# for "+name+":");
            iD=Integer.valueOf(input.nextLine());
         }
         else if(address.length()>100){
            System.out.print("Address is too long for "+name+". Enter abreviated address: ");
            address=input.nextLine();
         }
         else if(!semester.equals("FALL") && !semester.equals("SPRING")&& !semester.equals("SUMMER")){
            System.out.print("Semester must be Fall, Spring, or Summer for "+name+". Enter new semester: ");
            semester=input.next();
         }
         else if(degree.length()>10){
            System.out.print("Degree name is too long for "+name+". Please enter an abreviated degree: ");
            degree=input.nextLine();
         }
         else if(major.length()>40){
            System.out.print("Major length is too long for "+name+". Please enter an abreviated major: ");
            major=input.nextLine();
         }
         else if(gender!='M'&& gender!='F'){
            System.out.print("Gender must be either Male(M) or Female(F) for "+name+". Please re-enter: ");
            gender=input.next().charAt(0);
         }
         else if(name.length()>60){
            System.out.print("Name is too long for "+name+". Please enter abreviated name: ");
            name=input.nextLine();
         }
         else{
            valid=true;
        }
      }
   }
   private static void studentLengthCheck(){
      boolean valid=false;
      int length=0;
      Scanner input=new Scanner(System.in);
      while(!valid){
         if(String.valueOf(iD).length()>7){
            System.out.print("ID# is too long. Enter a new ID# for "+name+":");
            iD=Integer.valueOf(input.nextLine());
         }
         else if(String.valueOf(iD).length()<7){
            System.out.print("ID# is too short. Enter a new ID# for "+name+":");
            iD=Integer.valueOf(input.nextLine());
         }
         else if(address.length()>100){
            System.out.print("Address is too long for "+name+". Enter abreviated address: ");
            address=input.nextLine();
         }
         else if(gender!='M' && gender!='F'){
            System.out.print("Gender must be either Male(M) or Female(F) for "+name+". Please re-enter: ");
            gender=input.next().charAt(0);
         }
         else if(name.length()>60){
            System.out.print("Name is too long for "+name+". Please enter abreviated name: ");
            name=input.nextLine();
         }
         else{
            valid=true;
        }
      }
   }   
   private static void courseLengthCheck(){
      boolean valid=false;
      int length=0;
      Scanner input=new Scanner(System.in);
      while(!valid){
         if(String.valueOf(iD).length()>7){
            System.out.print("ID# is too long. Enter a new ID# for "+name+":");
            iD=Integer.valueOf(input.nextLine());
         }
         else if(String.valueOf(iD).length()<7){
            System.out.print("ID# is too short. Enter a new ID# for "+name+":");
            iD=Integer.valueOf(input.nextLine());
         }
         else if(!semester.equals("FALL") && !semester.equals("SPRING") && !semester.equals("SUMMER")){
            System.out.print("Semester must be Fall, Spring, or Summer for "+name+". Enter new semester: ");
            semester=input.next(); 
         }
         else if(name.length()>60){
            System.out.print("Name is too long for "+name+". Please enter abreviated name: ");
            name=input.nextLine();
         }
         else{
            valid=true;
        }
      }
   } 
}