/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indivpro_zografos_part_b;

import dao.AssignmentDaoImpl;
import dao.AssignmentDaoInt;
import dao.AssignmentPerCourseDaoImpl;
import dao.AssignmentPerCourseDaoInt;
import dao.StudentDaoImpl;
import dao.StudentDaoInt;
import java.time.LocalDate;
import java.util.Scanner;
import menu.ReadMenu;
import static menu.ReadMenu.printListOfCourses;
import menu.WriteMenu;
import static menu.WriteMenu.staticDate;
import model.Assignment;
import model.Student;
import utils.SupportMethods;
import static utils.SupportMethods.valDate;


/**
 *
 * @author eon_A
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

// ReadMenu.readMenu();
 WriteMenu.writeMenu();

//           Scanner input = new Scanner(System.in);
//            Student student = new Student();
//            StudentDaoInt sdi = new StudentDaoImpl();
//            
//            student.setStundentID(sdi.maxStudentId()+1);
//                                                
//            System.out.println("First name:");
//            String firstName = input.nextLine();
//            student.setFirstName(firstName);
//
//            System.out.println("Last name:");
//            String lastName = input.nextLine();
//            student.setLastName(lastName);
//
//            System.out.println("Date of birth (\"yyyy-mm-dd\"):");
//            staticDate = input.next();
//            valDate();
//            LocalDate dateObj = LocalDate.parse(staticDate);
//            student.setDateOfBirth(dateObj);
//
//            System.out.println("Tuition Fees:");
//            int tuitionFees = input.nextInt();
//            student.setTuitionFees(SupportMethods.validatePositive(tuitionFees));
//            
//            sdi.insertStudent(student);
//            
//             
//        System.out.println(student);
// 
//        String loopCheck = "y";
//do {
//            String choice = null;
//            Scanner input = new Scanner(System.in);
//            Assignment assignment = new Assignment();
//            AssignmentDaoInt adi = new AssignmentDaoImpl();
//            AssignmentPerCourseDaoInt apcdi = new AssignmentPerCourseDaoImpl();
//
//            assignment.setAssignmentID(adi.maxAssignmentId() + 1);
//
//            System.out.println("Title:");
//            String title = input.nextLine();
//            assignment.setTitle(title);
//
//            System.out.println("Description:");
//            String description = input.nextLine();
//            assignment.setDescription(description);
//
//            //adi.insertAssingnment(assignment);
//            
//            System.out.println("Please Select the CourseID of the Assignment: ");
//            printListOfCourses();
//            try {
//                choice = input.nextLine();
//                //apcdi.insertAssignmentPerCourse(assignment.getAssignmentID(), Integer.parseInt(choice));
//
//            } catch (NumberFormatException e) {
//                System.err.println("Please select with number");
//       
//            }
//             System.out.println("Do you want to add another Assignment? (Y/N)");
//            loopCheck = input.next();
//}while (loopCheck.equalsIgnoreCase("Y"));
//
}
    
    
    
    

    
    
    
}
