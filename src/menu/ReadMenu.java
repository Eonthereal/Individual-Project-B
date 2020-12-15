/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import dao.AssignmentDaoImpl;
import dao.AssignmentDaoInt;
import dao.CourseDaoImpl;
import dao.CourseDaoInt;
import dao.StudentDaoImpl;
import dao.StudentDaoInt;
import dao.TrainerDaoImpl;
import dao.TrainerDaoInt;
import dto.StudentAssignmentDto;
import java.util.List;
import java.util.Scanner;
import model.Assignment;
import model.Course;
import model.Student;
import model.Trainer;

/**
 *
 * @author eon_A
 */
public class ReadMenu {

    public static void readMenu() {
        int choice2;
        Scanner input = new Scanner(System.in);
        System.out.println("~~~~~~~~~~~~~~~~~~~READ FROM DATABASE!~~~~~~~~~~~~~~~~~~~");
        System.out.println(" ");
        System.out.println(" ");

        do {
            System.out.println("~~~~~~~~~~~~~~~~~~~Options Menu~~~~~~~~~~~~~~~~~~~");
            System.out.println("type 1 to see Students list");
            System.out.println("type 2 to see Trainers' list");
            System.out.println("type 3 to see Assignments' list");
            System.out.println("type 4 to see Courses' list");
            System.out.println("type 5 to see the Students per course");
            System.out.println("type 6 to see the Trainers per course");
            System.out.println("type 7 to see the Assignments per course");
            System.out.println("type 8 to see the Assignments per Student");          
            System.out.println("type 9 to see the list of Students that participating to more than one Courses");
            System.out.println("type 0 to exit");
            choice2 = 0;
            try {
                choice2 = input.nextInt();
            } catch (Exception e) {
                System.err.println("Please select with number");

            }
            switch (choice2) {
                case 1:
                    printListOfStudents();
                    break;
                case 2:
                    printListOfTrainers();
                    break;
                case 3:
                    printListOfAssignments();
                    break;
                case 4:
                    printListOfCourses();
                    break;
                case 5:
                    printListOfStudentsPerCourse();
                    break;
                case 6:
                    printListOfTrainersPerCourse();
                    break;
                case 7:
                    printListOfAssignmentsPerCourse();
                    break;
                case 8:
                    printListOfAssignmentsPerStudentPerCourse();
                    break;

                case 9:
                    printlistOfStudentMoreThanOneCourses();
                    break;
                default:
                    System.out.println("");
                    System.err.println("!!!~~~!!!~~~!!!Invalid Answer, please press one of the above!!!~~~!!!~~~!!!");
                    System.out.println("");
                    break;

                case 0:
                    System.out.println("~~~~~~~~~~~~~~~~~~~The Program has been terminated~~~~~~~~~~~~~~~~~~~");
            }
        } while (choice2 != 0);

        System.out.println("");

    }

    public static void printListOfStudents() {
        StudentDaoInt sdi = new StudentDaoImpl();
        List<Student> listOfStudents = sdi.getAllStudents();
        for (Student x : listOfStudents) {
            System.out.println((listOfStudents.indexOf(x) + 1) + ". " + x);
            System.out.println("");
        }
    }

    public static void printListOfTrainers() {
        TrainerDaoInt tdi = new TrainerDaoImpl();
        List<Trainer> listOfTrainers = tdi.getAllTrainers();
        for (Trainer x : listOfTrainers) {
            System.out.println((listOfTrainers.indexOf(x) + 1) + ". " + x);
            System.out.println("");
        }
    }

    public static void printListOfAssignments() {
        AssignmentDaoInt adi = new AssignmentDaoImpl();
        List<Assignment> listOfAssignments = adi.getAllAssignments();
        for (Assignment x : listOfAssignments) {
            System.out.println((listOfAssignments.indexOf(x) + 1) + ". " + x);
            System.out.println("");
        }
    }

    public static void printListOfCourses() {
        CourseDaoInt cdi = new CourseDaoImpl();
        List<Course> listOfCourses = cdi.getAllCourses();
        for (Course x : listOfCourses) {
            System.out.println((listOfCourses.indexOf(x) + 1) + ". " + x);
            System.out.println("");
        }
    }

