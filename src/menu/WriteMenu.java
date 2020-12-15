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
import dao.CourseDaoImpl;
import dao.CourseDaoInt;
import dao.StudentDaoImpl;
import dao.StudentDaoInt;
import dao.TrainerDaoImpl;
import dao.TrainerDaoInt;
import dto.StudentAssignmentDto;
import static java.lang.System.exit;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import static menu.ReadMenu.printListOfCourses;
import static menu.ReadMenu.printListOfStudents;
import model.Assignment;
import model.Course;
import model.Student;
import model.Trainer;
import utils.SupportMethods;
import static utils.SupportMethods.valDate;

/**
 *
 * @author eon_A
 */
public class WriteMenu {

    static SupportMethods validateDates = new SupportMethods();
    public static String staticDate;

    public static void writeMenu() {
        int counter = 0;
        int choice;
        Scanner input = new Scanner(System.in);
        System.out.println("Hello!! \nDo you want to input your data manually?\nY / N or type \"exit\" to stop:");
//        do {
//            String choice = input.next();
//
//            if (choice.equalsIgnoreCase("Y")) {
//
//                System.out.println("~~~~~~~~~Courses, creation~~~~~~~~~");
//                ManualDataMethods.inputCourses();
//
//                System.out.println("~~~~~~~~~Students, creation~~~~~~~~~");
//                ManualDataMethods.inputStudents();
//
//                System.out.println("~~~~~~~~~Trainers, creation~~~~~~~~~");
//                ManualDataMethods.inputTrainers();
//
//                System.out.println("~~~~~~~~~Assignments, creation~~~~~~~~~");
//                ManualDataMethods.inputAssignments();
//
//                counter = 10;
//
//            } else if (choice.equalsIgnoreCase("N")) {
//
//                SyntheticDataMethods.botFiller();
//                counter = 10;
//
//            } else if (choice.equalsIgnoreCase("exit")) {
//                System.out.println("~~~~~~~~~~~~~~~~~~~The Program has been terminated~~~~~~~~~~~~~~~~~~~");
//                System.exit(0);   //googled it!
//            } else {
//
//                if (counter < 9) {
//                    System.err.println("Invalid Answer...\nPlease Try Again or Type \"exit\" to stop " + "\n*****" + (9 - counter) + " Attempts Remaining" + "*****");
//                    counter++;
//                } else {
//                    System.out.println("~~~~~~~~~~~~~~~~~~~The Program has been terminated automatically~~~~~~~~~~~~~~~~~~~");
//                    System.exit(0);
//                }
//            }
//
//        } while (counter < 10);

        do {
            System.out.println("~~~~~~~~~~~~~~~~~~~Options Menu~~~~~~~~~~~~~~~~~~~");
            System.out.println("type 1 to input a Student");
            System.out.println("type 2 to input a Trainer");
            System.out.println("type 3 to input an Assignment");
            System.out.println("type 4 to input a Course");
            System.out.println("type 5 to add existing Students to Courses");
            System.out.println("type 6 to input a the Trainers per course"); // Να το δω μετά
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
                    inputStudentsPerCourse();
                    break;
                case 6:
                    //Να βάλω να αλλάζει course ένα existing, οι νέοι trainers μπαίνουν σε Courses.
                    inputTrainersPerCourse();
                    break;
                case 7:
                    //New Assignments are added to Courses when they created. (Select "3" to Create a new Assignment)
                    //inputAssignmentsPerCourse(); 
                    break;
                case 8:
                    inputAssignmentsPerStudentPerCourse();
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

            student.setStundentID(sdi.maxStudentId() + 1);

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

            assignment.setAssignmentID(adi.maxAssignmentId() + 1);

            System.out.println("Title:");
            String title = input.nextLine();
            assignment.setTitle(title);

            System.out.println("Description:");
            String description = input.nextLine();
            assignment.setDescription(description);

            adi.insertAssingnment(assignment);

            System.out.println("Please Select the CourseID of the Assignment: ");
            printListOfCourses();
            try {
                choice = input.nextLine();
                apcdi.insertAssignmentPerCourse(assignment.getAssignmentID(), Integer.parseInt(choice));

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
        String flag1 = "Y";
        String flag2 = "Y";

        String choice = null;
        String choice2 = null;
        String generalChoice = null;
        int choiceInt;
        int choiceInt2;

        StudentDaoInt sdi = new StudentDaoImpl();
        do {
            System.out.println("type 1 to add Students without Courses to a Course");
            System.out.println("type 2 to add Students with Courses to another Course");
            System.out.println("type 0 to exit");
            generalChoice = input.nextLine();

            switch (Integer.parseInt(generalChoice)) {
                case 1:
                    if (flag1.equalsIgnoreCase("Y")) {
                        try {
                            List<Student> listOfStudents = sdi.printListOfStudentsWithoutCourse();
                            if (listOfStudents.size() == 0) {
                                System.out.println("");
                                System.err.println("There are no Students without Courses Assigned");

                            } else {
                                System.out.println("~~~~~~~~~Please select a StudentID to add them to a Course~~~~~~~~~");

                                for (Student x : listOfStudents) {
                                    System.out.println((listOfStudents.indexOf(x) + 1) + ". " + x);
                                    System.out.println("");
                                }
                                choice = input.nextLine();
                                choiceInt = Integer.parseInt(choice);
                                System.out.println("~~~~~~~~~Please select the CourseID of the Course~~~~~~~~~");
                                printListOfCourses();
                                choice2 = input.nextLine();
                                choiceInt2 = Integer.parseInt(choice2);
                                sdi.addExistingStudentToCourse(choiceInt2, choiceInt);
                            }

                        } catch (NumberFormatException e) {

                            System.err.println("Please select with number");
                        }
                    } else if (flag1.equalsIgnoreCase("N")) {

                        return;
                    } else {
                        System.err.println("Invalid Answer, type: Y or N");
                    }
                    System.out.println("Do you want to search for another Student? (Y / N)");
                    flag1 = input.nextLine();
                    break;
                case 2:
                    if (flag1.equalsIgnoreCase("Y")) {
                        try {

                            System.out.println("~~~~~~~~~Please select a StudentID to add them to a Course~~~~~~~~~");
                            printListOfStudents();
                            choice = input.nextLine();
                            choiceInt = Integer.parseInt(choice);

                            System.out.println("~~~~~~~~~Please select the CourseID of the Course~~~~~~~~~");
                            printListOfCourses();
                            choice2 = input.nextLine();
                            choiceInt2 = Integer.parseInt(choice2);

                            sdi.addExistingStudentToCourse(choiceInt2, choiceInt);

                        } catch (NumberFormatException e) {

                            System.err.println("Please select with number");
                        }
                    } else if (flag1.equalsIgnoreCase("N")) {

                        return;
                    } else {
                        System.err.println("Invalid Answer, type: Y or N");
                    }
                    System.out.println("Do you want to search for another Student? (Y / N)");
                    flag1 = input.nextLine();

                    break;
                case 0:

                    caseCheck = true;

                    break;

            }
        } while (caseCheck == false);

    }

}

}
