/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import dao.AssignmentDaoImpl;
import dao.AssignmentDaoInt;
import dao.AssignmentPerCourseDaoImpl;
import dao.AssignmentPerCourseDaoInt;
import dao.AssignmentPerStudentDaoImpl;
import dao.AssignmentPerStudentDaoInt;
import dao.CourseDaoImpl;
import dao.CourseDaoInt;
import dao.StudentDaoImpl;
import dao.StudentDaoInt;
import dao.TrainerDaoImpl;
import dao.TrainerDaoInt;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static menu.ReadMenu.printListOfCourses;
import static menu.ReadMenu.printListOfTrainers;
import model.Assignment;
import model.Course;
import model.Student;
import model.Trainer;
import utils.SupportMethods;
import static utils.SupportMethods.studentsWithCourses;
import static utils.SupportMethods.studentswithoutCourses;
import static utils.SupportMethods.valDate;

/**
 *
 * @author eon_A
 */
public class WriteMenu {

    static SupportMethods validateDates = new SupportMethods();
    public static String staticDate;

    public static void writeMenu() {
        int choice;
        Scanner input = new Scanner(System.in);
        System.out.println("~~~~~~~~~~~~~~~~~~~WRIGHT TO DATABASE!~~~~~~~~~~~~~~~~~~~");
        System.out.println(" ");
        System.out.println(" ");

        do {
            System.out.println("~~~~~~~~~~~~~~~~~~~Options Menu~~~~~~~~~~~~~~~~~~~");
            System.out.println("type 1 to input a Student");
            System.out.println("type 2 to input a Trainer");
            System.out.println("type 3 to input an Assignment");
            System.out.println("type 4 to input a Course");
            System.out.println("type 5 to add existing Students to Courses");
            System.out.println("type 6 to input new Course and Subject to Trainers"); // Να το δω μετά
            System.out.println("type 7 to add Assignments to Course");
            System.out.println("type 8 to input a the Assignments per Student"); // Να το δω μετά
            System.out.println("type 0 to exit");
            choice = 0;
            try {
                choice = input.nextInt();
            } catch (Exception e) {
                System.err.println("Please select with number");

            }
            switch (choice) {
                case 1:
                    inputStudents();
                    break;
                case 2:
                    inputTrainers();
                    break;
                case 3:
                    inputAssignments();
                    break;
                case 4:
                    inputCourses();
                    break;
                case 5:
                    inputStudentsPerCourse(); // This Method has two functions, One for Students that have no Course yet and one to Add one Student to more Courses
                    break;
                case 6:
                    inputTrainersPerCourse(); // Trainers cannot stay without Course but can change between Courses, this method changes their Course and Subject via User Input.
                    break;
                case 7:
                    inputAssignmentsPerCourse(); 
                    break;
                case 8:
                    inputAssignmentsPerStudentPerCourse(); // Instead of this method I could do a graderMethod for Assingnments that have no Grades but there is not time atm.
                    break;
                default:
                    System.out.println("");
                    System.err.println("!!!~~~!!!~~~!!!Invalid Answer, please press one of the above!!!~~~!!!~~~!!!");
                    System.out.println("");
                    break;

                case 0:
                    System.out.println("~~~~~~~~~~~~~~~~~~~The Program has been terminated~~~~~~~~~~~~~~~~~~~");
            }
        } while (choice != 0);

        System.out.println("");
    
    }

    public static void inputStudents() {
        String loopCheck = "y";
        do {
            Scanner input = new Scanner(System.in);
            Student student = new Student();
            StudentDaoInt sdi = new StudentDaoImpl();

            student.setStundentID(sdi.maxStudentId() + 1); //I didn't use Auto Increment in SQL, in case IDs are usefull for other methods

            System.out.println("First name:");
            String firstName = input.nextLine();
            student.setFirstName(firstName);

            System.out.println("Last name:");
            String lastName = input.nextLine();
            student.setLastName(lastName);

            System.out.println("Date of birth (\"yyyy-mm-dd\"):");
            staticDate = input.next();
            valDate();
            LocalDate dateObj = LocalDate.parse(staticDate);
            student.setDateOfBirth(dateObj);

            System.out.println("Tuition Fees:");
            int tuitionFees = input.nextInt();
            student.setTuitionFees(SupportMethods.validatePositive(tuitionFees));

            sdi.insertStudent(student);

            System.out.println("Do you want to add another Student? (Y/N)");
            loopCheck = input.next();

        } while (loopCheck.equalsIgnoreCase("Y"));
    }

    public static void inputTrainers() {
        String loopCheck = "y";

        do {
            String choice = null;
            Scanner input = new Scanner(System.in);
            Trainer trainer = new Trainer();
            TrainerDaoInt tdi = new TrainerDaoImpl();
            CourseDaoInt cdi = new CourseDaoImpl();

            trainer.setTrainerID(tdi.maxTrainerId() + 1);

            System.out.println("First name:");
            String firstName = input.nextLine();
            trainer.setFirstName(firstName);

            System.out.println("Last name:");
            String lastName = input.nextLine();
            trainer.setLastName(lastName);

            System.out.println("Subject:");
            String subject = input.nextLine();
            trainer.setSubject(subject);

            System.out.println("Please Select the CourseID of the Trainer: ");
            printListOfCourses();
            try {
                choice = input.nextLine();
                trainer.setCourse(cdi.getCourseById(Integer.parseInt(choice)));

            } catch (NumberFormatException e) {
                System.err.println("Please select with number");
            }

            tdi.insertTrainer(trainer);

            System.out.println("Do you want to add another Trainer? (Y/N)");
            loopCheck = input.next();
        } while (loopCheck.equalsIgnoreCase("Y"));
    }