    public static void printListOfStudentsPerCourse() {
        Scanner input = new Scanner(System.in);
        System.out.println("~~~~~~~~~Please select a CourseID to see the Students~~~~~~~~~");
        boolean caseCheck = false;
        String flag = "Y";
        String choice = null;
        do {
            StudentDaoInt sdi = new StudentDaoImpl();
            if (flag.equalsIgnoreCase("Y")) {
                printListOfCourses();
                try {
                    choice = input.nextLine();
                    List<Student> listOfStudents = sdi.getStudentsByCourseId(Integer.parseInt(choice));;
                    for (Student x : listOfStudents) {
                        System.out.println((listOfStudents.indexOf(x) + 1) + ". " + x);
                        System.out.println("");
                    }
                } catch (NumberFormatException e) {

                    System.err.println("Please select with number");

                }

            } else if (flag.equalsIgnoreCase("N")) {

                return;
            } else {
                System.err.println("Invalid Answer, type: Y or N");
            }
            System.out.println("Do you want to check another Course? (Y / N)");
            flag = input.nextLine();

        } while (caseCheck == false);

    }

    public static void printListOfTrainersPerCourse() {
        Scanner input = new Scanner(System.in);
        System.out.println("~~~~~~~~~Please select a CourseID to see the Trainers~~~~~~~~~");
        boolean caseCheck = false;
        String flag = "Y";
        String choice = null;
        do {
            TrainerDaoInt tdi = new TrainerDaoImpl();
            if (flag.equalsIgnoreCase("Y")) {
                printListOfCourses();
                try {
                    choice = input.nextLine();
                    List<Trainer> listOfTrainer = tdi.getTrainerByCourseId(Integer.parseInt(choice));
                    for (Trainer x : listOfTrainer) {
                        System.out.println((listOfTrainer.indexOf(x) + 1) + ". " + x);
                        System.out.println("");
                    }
                } catch (NumberFormatException e) {

                    System.err.println("Please select with number");
                }

            } else if (flag.equalsIgnoreCase("N")) {

                return;

            } else {
                System.err.println("Invalid Answer, type: Y or N");
            }
            System.out.println("Do you want to check another Course? (Y / N)");
            flag = input.nextLine();

        } while (caseCheck == false);

    }

    public static void printListOfAssignmentsPerCourse() {
        Scanner input = new Scanner(System.in);
        System.out.println("~~~~~~~~~Please select a CourseID to see the Assignements~~~~~~~~~");
        boolean caseCheck = false;
        String flag = "Y";
        String choice = null;
        do {
            AssignmentDaoInt adi = new AssignmentDaoImpl();
            if (flag.equalsIgnoreCase("Y")) {
                printListOfCourses();
                try {
                    choice = input.nextLine();
                    List<Assignment> listOfAssignment = adi.getAssignmentsByCourseId(Integer.parseInt(choice));
                    for (Assignment x : listOfAssignment) {
                        System.out.println((listOfAssignment.indexOf(x) + 1) + ". " + x);
                        System.out.println("");
                    }
                } catch (NumberFormatException e) {

                   System.err.println("Please select with number");
                }
            } else if (flag.equalsIgnoreCase("N")) {

                return;
            } else {
                System.err.println("Invalid Answer, type: Y or N");
            }
            System.out.println("Do you want to check another Course? (Y / N)");
            flag = input.nextLine();

        } while (caseCheck == false);

    }

    public static void printListOfAssignmentsPerStudentPerCourse() {
        Scanner input = new Scanner(System.in);
        System.out.println("~~~~~~~~~Please select a StudentID to see the Assignements~~~~~~~~~");
        boolean caseCheck = false;
        String flag = "Y";
        String choice = null;
        do {
            AssignmentDaoInt adi = new AssignmentDaoImpl();
            if (flag.equalsIgnoreCase("Y")) {
                printListOfStudents();
                try {
                    choice = input.nextLine();
                    List<StudentAssignmentDto> listOfAssignmentPerStudent = adi.getAssignmentsByStudentId(Integer.parseInt(choice));
                 
                    for (StudentAssignmentDto x : listOfAssignmentPerStudent) {
                        
                        System.out.println((listOfAssignmentPerStudent.indexOf(x) + 1) + ". " + x);
                        System.out.println("");
                 }
                } catch (NumberFormatException e) {

                   System.err.println("Please select with number");
                }
            } else if (flag.equalsIgnoreCase("N")) {

                return;
            } else {
                System.err.println("Invalid Answer, type: Y or N");
            }
            System.out.println("Do you want to check another Student? (Y / N)");
            flag = input.nextLine();

        } while (caseCheck == false);

    }
  
    public static void printlistOfStudentMoreThanOneCourses() {
        StudentDaoInt sdi = new StudentDaoImpl();
        List<Student> listOfStudents = sdi.getStudentsMoreThanOneCourses();
        for (Student x : listOfStudents) {
            System.out.println((listOfStudents.indexOf(x) + 1) + ". " + x);
            System.out.println("");
        }
    }

}