    public static void inputAssignments() {
        String loopCheck = "y";

        do {
            String choice = null;
            Scanner input = new Scanner(System.in);
            Assignment assignment = new Assignment();
            AssignmentDaoInt adi = new AssignmentDaoImpl();
            AssignmentPerCourseDaoInt apcdi = new AssignmentPerCourseDaoImpl();
            StudentDaoInt sdi = new StudentDaoImpl();
            List<Student> studentList = new ArrayList();
            AssignmentPerStudentDaoInt apsdi = new AssignmentPerStudentDaoImpl();

            assignment.setAssignmentID(adi.maxAssignmentId() + 1);

            System.out.println("Title:");
            String title = input.nextLine();
            assignment.setTitle(title);

            System.out.println("Description:");
            String description = input.nextLine();
            assignment.setDescription(description);

            adi.insertAssingnment(assignment);

            System.out.println("Please Select the CourseID of the Assignment: ");
            System.err.println("!!!**Important Note: All Students of this Course will be assigned to this Assignemt**!!!");
            printListOfCourses();
            try {
                choice = input.nextLine();
                apcdi.insertAssignmentPerCourse(assignment.getAssignmentID(), Integer.parseInt(choice));
                studentList=sdi.getStudentsByCourseId(Integer.parseInt(choice));
                LocalDate subDate = LocalDate.now();
                for (Student x: studentList){
                    int studentID=x.getStundentID();
                    apsdi.insertAssignmentPerStudent(assignment.getAssignmentID(), studentID, subDate); 
                } 

            } catch (NumberFormatException e) {
                System.err.println("Please select with number");

            }
            System.out.println("Do you want to add another Assignment? (Y/N)");
            loopCheck = input.next();
        } while (loopCheck.equalsIgnoreCase("Y"));
    }

    public static void inputCourses() {
        String loopCheck = "Y";
        do {
            Scanner input = new Scanner(System.in);
            Course course = new Course();
            CourseDaoInt cdi = new CourseDaoImpl();

            course.setCourseID(cdi.maxCourseId() + 1);

            System.out.println("Title:");
            String title = input.nextLine();
            course.setTitle(title);

            System.out.println("Stream:");
            String stream = input.nextLine();
            course.setStream(stream);

            System.out.println("Type:");
            String answer = input.nextLine();
            course.setType(SupportMethods.valPartFullTime(answer));

            System.out.println("Start Date (\"yyyy-mm-dd\"):");
            staticDate = input.next();
            valDate();
            LocalDate dateObj = LocalDate.parse(staticDate);
            course.setStartDate(dateObj);

            System.out.println("End Date (\"yyyy-mm-dd\"):");
            staticDate = input.next();
            valDate();
            LocalDate dateObj2 = LocalDate.parse(staticDate);
            course.setEndDate(dateObj2);

            cdi.insertCourse(course);

            System.out.println("Do you want to add another Course? (Y/N)");
            loopCheck = input.next();

        } while (loopCheck.equalsIgnoreCase("Y"));

    }

    public static void inputStudentsPerCourse() {
        Scanner input = new Scanner(System.in);
        boolean caseCheck = false;    
        String generalChoice = null;        
        do {
            System.out.println("type 1 to add Students without Courses to a Course");
            System.out.println("type 2 to add Students with Courses to another Course");
            System.out.println("type 0 to exit");
            generalChoice = input.nextLine();

            switch (Integer.parseInt(generalChoice)) {
                case 1:
                    studentswithoutCourses();
                    break;
                case 2:             
                    studentsWithCourses();
                    break;
                case 0:
                    caseCheck = true;
                    break;
            }
        } while (caseCheck == false);

    }
    
    
    public static void inputAssignmentsPerCourse() {
        System.out.println("In this project implemenetation, this method would have no point. "
                + "Since there is no point of an Assignment to exist without a Course. "
                + "The Method inputAssignments(),case 3, produces an Assignment through user input, "
                + "assigns it in a particular Course and its Students.");
    
    }
    
    public static void inputTrainersPerCourse(){
    Scanner input = new Scanner(System.in);
    String trainerID = null;
    String courseID = null;
    String subject = null;
    TrainerDaoInt tdi = new TrainerDaoImpl();
   
    try {
        System.out.println("~~~~~~~~~Please Select the **TrainerID** you want to Change Course~~~~~~~~~");
        System.out.println("");
        printListOfTrainers();
        trainerID = input.nextLine();
        
        System.out.println("~~~~~~~~~Please Select the new **CourseID** of the Trainer~~~~~~~~~");
        System.out.println("");
        printListOfCourses();
        courseID =  input.nextLine();
        
        System.out.println("~~~~~~~~~Please type what is the new Subject of the Trainer~~~~~~~~~ ");
        subject = input.nextLine();
        
        tdi.changeTrainerCourse(Integer.parseInt(courseID),Integer.parseInt(trainerID), subject);
        
       }catch (NumberFormatException e) {
                System.err.println("Please select with number");
            }  
        System.out.println("");
        System.out.println("DONE!!");
   
    }
    
    public static void inputAssignmentsPerStudentPerCourse(){
    System.out.println("In this project implemenetation, this method would have no point. "
                + "Since this scenario is covered partially with case 3 (Method inputAssignments()), "
            + "which produces an Assignment through user input, assigns it in a particular Course and its Students. "
            + "Furthemore with case 5 (method inputStudentsPerCourse()) we cover the other half of this function. "
            + "Since, when a students gets an extra Course, will also get automatically the Course's Assignments."
            + "A 'graderMethod' could replace this instead, for changing grades to students assignments. But there is no time. ");
    
    }
    

}


